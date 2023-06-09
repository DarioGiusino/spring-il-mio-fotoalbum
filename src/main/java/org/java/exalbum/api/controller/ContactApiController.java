package org.java.exalbum.api.controller;

import org.java.exalbum.pojo.Contact;
import org.java.exalbum.serv.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/")
public class ContactApiController {
	
	@Autowired
	private ContactService contactService;
	
	@PostMapping("contact/store")
	public ResponseEntity<Contact> apiContactCreate(@RequestBody Contact contact) {
		
		Contact newContact = contactService.save(contact);
		
		return new ResponseEntity<>(newContact, HttpStatus.OK);
	}
}
