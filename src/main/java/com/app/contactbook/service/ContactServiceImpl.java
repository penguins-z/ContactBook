package com.app.contactbook.service;

import com.app.contactbook.entity.Contact;
import com.app.contactbook.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.contactbook.exception.ContactNotFoundException;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactServiceInterface {

    private ContactRepository contactRepository;

    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public Contact getContactById(int id) {
        return contactRepository.findById(id).orElseThrow(() -> new ContactNotFoundException
                ("Contact not found for ID : "+ id));
    }

    @Override
    public Contact getContactByPhoneNumber(String phoneNumber) {
        return null;
    }

    @Override
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    @Override
    public String deleteContactById(int id) {
        if(!contactRepository.existsById(id)) {
            throw new ContactNotFoundException(id);
        }
        else{
            contactRepository.deleteById(id);
            return "Contact deleted successfully";
        }
    }

    @Override
    public Contact updateContactById(int id, Contact updatedContact) {
        Contact existingContact = contactRepository.findById(id).orElseThrow(() ->
                new ContactNotFoundException(id));

        existingContact.setName(updatedContact.getName());
        existingContact.setEmail(updatedContact.getEmail());
        existingContact.setPhoneNumber(updatedContact.getPhoneNumber());

        return contactRepository.save(existingContact);
    }
}
