package com.mv.breakfast.controller;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mv.breakfast.Alimento;
import com.mv.breakfast.repositorio.AlimentoRepositorio;

@RestController
@RequestMapping("/alimento")
public class AlimentoController {
	
	@Autowired
	private DataSource dataSource;
	
	@GetMapping
	public ResponseEntity<?> listar(){
		AlimentoRepositorio alimentoRepositorio = new AlimentoRepositorio(dataSource);
		List<Alimento> alimentos = alimentoRepositorio.buscarTodos();
		 
		return new ResponseEntity<>(alimentos, HttpStatus.OK);
	}
}
