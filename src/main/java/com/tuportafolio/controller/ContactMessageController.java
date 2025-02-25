package com.tuportafolio.controller;

import com.tuportafolio.model.ContactMessage;
import com.tuportafolio.service.ContactMessageService;
import com.tuportafolio.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
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
    public ResponseEntity<?> sendMessage(@RequestBody ContactMessage message) {
        try {

            ContactMessage savedMessage = contactMessageService.saveMessage(message);
            System.out.println("Guardado en la base de datos: " + savedMessage);

            // Intentar enviar el correo
            try {
                String subject = "Nuevo mensaje de contacto de " + message.getName();
                String body = "<p><strong>Nombre:</strong> " + message.getName() + "</p>"
                        + "<p><strong>Email:</strong> " + message.getEmail() + "</p>"
                        + "<p><strong>Mensaje:</strong><br>" + message.getMessage() + "</p>";

                System.out.println("Enviando correo...");
                emailService.sendEmail("siekren@hotmail.com", subject, body);
                System.out.println("Correo enviado con éxito.");
            } catch (MessagingException e) {
                System.err.println("Error al enviar el correo: " + e.getMessage());
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error al enviar el correo: " + e.getMessage());
            }

            return ResponseEntity.ok(savedMessage);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al procesar la solicitud: " + e.getMessage());
        }
    }
}
