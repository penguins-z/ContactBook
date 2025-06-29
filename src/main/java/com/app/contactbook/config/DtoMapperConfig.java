package com.app.contactbook.config;

import com.app.contactbook.dto.ContactDTO;
import com.app.contactbook.entity.Contact;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DtoMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        // Map Contact → ContactDTO using record constructor
        TypeMap<Contact, ContactDTO> typeMap = mapper.createTypeMap(Contact.class, ContactDTO.class);
        typeMap.setProvider(request -> {
            Contact source = (Contact) request.getSource();
            return new ContactDTO(
                    source.getName(),
                    source.getEmail(),
                    source.getPhoneNumber()
            );
        });

        TypeMap<ContactDTO, Contact> toEntityMap = mapper.createTypeMap(ContactDTO.class, Contact.class);
        toEntityMap.setProvider(request -> {
            ContactDTO source = (ContactDTO) request.getSource();
            Contact entity = new Contact();
            entity.setName(source.name());
            entity.setPhoneNumber(source.phoneNumber());
            return entity;
        });

        return mapper;
    }
}
