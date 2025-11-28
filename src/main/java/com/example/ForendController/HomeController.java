package com.example.ForendController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Entity.Product;
import com.example.Repository.ProductRepository;
import com.example.Service.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductRepository productRepository;

	@GetMapping("/")
	public String base()
	{
		return "base";
	}
	
	@GetMapping("/index")
	public String index()
	{
		return "index";
	}
	

	@GetMapping("/reg")
	public String reg(Model m )
	{
		m.addAttribute("product", new Product());
		return "reg";
	}
	
	//Register here with API...
	@PostMapping("/saveProduct")
	public String insertProduct (@ModelAttribute("product") Product product,HttpSession session)
	{
		Product p=productService.saveProduct(product);
		if(!ObjectUtils.isEmpty(p))
		{
			session.setAttribute("succMsg","Product is Register!!");
		}
		return "redirect:/reg";
	}
	
	//Show all Product...
	@GetMapping("/show_Product")
	public String showProduct(Model m)
	{
		m.addAttribute("product",productService.getAllProduct());
		return "view";
	}
	
	//Update the Pending and Processing
	
	  @GetMapping("/product/update/{orderId}")
	    public String updateStatus(@PathVariable int orderId) {
	        Product pro = productRepository.findById(orderId).get();
	        
	        if(pro.getStatus().equals("Pending")) {
	        	pro.setStatus("Processing");
	        } else if(pro.getStatus().equals("Processing")) {
	        	pro.setStatus("Completed");
	        }

	        productRepository.save(pro);
	        return "redirect:/show_Product";
	    }
	
}



