package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Users;
import com.example.demo.repository.UsersRepository;

@Service
public class UsersService {
	@Autowired
	UsersRepository usersRepository;
	@Autowired
	GestorServices gestorServices;
	@Autowired
	MotoristaServices motoristaServices;
	@Autowired
	EstudanteServices estudanteServices;
	

	/*Função para retornar todos os usuários cadastrados*/
	public List<Users> returnUsers(){
		List<Users> users = new ArrayList<Users>();
		usersRepository.findAll().forEach(users::add);
		return users;
	}
	
	/*Função para retornar um dos usuários cadastrados*/
	public Optional<Users> retUser(Integer id){
		Optional<Users> user = usersRepository.findById(id);
		return user;
	}

	/*Função para apagar um usuário do bd*/
	public void dletUser(Integer id) {
		usersRepository.deleteById(id);
	}
	
	
	
}
