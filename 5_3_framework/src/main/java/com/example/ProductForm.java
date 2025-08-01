package com.example;

public class ProductForm {
    private String name;
    private String price;
    
    public ProductForm() {}
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPrice() {
        return price;
    }
    
    public void setPrice(String price) {
        this.price = price;
    }
    
    // 検索条件が空かどうかをチェック
    public boolean isEmpty() {
        return (name == null || name.trim().isEmpty()) && 
               (price == null || price.trim().isEmpty());
    }
    
    // priceをIntegerに変換
    public Integer getPriceAsInteger() {
        if (price == null || price.trim().isEmpty()) {
            return null;
        }
        try {
            return Integer.parseInt(price.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}