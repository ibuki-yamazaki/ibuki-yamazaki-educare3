package com.example;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class ProductForm {
    @NotBlank(message = "{validation.name.required}")
    private String name;
    
    @NotBlank(message = "{validation.price.required}")
    @Pattern(regexp = "^[0-9]+$", message = "{validation.price.format}")
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
            Integer parsedPrice = Integer.parseInt(price.trim());
            return parsedPrice > 0 ? parsedPrice : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}