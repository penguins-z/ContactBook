package com.app.contactbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.contactbook.entity.contact;

public interface ContactRepository extends JpaRepository<contact,Long>{
  contact findByPhonenumber(long phonenumber);
}

