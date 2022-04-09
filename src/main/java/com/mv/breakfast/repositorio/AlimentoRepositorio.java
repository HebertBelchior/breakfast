package com.mv.breakfast.repositorio;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.mv.breakfast.Alimento;

@Repository
public class AlimentoRepositorio {
	
	private DataSource dataSource;
	
	public AlimentoRepositorio(DataSource db) {
		dataSource = db;
	}

	public Alimento buscar(Long id) {
		Alimento alimento = new Alimento();

		String sql = "select id, nome from alimento where id=" + id;

		Connection conn;

		try {
			conn = dataSource.getConnection();
			Statement statement = conn.createStatement();

			ResultSet result = statement.executeQuery(sql);
			
			if(result.next()) {
				alimento.setId(result.getLong("id"));
				alimento.setNome(result.getString("nome"));
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
			
		return alimento;
	}

	public List<Alimento> buscarTodos(){
		List<Alimento> listaAlimentos = new ArrayList<Alimento>();
		String sql = "select id, nome from alimento";
		
		Connection conn;
		
		try {
			conn = dataSource.getConnection();
			Statement statement = conn.createStatement();

			ResultSet result = statement.executeQuery(sql);
			
			while(result.next()) {
				Alimento alimento = new Alimento();
				alimento.setId(result.getLong("id"));
				alimento.setNome(result.getString("nome"));
				
				listaAlimentos.add(alimento);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return listaAlimentos;
	}
}
