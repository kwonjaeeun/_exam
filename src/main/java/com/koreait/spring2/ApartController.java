package com.koreait.spring2;

import com.koreait.spring2.vo.ApartDTO;
import com.koreait.spring2.vo.LocationCodeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ApartController {

    @Autowired
    private ApartService service;

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("locationList", service.selLocationCodeList(null));
        return "main";
    }
    @GetMapping("/result")
    public String result_view(Model model, ApartDTO param){
        List<LocationCodeEntity> result=service.selLocationCodeList(param);
        model.addAttribute("location", result.get(0));
        model.addAttribute("list",service.selApartData(param));
        return "result";
    }
    @PostMapping("/result")
    public String result(ApartDTO param){
        service.saveData(param);
        return "redirect:/result?ex_cd="+param.getEx_cd();
    }
}
