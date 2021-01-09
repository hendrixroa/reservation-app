package com.reservation.api.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

import com.reservation.api.entities.Contact;
import com.reservation.api.dto.ContactDTO;

import com.reservation.api.services.ContactService;


@Tag(name = "Contact")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    ContactService contactService;

    @GetMapping("")
    public ResponseEntity<List<Contact>> getContacts() {
        try {
            List<Contact> contacts = contactService.getContacts();
            return new ResponseEntity<>(contacts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<Void> createContact(@RequestBody ContactDTO contact){
        try {
            contactService.createContact(contact);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{name}")
    public ResponseEntity<Void> updateContact(@PathVariable("name") String name, @RequestBody ContactDTO contact){
        try {
            contactService.updateContact(name, contact);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<Contact> getContactByName(@PathVariable("name") String name) {
        try {
            Optional<Contact> contact = contactService.getContactByName(name);
            if(contact.isPresent()) {
                return new ResponseEntity<>(contact.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
