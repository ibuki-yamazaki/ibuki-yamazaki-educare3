package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	private productDao productsDao;
	
	public List<Products> findAll() {
        return productsDao.findAll();
    }
	

}
