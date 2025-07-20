package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.productDao;

@Service
public class DefaultProductService implements ProductService {

    private final productDao productDao;

    @Autowired
    public DefaultProductService(productDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public String find() {
        return productDao.find();
    }
}