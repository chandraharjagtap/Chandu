package com.acks.Controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acks.model.Shop;
import com.acks.service.Shopservice;

@RestController

@RequestMapping("/shop/v1")
public class ShopController {
	
	@Autowired
	private Shopservice shopservice;
	
	@RequestMapping("/test")
	public String test()
	{
		String str=null;
		int length = str.length();
		
		return "test";
		
	}
	
	
	@PostMapping("/save")
	public ResponseEntity<Shop> saveshop(@Valid @RequestBody Shop shop)
	{
		Shop s = null;
		try {
			s = shopservice.saveshop(shop);
			return ResponseEntity.status(HttpStatus.CREATED).body(s);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		
	}
	
	@GetMapping("/show/{shopid}")
    public ResponseEntity<Optional<Optional<Shop>>> showw(@PathVariable("shopid") int shopid)
    {
		
		try {
      	 Optional<Shop> showw = shopservice.showw(shopid);
      	 
      	 if(showw.get().getShopid()==shopid)
      	 {
      		 return ResponseEntity.ok(Optional.of(showw));
      	 }
		}catch (Exception e) {
			// TODO: handle exception
			
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
      
      	 
      
      	return null;
    }

	@PutMapping("/update")
	public ResponseEntity<String> update(@RequestBody Shop shop)
	{
		boolean saveshop = shopservice.saveUpdate(shop);
		
		if(true==saveshop)
		{
			return ResponseEntity.status(HttpStatus.OK).body("Updated...");
		}
		else {
			return ResponseEntity.status(HttpStatus.CREATED).body("Inserted...");
		}
		
	}
	
	@GetMapping("/findall")
	public ResponseEntity<List<Shop>>  fhowall(Shop shop)
	{
	        List<Shop> showall = shopservice.showall();
	        
	        if(showall.size()<=0)
	        {
	        	return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	        }
	        else
	        {
	        	return ResponseEntity.of(Optional.of(showall));
	        }
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletedd(@PathVariable int id)
	{
		try {
			
		String message = shopservice.deleted(id);
		System.out.println(message);
	
		if(true)
		{
			return ResponseEntity.status(HttpStatus.OK).body("deleted");
		}
	
		}
		catch (Exception e) {
			// TODO: handle exception
			
		}
	
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("null");
	}
	
//	@ExceptionHandler(value = NullPointerException.class)
//	public String Exceptionhandlernull(Model model)
//	{
//	  return "nullpointer exception";
//	}
	
	//validation
	@PostMapping("/store")
 public ResponseEntity<Shop> createShop(@Valid @RequestBody Shop shop)
 {
	 Shop saveshop = shopservice.saveshop(shop);
	 return new ResponseEntity<>(saveshop,HttpStatus.CREATED);
 }
	

}
