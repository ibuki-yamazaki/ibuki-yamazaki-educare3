package com.example.a;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductForm {
    
    @NotBlank(message = "{validation.name.required}")
    private String name;

    @NotNull(message = "{validation.price.required}")
    @Min(value = 1, message = "{validation.price.min}")
    private Integer price;

    // デフォルトコンストラクタ
    public ProductForm() {
    }

    // ゲッター・セッター
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
}