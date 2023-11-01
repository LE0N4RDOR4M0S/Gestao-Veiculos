package com.example.demo.controll;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.AuthenticationDTO;
import com.example.demo.domain.RegisterDTO;
import com.example.demo.domain.Users;
import com.example.demo.repository.UsersRepository;
import com.example.demo.security.TokenService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
@RequestMapping("auth")
public class AuthenticationController {
	@Autowired
	private AuthenticationManager authentication;
	@Autowired
	private UsersRepository userre;
	@Autowired
	private TokenService tokenService;
	
	@GetMapping("/login")
	public String telaLogin() {
		return "login";
	}
	
	@PostMapping("/login")
    public String login(@ModelAttribute @Valid AuthenticationDTO data, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var auth = this.authentication.authenticate(usernamePassword);
                
        var token = tokenService.generateToken((Users) auth.getPrincipal());

        Cookie cookie = new Cookie("seuCookieNome", token);
        cookie.setPath("/");
        response.addCookie(cookie);

        return "redirect:/dashboard";
        } catch (Exception e) {
        	return "acessoNegado";
        }
    }

	@GetMapping("/register/{id}")
	public ModelAndView telaRegistro(@PathVariable Integer id){
		ModelAndView movi = new ModelAndView("registroUser");
		String idn = id.toString();
		movi.addObject("gestor",idn);
		return movi;
	}
	
	@PostMapping("/register/{id}")
	public String register(@ModelAttribute @Valid RegisterDTO data, @PathVariable Integer id) {
		try {
	    if (this.userre.findByLogin(data.username()) != null) {
	        return "redirect:/erroCadastro";
	    }
	    String encriptedPassword = new BCryptPasswordEncoder().encode(data.password());
	    Users user = new Users(data.username(), encriptedPassword, data.role(), data.idTipoUsuario());
	    this.userre.save(user);
	    return "redirect:/salvoSucesso";
	} catch(Exception e) {
		return "redirect:/erroCadastro";
	}
	}


}
