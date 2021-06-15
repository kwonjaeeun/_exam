package com.koreait.spring2;

import com.koreait.spring2.vo.ApartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApartController {

    @Autowired
    private ApartService service;

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("locationList", service.selLocationCodeList());
        return "main";
    }
    @GetMapping("/result")
    public String result_view(Model model){
        model.addAttribute("list",service.selApartData());
        return "";
    }
    @PostMapping("/result")
    public String result(ApartDTO param){
        service.saveData(param);
        return "redirect:/result";
    }
}
