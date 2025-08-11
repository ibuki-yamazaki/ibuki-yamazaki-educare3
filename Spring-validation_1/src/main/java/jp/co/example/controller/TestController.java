package jp.co.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.example.controller.form.TestForm;

@Controller
public class TestController {

    @RequestMapping({ "/", "/index" })
    public String index(@ModelAttribute("test") TestForm form, Model model) {
        return "index";
    }

    @RequestMapping(value="/test", method=RequestMethod.POST)
    public String test(@Validated @ModelAttribute("test") TestForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "index";
        }

        return "top";
    }

}