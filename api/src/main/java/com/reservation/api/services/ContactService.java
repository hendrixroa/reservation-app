package com.reservation.api.services;

import com.reservation.api.dto.ContactDTO;
import com.reservation.api.entities.Contact;
import com.reservation.api.repositories.ContactRepository;

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

    public Contact createContact(ContactDTO contactDTO) {
        Contact contactSaved = contactRepository.save(this.mapDTOToContact(contactDTO));
        return  contactSaved;
    }

    public Contact mapDTOToContact(ContactDTO contactDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Contact contact = modelMapper.map(contactDTO, Contact.class);
        return contact;
    }
}
