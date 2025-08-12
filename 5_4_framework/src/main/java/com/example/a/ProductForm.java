package com.example.a;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
public class ProductForm {
	@NotBlank(message = "nameは必須です")
    private String name;

    @NotNull(message = "priceは必須です")
    private Integer price;

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
