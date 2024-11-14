package com.ecom.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecom.app.entity.Category;
import com.ecom.app.service.CategoryService;
import com.ecom.app.service.impl.CategoryServiceImpl;

@Controller
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	CategoryService service;
 
	@GetMapping("/load")
	public String category(Model model) {
		model.addAttribute("category", new Category());
		return "Category";
	}
	
	@PostMapping("/save")
	public String saveCategory(@ModelAttribute Category category,Model model) {
		 Boolean isSaved=service.saveCategory(category);
  	   if(isSaved)
  		   model.addAttribute("message", "Saved successfully");
  	   else {
			model.addAttribute("message", "Unable to Save");
		}
  	   model.addAttribute("category", new Category());
  	   return "Category";
     }
	
	@GetMapping("/all")
	public String getAllCategories(Model model) {
		List<Category> list=service.getAllCategories();
		model.addAttribute("list", list);
		return "CategoryData";
	}
	
	
	}
	

