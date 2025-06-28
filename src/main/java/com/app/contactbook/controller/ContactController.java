package com.app.contactbook.controller;

import com.app.contactbook.entity.Contact;
import com.app.contactbook.service.ContactServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {
    private ContactServiceInterface contactService;

    @Autowired
    public void setContactService(ContactServiceInterface contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    public ResponseEntity<Contact> addContact(@RequestBody Contact contact) {
        Contact savedContact = contactService.saveContact(contact);
        return ResponseEntity.ok().body(savedContact);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable int id){
        Contact contact = contactService.getContactById(id);
        return ResponseEntity.ok().body(contact);
    }

    @GetMapping
    public ResponseEntity<List<Contact>> getAllContacts(){
        List<Contact> contacts = contactService.findAll();
        return ResponseEntity.ok().body(contacts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContactById(@PathVariable int id, @RequestBody Contact updatedContact){
        Contact savedContact = contactService.updateContactById(id, updatedContact);
        return ResponseEntity.ok().body(savedContact);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContactById(@PathVariable int id){
        String deleteStatus = contactService.deleteContactById(id);
        return ResponseEntity.ok().body(deleteStatus);
    }

}

