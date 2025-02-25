package com.tuportafolio.controller;

import com.tuportafolio.model.ContactMessage;
import com.tuportafolio.service.ContactMessageService;
import com.tuportafolio.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "*") // Permitir solicitudes desde el frontend
public class ContactMessageController {

    private final ContactMessageService contactMessageService;
    private final EmailService emailService;

    public ContactMessageController(ContactMessageService contactMessageService, EmailService emailService) {
        this.contactMessageService = contactMessageService;
        this.emailService = emailService;
    }

    @GetMapping
    public List<ContactMessage> getMessages() {
        return contactMessageService.getAllMessages();
    }

    @PostMapping
    public ContactMessage sendMessage(@RequestBody ContactMessage message) throws MessagingException {
        System.out.println("📩 Recibido en el controller: " + message);
        ContactMessage savedMessage = contactMessageService.saveMessage(message);

        // Enviar un correo con los datos del mensaje
        String subject = "Nuevo mensaje de contacto de " + message.getName();
        String body = "<p><strong>Nombre:</strong> " + message.getName() + "</p>"
                + "<p><strong>Email:</strong> " + message.getEmail() + "</p>"
                + "<p><strong>Mensaje:</strong><br>" + message.getMessage() + "</p>";

        emailService.sendEmail("siekren@hotmail.com", subject, body); // Cambia al correo donde quieres recibir los mensajes

        return savedMessage;
    }
}
