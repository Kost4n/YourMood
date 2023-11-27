package com.kost4n.yourmood.messaging

import android.util.Log
import javax.mail.Authenticator
import javax.mail.MessagingException
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport


class EmailSender(val username: String, val password: String) {

     val session: Session = createSession()

    private fun createSession(): Session {
//        val properties = Properties().apply {
//            setProperty("mail.transport.protocol", "smtp");
//            setProperty("mail.host", "smtp.gmail.com");
//            put("mail.smtp.auth", "true");
//            put("mail.smtp.port", "587");
//            put("mail.debug", "true");
//            put("mail.smtp.starttls.enable", "false")
//            put("mail.smtp.socketFactory.port", "587");
//            put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
//            put("mail.smtp.socketFactory.fallback", "false");

//            put("mail.smtp.auth", true)
//            put("mail.smtp.starttls.enable", true)
//            put("mail.smtp.host", "smtp.gmail.com")
//            put("mail.smtp.port", "465")
////            put("mail.smtp.starttls.enable", "true");
//        }
        val properties = System.getProperties()
        properties["mail.smtp.auth"] = "true";
        properties["mail.smtp.starttls.enable"] = "true";
        properties["mail.smtp.host"] = "smtp.gmail.com";
        properties["mail.smtp.port"] = "587";

        val session = Session.getInstance(properties, object : Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(username, password)
            }
        })

        try {
            val transport: Transport = session.getTransport("smtp")
            transport.connect()
            transport.close()
            Log.d("gmail","Login credentials verified successfully.")
        } catch (e: MessagingException) {
            Log.d("gmail","Failed to verify login credentials: " + e.message)
        }


        return session
    }
}

