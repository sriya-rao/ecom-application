package com.ecom.app.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.app.entity.File;
import com.ecom.app.entity.Product;
import com.ecom.app.service.CategoryService;
import com.ecom.app.service.FileServiceInterface;
import com.ecom.app.service.ProductService;


@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService service;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	FileServiceInterface fileService;

	private void addDynamicComponents(Model model) {
		model.addAttribute("categories", categoryService.getIdAndName());
	}
	
	
	@GetMapping("/load")
	public String loadPage(Model model) {
		model.addAttribute("product", new Product());
		addDynamicComponents(model);
		return "Product";
	}
	
	@PostMapping("/save")
	public String saveProduct(@RequestParam("file") MultipartFile file,@ModelAttribute Product product,Model model) {		
      product.setProdPicName(file.getOriginalFilename());
      product.setDiscountedPrice(product.getProdPrice()-(product.getProdDiscount()/100.0*product.getProdPrice()));
		Boolean isSaved=service.saveProduct(product);
	  	   if(isSaved)
	  		   model.addAttribute("message", "Saved successfully");
	  	   else {
				model.addAttribute("message", "Unable to Save");
			}

  	    File fileNew=new File();
		try {
		if(file!=null) {
		
        fileNew.setId(product.getId());
		fileNew.setFileName(file.getOriginalFilename());
		fileNew.setFileData(file.getBytes());
		fileService.saveFile(fileNew);
		}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		   	   model.addAttribute("product", new Product());
  	   addDynamicComponents(model);
  	   return "Product";
     }
	
	@GetMapping("/allFiles")
	public String showDocs(Model model) {
		List<Object[]> list=fileService.getFileIdAndName();
		model.addAttribute("list", list);
		return "Files";
	}
	
	
	@GetMapping("/image")
	public @ResponseBody ByteArrayResource showImage(@RequestParam Integer id) {
		File file=fileService.getFileById(id);
		byte[] image=file.getFileData();
		ByteArrayResource resource= new ByteArrayResource(image);
		return resource;
	}

	
	@GetMapping("/viewAll")
	public String viewAll(Model model) {
		List<Product> list=service.getAllProducts();
		model.addAttribute("list", list);
		return "ProductData";
	}
	
	@GetMapping("/delete")
	public String deleteProduct(@RequestParam Integer id,Model model) {
		service.deleteProduct(id);
	    fileService.deleteFileById(id);
		model.addAttribute("list", service.getAllProducts());
		return "ProductData";
	}
	
	
    
	
}
