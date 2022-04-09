package com.mv.breakfast;

public class ColaboradorAlimento {
	private Long id;
	private Colaborador colaborador;
	private Alimento alimento;
	
	public Colaborador getColaborador() {
		return colaborador;
	}
	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}
	public Alimento getAlimento() {
		return alimento;
	}
	public void setAlimento(Alimento alimento) {
		this.alimento = alimento;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

}
