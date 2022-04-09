package com.mv.breakfast.controller;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mv.breakfast.ColaboradorAlimento;
import com.mv.breakfast.repositorio.ColaboradorAlimentoRepositorio;

@RestController
@RequestMapping("/colaboradorAlimento")
public class ColaboradorAlimentoController {
	@Autowired
	private DataSource dataSource;
	
	@GetMapping
	public ResponseEntity<?> listar(){
		ColaboradorAlimentoRepositorio colaboradorAlimentoRepositorio = new ColaboradorAlimentoRepositorio(dataSource);
		List<ColaboradorAlimento> contribuicoes = colaboradorAlimentoRepositorio.buscarTodos();
		 
		return new ResponseEntity<>(contribuicoes, HttpStatus.OK);
	}
}
