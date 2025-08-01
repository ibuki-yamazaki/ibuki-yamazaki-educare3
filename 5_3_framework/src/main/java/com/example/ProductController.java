package com.example;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/products")
public class ProductController {
    
    private ProductDao productDao = new ProductDao();
    
    // トップ画面を表示
    @GetMapping({"", "/"})
    public String index(Model model) {
        model.addAttribute("productForm", new ProductForm());
        return "top";
    }
    
    // 検索処理
    @PostMapping("/search")
    public String search(@ModelAttribute ProductForm productForm, Model model) {
        List<Product> products;
        
        if (productForm.isEmpty()) {
            // 検索条件が空の場合は全件表示
            products = productDao.findAll();
        } else {
            // 条件に基づいて検索（AND検索）
            products = productDao.search(productForm);
        }
        
        model.addAttribute("products", products);
        model.addAttribute("productForm", productForm);
        return "searchResult";
    }
    
    // 登録処理
    @PostMapping("/register")
    public String register(@ModelAttribute ProductForm productForm, Model model) {
        // 入力値の検証
        if (productForm.getName() == null || productForm.getName().trim().isEmpty()) {
            model.addAttribute("error", "商品名を入力してください");
            model.addAttribute("productForm", productForm);
            return "top";
        }
        
        if (productForm.getPrice() == null || productForm.getPrice().trim().isEmpty()) {
            model.addAttribute("error", "価格を入力してください");
            model.addAttribute("productForm", productForm);
            return "top";
        }
        
        Integer price = productForm.getPriceAsInteger();
        if (price == null || price <= 0) {
            model.addAttribute("error", "正しい価格を入力してください");
            model.addAttribute("productForm", productForm);
            return "top";
        }
        
        // データベースに登録
        boolean success = productDao.insert(productForm);
        
        if (success) {
            model.addAttribute("message", "登録が完了しました");
        } else {
            model.addAttribute("error", "登録に失敗しました");
        }
        
        return "insertResult";
    }
}