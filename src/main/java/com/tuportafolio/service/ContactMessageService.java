package com.tuportafolio.service;

import com.tuportafolio.dto.ContactMessageDTO;
import com.tuportafolio.model.ContactMessage;
import com.tuportafolio.repository.ContactMessageRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactMessageService {

    private final ContactMessageRepository contactMessageRepository;

    public ContactMessageService(ContactMessageRepository contactMessageRepository) {
        this.contactMessageRepository = contactMessageRepository;
    }

    public List<ContactMessage> getAllMessages() {
        return contactMessageRepository.findAll();
    }

    public ContactMessage saveMessage(@Valid ContactMessageDTO contactMessageDTO) {
        ContactMessage message = new ContactMessage();
        message.setName(contactMessageDTO.getName());
        message.setEmail(contactMessageDTO.getEmail());
        message.setMessage(contactMessageDTO.getMessage());
        return contactMessageRepository.save(message);
    }
}
