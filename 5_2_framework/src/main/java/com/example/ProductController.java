package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    // JSON形式で商品一覧を返す（APIエンドポイント）
    @GetMapping("/api/products")
    @ResponseBody
    public List<Products> getProductsApi() {
        return productService.findAll();
    }
    
   
    // HTML画面で商品一覧を表示
    @GetMapping("/products")
    public String getProducts(Model model) {
        List<Products> products = productService.findAll();
        model.addAttribute("products", products);
        return "products"; // products.htmlテンプレートを返す
    }
    
   
}