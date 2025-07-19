package com.springbootproject.phonebooksb.service;

import com.springbootproject.phonebooksb.model.Contact;
import com.springbootproject.phonebooksb.model.PhoneNumber;
import com.springbootproject.phonebooksb.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactServiceImpl(ContactRepository repository) {
        this.contactRepository = repository;
    }

    @Override
    public Contact saveContact(Contact contact) {
        if (contact.getPhoneNumberList() != null) {
            contact.getPhoneNumberList().forEach(phoneNumber -> phoneNumber.setContact(contact));
        }
        return contactRepository.save(contact);
    }

    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @Override
    public Contact getContactById(Long id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact not found with id: " + id));
    }

    @Override
    public Contact updateContact(Long id, Contact updateContact) {
//        Contact existContact  = contactRepository.findById(id)
//                .map(existingContact -> {
//                    existingContact.setFirstName(contact.getFirstName());
//                    existingContact.setLastName(contact.getLastName());
//                    existingContact.setEmail(contact.getEmail());
//                    existingContact.setPhoneNumberList(contact.getPhoneNumberList());
//                    return contactRepository.save(existingContact);
//                })
//                .orElseThrow(() -> new RuntimeException("Contact not found with id: " + id));
        Contact existContact = contactRepository.findById(id).orElseThrow(() -> new RuntimeException("Contact not found with id: " + id));
        existContact.setFirstName(updateContact.getFirstName());
        existContact.setLastName(updateContact.getLastName());
        existContact.setEmail(updateContact.getEmail());

        List<PhoneNumber> updatePhones = updateContact.getPhoneNumberList();
//        existContact.getPhoneNumberList().removeIf(existingPhone ->
//                updatePhones.stream().noneMatch(p -> p.getId() != null && p.getId().equals(existingPhone.getId()))
//        );

        for (PhoneNumber updatePhone : updatePhones) {
            if (updatePhone.getId() == null) {
                updatePhone.setContact(existContact);
                existContact.getPhoneNumberList().add(updatePhone);
            } else {
                for (PhoneNumber existingPhone : existContact.getPhoneNumberList()) {
                    if (existingPhone.getId().equals(updatePhone.getId())) {
                        existingPhone.setNumber(updatePhone.getNumber());
                        existingPhone.setType(updatePhone.getType());
                    }
                }
            }
        }

        return contactRepository.save(existContact);
    }

    @Override
    public void deleteContact(Long id) {
        contactRepository.findById(id)
                .ifPresentOrElse(contactRepository::delete,
                        () -> {
                            throw new RuntimeException("Contact not found with id: " + id);
                        });
    }
}
