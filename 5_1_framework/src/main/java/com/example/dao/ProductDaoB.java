package com.example.dao;

import org.springframework.stereotype.Repository;



@Repository
public class ProductDaoB implements productDao {
    @Override
    public String find() {
        return "消しゴム";
    }
}
