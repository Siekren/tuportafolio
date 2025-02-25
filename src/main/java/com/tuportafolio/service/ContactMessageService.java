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

    public ContactMessage saveMessage(@Valid ContactMessageDTO message) {
        return contactMessageRepository.save(message);
    }
}
