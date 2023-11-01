package com.example.demo.controll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.VeiculoServices;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    VeiculoServices veiculoServices;

    @GetMapping
    private ModelAndView telaDashboard(){
        Integer manutencao = veiculoServices.retornarPStatus("manutencao").size();
        Integer atividade = veiculoServices.retornarPStatus("atividade").size();
        Integer estacionados = veiculoServices.retornarPStatus("estacionado").size();
        ModelAndView movi = new ModelAndView("telaDashboard");
        movi.addObject("manutencao", manutencao);
        movi.addObject("atividade", atividade);
        movi.addObject("estacionado",estacionados);
        return movi;
    }
}
