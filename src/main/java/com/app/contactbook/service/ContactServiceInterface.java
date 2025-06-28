package com.app.contactbook.service;

import com.app.contactbook.entity.Contact;

import java.util.List;

public interface ContactServiceInterface {
    Contact saveContact(Contact contact);
    Contact getContactById(int id);
    Contact getContactByPhoneNumber(String phoneNumber);
    List<Contact> findAll();
    String deleteContactById(int id);
    Contact updateContactById(int id, Contact contact);
}
