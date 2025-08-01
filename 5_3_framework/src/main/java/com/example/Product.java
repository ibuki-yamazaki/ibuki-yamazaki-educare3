package com.example;

public class Product {
    private Integer id;
    private String name;
    private Integer price;
    
    // デフォルトコンストラクタ
    public Product() {}
    
    // コンストラクタ
    public Product(String name, Integer price) {
        this.name = name;
        this.price = price;
    }
    
    public Product(Integer id, String name, Integer price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    
    // ゲッター・セッター
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getPrice() {
        return price;
    }
    
    public void setPrice(Integer price) {
        this.price = price;
    }
    
    @Override
    public String toString() {
        return "Product{id=" + id + ", name='" + name + "', price=" + price + "}";
    }
}