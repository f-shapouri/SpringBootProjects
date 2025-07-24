package com.springbootproject.phonebooksb.controller;

import com.springbootproject.phonebooksb.model.Contact;
import com.springbootproject.phonebooksb.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService ContactService) {
        this.contactService = ContactService;
    }

    @Operation(summary = "Get all contacts", description = "Retrieve a list of all contacts in the phonebook")
    @GetMapping
    public List<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }

    @Operation(summary = "Add a new contact", description = "Create a new contact in the phonebook")
    @PostMapping
    public Contact addContact(@RequestBody Contact contact) {
        return contactService.saveContact(contact);
    }

    @Operation(summary = "Get contact by ID", description = "Retrieve a specific contact by its ID")
    @GetMapping("/{id}")
    public Contact getContact(@PathVariable Long id) {
        return contactService.getContactById(id);
    }

    @Operation(summary = "Update an existing contact", description = "Update the details of an existing contact")
    @PutMapping("/{id}")
    public Contact updateContact(@PathVariable Long id, @RequestBody Contact updated) {
        return contactService.updateContact(id, updated);
    }

    @Operation(summary = "Delete a contact", description = "Delete a specific contact by its ID")
    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
    }
}
