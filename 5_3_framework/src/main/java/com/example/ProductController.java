package com.example;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {
    
    @Autowired
    private ProductDao productDao;
    
    @Autowired
    private MessageSource messageSource;
    
    // トップ画面を表示
    @GetMapping({"index", "/"})
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
    public String register(@Validated @ModelAttribute ProductForm productForm, 
                          BindingResult bindingResult, Model model, Locale locale) {
        
        // バリデーションエラーがある場合
        if (bindingResult.hasErrors()) {
            return "top";
        }
        
        // 追加のビジネスロジックバリデーション
        Integer price = productForm.getPriceAsInteger();
        if (price == null || price <= 0) {
            String errorMessage = messageSource.getMessage("validation.price.positive", null, locale);
            model.addAttribute("error", errorMessage);
            return "top";
        }
        
        // データベースに登録
        boolean success = productDao.insert(productForm);
        
        if (success) {
            String successMessage = messageSource.getMessage("message.register.success", null, locale);
            model.addAttribute("message", successMessage);
        } else {
            String errorMessage = messageSource.getMessage("message.register.error", null, locale);
            model.addAttribute("error", errorMessage);
        }
        
        return "insertResult";
    }
}