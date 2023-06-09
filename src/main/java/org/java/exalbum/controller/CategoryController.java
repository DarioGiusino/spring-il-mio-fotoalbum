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

import jakarta.validation.Valid;

@Controller
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PhotoService photoService;

	@GetMapping
	public String index(Model model) {
		List<Category> categories = categoryService.findAll();
		
		model.addAttribute("categories", categories);
		
		return "category/index";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("category", new Category());
		
		return "category/create";
	}
	
	@PostMapping("/store")
	public String store(@Valid @ModelAttribute Category category, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			
			model.addAttribute("category", category);
			model.addAttribute("errors", bindingResult);
			
			return "category/create";
		}
		
		categoryService.save(category);
		
		return "redirect:/categories";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		
		Optional<Category> categoryOpt = categoryService.findById(id);
		Category category = categoryOpt.get();
		
		for (Photo p : photoService.findAll()) {
			p.removeCategory(category);
			photoService.save(p);
		}
		
		categoryService.delete(category);
		
		return "redirect:/categories";
	}
}
