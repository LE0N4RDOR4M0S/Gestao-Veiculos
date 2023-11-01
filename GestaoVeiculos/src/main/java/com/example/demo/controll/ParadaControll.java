package com.example.demo.controll;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.domain.Parada;
import com.example.demo.domain.Rota;
import com.example.demo.domain.Veiculo;
import com.example.demo.service.ParadaServices;
import com.example.demo.service.RotaServices;
import com.example.demo.service.VeiculoServices;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;

@Controller
@RequestMapping("/paradas")
public class ParadaControll {
	/*------------------------------Injeções*/
	@Autowired
	ParadaServices paradase;
	
	@Autowired
	RotaServices rotase;
	
	@Autowired
	VeiculoServices veise;
	
	@Autowired
	Veiculo veiculo;
	
	@Autowired
	Rota rota;
	
	@Autowired
	Parada parada;
	
	@Value("${google.maps.api.key}")
    private String apiKey;
	/*----------------------------------- Retornar paradas cadastradas */
	@GetMapping
	private ModelAndView retornarVeiculos(){
		ModelAndView movi = new ModelAndView("listagemParada");
		movi.addObject("paradas",paradase.retornarParadas());
		return movi;
	}
	
	/*------------------------------------Retornar uma parada pelo id*/
	@GetMapping("/{id}")
	private Optional<Parada> buscarParadai(@PathVariable Integer id){
		return paradase.buscaParada(id);
	}

	@GetMapping("/add")
	private String addParada(){
		return "addParada";
	}

	/*-----------------------------------Cadastrar nova parada*/
	@PostMapping("/add")
    public String cadastrarParada(@RequestParam String endereco, Model model) {
        GeoApiContext context = new GeoApiContext.Builder().apiKey(apiKey).build();

        try {
            // Faça a busca de geocodificação
            GeocodingResult[] results = GeocodingApi.geocode(context, endereco).await();

            if (results.length > 0) {
                // Se pelo menos um resultado foi encontrado, crie uma nova Parada com as coordenadas
                Parada novaParada = new Parada(endereco, results[0].geometry.location.lat, results[0].geometry.location.lng);
                paradase.addParada(novaParada);
                return "redirect:/salvoSucesso";
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Lide com exceções, se necessário
            model.addAttribute("erro", "Ocorreu um erro ao cadastrar a parada.");
            return "erroNoCadastro";
        }

        // Retorna uma resposta de erro se a geocodificação falhar
        model.addAttribute("erro", "Endereço não encontrado.");
        return "erroNoCadastro";
    }
	/*-----------------------------------Atualizar uma parada existente*/
	@GetMapping("/update/{id}")
	private ModelAndView editParada(@PathVariable Integer id) {
		ModelAndView movi = new ModelAndView("updParada");
		movi.addObject("parada",paradase.buscaParada(id).get());
		return movi;
	
	}
	@PostMapping("/update/{id}")
	private String updParada(@PathVariable Integer id, @ModelAttribute Parada parada) {
		try {
			paradase.attParada(id, parada);
			return "redirect:/paradas";
		}catch(Exception e) {
			return "erroAtualizacao";
		}
	}
	/*----------------------------------Deletar uma parada */
	@GetMapping("/delete/{id}")
	private String deleteParada(@PathVariable Integer id) {
		paradase.deleteParada(id);
		return "redirect:/paradas";
	}
}
