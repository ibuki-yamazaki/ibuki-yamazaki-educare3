package com.example.a;

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

    // トップ画面表示
    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(@ModelAttribute("productForm") ProductForm form) {
        return "index";
    }

    // 検索処理 - 一覧画面へ
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(@RequestParam(value = "searchName", required = false) String searchName,
                        Model model) {
        
        List<Product> products;
        if (StringUtils.hasText(searchName)) {
            products = productDao.findByName(searchName);
        } else {
            products = productDao.findAll();
        }

        model.addAttribute("products", products);
        model.addAttribute("searchName", searchName);
        return "list";
    }

    // 商品登録処理
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Validated @ModelAttribute("productForm") ProductForm form,
                           BindingResult bindingResult,
                           Model model) {

        if (bindingResult.hasErrors()) {
            // バリデーションエラー時はトップ画面に戻る（エラーメッセージ付き）
            model.addAttribute("hasValidationErrors", true);
            return "index";
        }

        Product product = new Product();
        product.setName(form.getName());
        product.setPrice(form.getPrice());
        productDao.insert(product);

        // 登録完了画面へ
        return "complete";
    }
}