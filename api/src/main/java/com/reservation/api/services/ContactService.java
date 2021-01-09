package com.reservation.api.services;

import com.reservation.api.dto.ContactDTO;
import com.reservation.api.entities.Contact;
import com.reservation.api.repositories.ContactRepository;

import javassist.tools.web.BadHttpRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    ContactRepository contactRepository;

    public List<Contact> getContacts() {
        List<Contact> contacts = new ArrayList<Contact>();
        contactRepository.findAll().forEach(contacts::add);
        return contacts;
    }

    public Optional<Contact> getContactByName(String name) {
        Optional<Contact> contact = contactRepository.findByName(name);
        return contact;
    }

    public void createContact(ContactDTO contactDTO) {
        contactRepository.save(this.mapDTOToContact(contactDTO));
    }

    public void updateContact(String name, ContactDTO contactDTO) {
        Optional<Contact> contactFound = contactRepository.findByName(name);
        if(contactFound.isPresent()) {
            Contact contact = contactFound.get();
            if(contactDTO.getBirthdate() != null){
                contact.setBirthdate(contactDTO.getBirthdate());
            }
            if(contactDTO.getName() != null) {
                // Check first if the new name exists
                List<Contact> contacts = contactRepository.findByNameContaining(contactDTO.getName());

                /**
                 * We are checking if the name is repeated and it is not the current
                 * contact to return an error because the name should be unique
                 */
                Contact existContactName = (Contact) contacts.stream().filter(c -> c.getName() == contactDTO.getName() && c.getId() != contact.getId());
                if(existContactName != null) {
                    throw new InvalidInputException
                }
            }

        }
    }

    public Contact mapDTOToContact(ContactDTO contactDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Contact contact = modelMapper.map(contactDTO, Contact.class);
        return contact;
    }
}
