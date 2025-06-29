package com.app.contactbook.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.el.stream.Optional;
import org.modelmapper.ModelMapper;
// import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.contactbook.dto.ContactDTO;
import com.app.contactbook.entity.contact;
import com.app.contactbook.repository.ContactRepository;

@RestController
@RequestMapping("/test")
public class pagecontroller {
    @Autowired
    private ContactRepository ContactRepository;

    @PostMapping("/Addusers")
    public ResponseEntity<String> createUser(@RequestBody contact user) {
        ContactRepository.save(user);
         return ResponseEntity.ok("User saved");
    }
    @PutMapping("/updateUser/{phonenumber}")
public ResponseEntity<String> updateUser(@PathVariable long phonenumber, @RequestBody contact updatedUser) {
    contact existingUser = ContactRepository.findByPhonenumber(phonenumber);

    if (existingUser != null) {
        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        ContactRepository.save(existingUser);
        return ResponseEntity.ok("User updated successfully");
    } else {
        return ResponseEntity.status(404).body("User not found");
    }
}
@GetMapping("/getAllUsers1")
public ResponseEntity<java.util.List<contact>> getAllUsers1() {
    return ResponseEntity.ok(ContactRepository.findAll());
}
@Autowired
private ModelMapper mp;
@GetMapping("/getAllUsers")
public ResponseEntity<List<ContactDTO>> getAllUsers() {
    List<contact> users = ContactRepository.findAll();

    List<ContactDTO> userDTOs = users.stream()
        .map(user -> mp.map(user, ContactDTO.class))
        .collect(Collectors.toList());

    return ResponseEntity.ok(userDTOs);
}            

}
