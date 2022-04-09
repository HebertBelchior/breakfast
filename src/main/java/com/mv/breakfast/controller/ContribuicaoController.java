package com.mv.breakfast.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mv.breakfast.Colaborador;
import com.mv.breakfast.ColaboradorAlimento;
import com.mv.breakfast.repositorio.ColaboradorAlimentoRepositorio;
import com.mv.breakfast.repositorio.ColaboradorRepositorio;

@RestController
@RequestMapping("/contribuicao")
public class ContribuicaoController {
	
	@Autowired
	private DataSource dataSource;

	@PostMapping
	public ResponseEntity<?> criar(@RequestBody ContribuicaoDto contribuicaoDto){
		
		ColaboradorRepositorio colaboradorRepositorio = new ColaboradorRepositorio(dataSource);
		Colaborador colaborador = colaboradorRepositorio.buscar(contribuicaoDto.getCpf());
		Long colaboradorId = colaborador.getId();
		
		if(colaboradorId == null || colaboradorId == 0) {
			colaboradorId = colaboradorRepositorio.inserir(contribuicaoDto.getNome(), contribuicaoDto.getCpf());
		}
		
		ColaboradorAlimentoRepositorio colaboradorAlimentoRepositorio = new ColaboradorAlimentoRepositorio(dataSource);
		ColaboradorAlimento colaboradorAlimento = colaboradorAlimentoRepositorio.buscarPorAlimentoId(contribuicaoDto.getIdAlimento());
		
		if (colaboradorAlimento.getId() == null || colaboradorAlimento.getId() == 0) {
			colaboradorAlimentoRepositorio.inserir(colaboradorId, contribuicaoDto.getIdAlimento());	
		}else {
			return new ResponseEntity<>("Por favor, escolha outro alimento", HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
