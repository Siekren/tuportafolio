package com.tuportafolio.controller;

import com.tuportafolio.dto.ContactMessageDTO;
import com.tuportafolio.model.ContactMessage;
import com.tuportafolio.service.ContactMessageService;
import com.tuportafolio.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
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
    public ResponseEntity<?> sendMessage(@Valid @RequestBody ContactMessageDTO messageDTO) throws MessagingException {
        contactMessageService.saveMessage(messageDTO);

        // Enviar correo
        String subject = "Nuevo mensaje de contacto de " + messageDTO.getName();
        String body = "<p><strong>Nombre:</strong> " + messageDTO.getName() + "</p>"
                + "<p><strong>Email:</strong> " + messageDTO.getEmail() + "</p>"
                + "<p><strong>Mensaje:</strong><br>" + messageDTO.getMessage() + "</p>";

        emailService.sendEmail("siekren@hotmail.com", subject, body);

        return ResponseEntity.ok("Mensaje enviado correctamente");
    }
}
