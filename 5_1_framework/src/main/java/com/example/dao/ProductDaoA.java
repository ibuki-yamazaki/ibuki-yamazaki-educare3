package com.example.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class ProductDaoA implements productDao {
    @Override
    public String find() {
        return "納豆";
    }
}
