package com.mv.breakfast.controller;

public class ContribuicaoDto {
	private Long idAlimento;
	private String nome;
	private String cpf;
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Long getIdAlimento() {
		return idAlimento;
	}
	public void setIdAlimento(Long idAlimento) {
		this.idAlimento = idAlimento;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

}
