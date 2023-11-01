package com.example.demo.controll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.domain.Users;
import com.example.demo.repository.UsersRepository;
import com.example.demo.service.GestorServices;
import com.example.demo.service.UsersService;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired 
    UsersService usersService;

    @Autowired 
     GestorServices gestorService;
    
    @Autowired
    UsersRepository usersRepository;
    
    @Autowired
    MotoristaController motoristaController;
    
    @Autowired
    EstudanteControll estudanteControll;
    
    @Autowired
    GestorController gestorController;
    
    @GetMapping
    public ModelAndView listarUsers(){
        ModelAndView movi = new ModelAndView("listagemUsers");
        movi.addObject("user", usersService.returnUsers());
        return movi;
    }

    @GetMapping("/add")
    public String cadastrarUser(){
        return "redirect:/auth/register";
    }

    @GetMapping("/{id}")
    public ModelAndView dadosuser(@PathVariable Integer id){
        Users user = usersRepository.findById(id).get();
        	Integer idn = Integer.parseInt(user.getIdTipoUsuario());
        	return gestorController.retornarG(idn);
    }
    @GetMapping("/delete/{id}")
    public String deleteUse(@PathVariable Integer id) {
    	Users user = usersRepository.findById(id).get();
    	usersService.dletUser(id);
        Integer idG = Integer.parseInt(user.getIdTipoUsuario());
        gestorService.deleteGestor(idG);
    	return "redirect:/user";
    }
    
}
