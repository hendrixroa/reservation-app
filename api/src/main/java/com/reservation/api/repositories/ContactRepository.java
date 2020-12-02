package com.reservation.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reservation.api.entities.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByNameContaining(String name);
    Optional<Contact> findByName(String name);
}
