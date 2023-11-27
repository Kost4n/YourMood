package com.kost4n.yourmood.messaging

import java.io.File
import javax.mail.Message
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeBodyPart
import javax.mail.internet.MimeMessage
import javax.mail.internet.MimeMultipart

class CreateMessage(private val emailSender: EmailSender) {

    fun createEmail(to: String, subject: String, bodyText: String): Message {
        val message = MimeMessage(emailSender.session)
        message.setFrom(InternetAddress(emailSender.username))
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to))
        message.subject = subject
        message.setText(bodyText)
        return message
    }

    fun createEmailWithAttachment(to: String, subject: String, bodyText: String, attachment: File): Message {
        val message = MimeMessage(emailSender.session)
        message.setFrom(InternetAddress(emailSender.username))
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to))
        message.subject = subject

        val multipart = MimeMultipart()

        val messageBodyPart = MimeBodyPart()
        messageBodyPart.setText(bodyText)
        multipart.addBodyPart(messageBodyPart)

        val attachmentBodyPart = MimeBodyPart()
        attachmentBodyPart.attachFile(attachment)
        multipart.addBodyPart(attachmentBodyPart)

        message.setContent(multipart)
        return message
    }
}


