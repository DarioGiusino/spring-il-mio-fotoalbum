package org.java.exalbum.controller;

import java.util.List;
import java.util.Optional;

import org.java.exalbum.pojo.Category;
import org.java.exalbum.pojo.Photo;
import org.java.exalbum.serv.CategoryService;
import org.java.exalbum.serv.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/photo")
public class PhotoController {

	@Autowired
	private PhotoService photoService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public String index(Model model){
		List<Photo> photos = photoService.findAll();
		
		model.addAttribute("photos", photos);

		return "photo/index";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable("id") Integer id, Model model) {
		
		Optional<Photo> photoOpt = photoService.findById(id);
		
		Photo photo = photoOpt.get();
		List<Category> categories = photo.getCategories();
		
		model.addAttribute("photo", photo);
		model.addAttribute("categories", categories);
		
		return "photo/show";
	}
	
	@PostMapping("/filter")
	public String filterPhoto(Model model, @RequestParam(required = false) String title) {
		
		List<Photo> photos = photoService.findByTitle(title);
		model.addAttribute("photos", photos);
		model.addAttribute("title", title);
		
		return "photo/index";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		List<Category> categories = categoryService.findAll();
		
		model.addAttribute("photo", new Photo());
		model.addAttribute("categories", categories);
		
		return "photo/create";
	}
	
	@PostMapping("/store")
	public String store(@Valid @ModelAttribute Photo photo, BindingResult bindingResult, Model model){
		
		if (bindingResult.hasErrors()) {
			
			model.addAttribute("photo", photo);
			model.addAttribute("errors", bindingResult);
			
			return "photo/create";
		}
		
		if(photo.isVisible() == null) photo.setVisible(false);
		
		photoService.save(photo);
		
		return "redirect:/";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") int id, Model model) {
		List<Category> categories = categoryService.findAll();
		
		Optional<Photo> photoOpt = photoService.findById(id);
		Photo photo = photoOpt.get();
		
		model.addAttribute("photo", photo);
		model.addAttribute("categories", categories);
		
		return "photo/edit";
	}
	
	@PostMapping("/update/{id}")
	public String update(@PathVariable("id") int id, @Valid @ModelAttribute Photo photo, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			
			model.addAttribute("photo", photo);
			model.addAttribute("errors", bindingResult);
			
			return "photo/edit";
		}
		
		if(photo.isVisible() == null) photo.setVisible(false);
		
		photoService.save(photo);
		
		return "redirect:/";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		
		Optional<Photo> photoOpt = photoService.findById(id);
		Photo photo= photoOpt.get();
		
		photoService.delete(photo);
		
		return "redirect:/";
	}
}
