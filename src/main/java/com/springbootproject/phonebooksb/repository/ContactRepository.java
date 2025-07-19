package com.springbootproject.phonebooksb.repository;

import com.springbootproject.phonebooksb.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
