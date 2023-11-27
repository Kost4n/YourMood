package com.kost4n.yourmood.messaging

import java.io.File
import javax.mail.Transport

class SendEmail(private val emailSender: EmailSender, private val createMessage: CreateMessage) {

    fun send(to: String, subject: String, bodyText: String) {
        val message = createMessage.createEmail(to, subject, bodyText)
        Transport.send(message)
    }

    fun sendWithAttachment(to: String, subject: String, bodyText: String, attachment: File) {
        val message = createMessage.createEmailWithAttachment(to, subject, bodyText, attachment)
        Transport.send(message)
    }
}

