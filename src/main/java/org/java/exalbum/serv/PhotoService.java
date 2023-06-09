package org.java.exalbum.serv;

import java.util.List;
import java.util.Optional;

import org.java.exalbum.pojo.Photo;
import org.java.exalbum.repo.PhotoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoService {
	
	@Autowired
	private PhotoRepo photoRepo;
	
	public List<Photo> findAll() {
		
		return photoRepo.findAll();
	}
	
	public Photo save(Photo photo) {
		
		return photoRepo.save(photo);
	}
	
	public Optional<Photo> findById(int id) {
		
		return photoRepo.findById(id);
	}
	
	public List<Photo> findByTitle(String title) {
		
		return photoRepo.findByTitleContaining(title);
	}
	
	public List<Photo> findByVisibleTrue(){
		return photoRepo.findByVisibleTrue();
	}
	
	public List<Photo> findByTitleContainingAndVisibleTrue(String title){
		return photoRepo.findByTitleContainingAndVisibleTrue(title);
	}
	
	public void delete(Photo photo) {
		
		photoRepo.delete(photo);
	}
}
