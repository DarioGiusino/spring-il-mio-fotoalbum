package org.java.exalbum.api.controller;

import java.util.List;

import org.java.exalbum.pojo.Photo;
import org.java.exalbum.serv.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/")
public class PhotoApiController {
	@Autowired
	private PhotoService photoService;

	@GetMapping("photos")
	public ResponseEntity<List<Photo>> apiIndex() {

		List<Photo> photos = photoService.findByVisibleTrue();

		if (photos.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(photos, HttpStatus.OK);
		}
	}

	@GetMapping("photos/filter")
	public ResponseEntity<List<Photo>> apiFilterByTitle(@RequestParam(required = false) String title) {

		List<Photo> photos = photoService.findByTitleContainingAndVisibleTrue(title);

		if (photos.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(photos, HttpStatus.OK);
		}
	}

}
