package com.acks.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acks.model.Shop;
import com.acks.repositry.Shoprepository;

@Service
public class Shopservice {
	
	@Autowired
	private Shoprepository shoprepository;
	
	public Shop saveshop(Shop shop)
	{
		Shop save = shoprepository.save(shop);
		return save;
	}
	
	public Boolean saveUpdate(Shop shop)
	{
		try {
			Shop shop2 = shoprepository.findById(shop.getShopid()).get();
			
			if(shop.getShopid() == shop2.getShopid())
			{
				Shop save1 = shoprepository.save(shop);
				return true;
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		Shop save = shoprepository.save(shop);
		return false;
		
	}
	
	
	public Optional<Shop> showw( int id)
	{
		Optional<Shop> findById = shoprepository.findById(id);
		
		return findById;
		
	}
	
	public List<Shop> showall()
	{
	    Iterable<Shop> findAll = shoprepository.findAll();
	    return (List<Shop>) findAll;
	}
	
	
	public String deleted(int id)
	{
		try
		{
		Shop s1 = shoprepository.findById(id).get();
		if(s1!=null)
		{
		
	      shoprepository.deleteById(id);
	      return "Deleted";
		}
		else
		{
			return "Not Found";
			
			
		}
		}
		catch(NoSuchElementException e)
		{
			return "Not Found";
		}
	}

}
