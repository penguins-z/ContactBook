package com.app.contactbook.controller;

import com.app.contactbook.dto.ContactDTO;
import com.app.contactbook.entity.Contact;
import com.app.contactbook.service.ContactServiceInterface;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/contacts")
public class ContactController {
    private ContactServiceInterface contactService;
    private ModelMapper modelMapper;

    @Autowired
    public void setContactService(ContactServiceInterface contactService) {
        this.contactService = contactService;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactDTO> getContactById(@PathVariable int id) {
       // ContactDTO contactDTO = modelMapper.map(contactService.getContactById(id), ContactDTO.class);
        return ResponseEntity.ok(modelMapper.map(contactService.getContactById(id), ContactDTO.class));
    }

    /*@PostMapping
    public ResponseEntity<Contact> addContact(@RequestBody Contact contact) {
        Contact savedContact = contactService.saveContact(contact);
        return ResponseEntity.ok().body(savedContact);
    }*/
    @PostMapping
    public ResponseEntity<ContactDTO> addContact(@Valid @RequestBody ContactDTO contactDTO) {
        Contact contact = modelMapper.map(contactDTO, Contact.class);
        Contact savedContact = contactService.saveContact(contact);
        ContactDTO ResponseDTO= modelMapper.map(savedContact, ContactDTO.class);
        return ResponseEntity.ok(ResponseDTO);
    }

    /*@GetMapping
    public ResponseEntity<List<ContactDTO>> getAllContacts(){
        List<Contact> contacts = contactService.findAll();
        return ResponseEntity.ok().body(contacts);
    }*/

    @GetMapping
    public ResponseEntity<List<ContactDTO>> getAllContacts() {
        List<ContactDTO> dtoList = contactService.findAll().stream()
                .map(contact -> modelMapper.map(contact, ContactDTO.class))
                .toList();

        return ResponseEntity.ok(dtoList);
    }



    @PutMapping("/{id}")
    public ResponseEntity<ContactDTO> updateContactById(@PathVariable int id, @Valid @RequestBody ContactDTO updatedContactDTO){
        Contact contact = modelMapper.map(updatedContactDTO, Contact.class);
        Contact savedContact = contactService.updateContactById(id, contact);
        ContactDTO ResponseDTO= modelMapper.map(savedContact, ContactDTO.class);
        return ResponseEntity.ok(ResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContactById(@PathVariable int id){
        String deleteStatus = contactService.deleteContactById(id);
        return ResponseEntity.ok().body(deleteStatus);
    }

}

