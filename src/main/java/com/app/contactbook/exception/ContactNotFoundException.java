package com.app.contactbook.exception;

public class ContactNotFoundException extends RuntimeException{
    public ContactNotFoundException(String message) {
        super(message);
    }
    public ContactNotFoundException(int id) {
        super("Contact not found for ID: " + id);
    }
}
