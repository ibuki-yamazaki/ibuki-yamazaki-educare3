package com.example.four;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {

    @Autowired
    private ProductDao productDao;

    @RequestMapping({ "/", "/index" })
    public String index(@ModelAttribute("productForm") ProductForm form, Model model,
                       @RequestParam(value = "searchName", required = false) String searchName) {
        
        List<Product> products;
        
        // 検索処理
        if (StringUtils.hasText(searchName)) {
            products = productDao.findByName(searchName);
        } else {
            products = productDao.findAll();
        }
        
        model.addAttribute("products", products);
        return "product/index";
    }

    @RequestMapping(value="/register", method=RequestMethod.POST)
    public String register(@Validated @ModelAttribute("productForm") ProductForm form, 
                          BindingResult bindingResult, Model model) {
        
        if (bindingResult.hasErrors()) {
            // バリデーションエラーの場合、商品一覧も取得して表示
            List<Product> products = productDao.findAll();
            model.addAttribute("products", products);
            return "product/index";
        }

        // 商品登録
        Product product = new Product();
        product.setName(form.getName());
        product.setPrice(form.getPrice());
        productDao.insert(product);

        return "redirect:/";
    }
}