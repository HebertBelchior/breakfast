package com.mv.breakfast.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mv.breakfast.Colaborador;

@Repository
public class ColaboradorRepositorio {

	@Autowired
	private DataSource dataSource;
	
	public ColaboradorRepositorio(DataSource db) {
		dataSource = db;
	}

	public Colaborador buscar(String cpf) {
		Colaborador colaborador = new Colaborador();
		
		String sql = "select id, nome, cpf from colaborador where cpf = '" + cpf + "'";

		Connection conn;
		try {
			conn = dataSource.getConnection();
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			if(result.next()) {
				colaborador.setId(result.getLong("id"));
				colaborador.setNome(result.getString("nome"));
				colaborador.setCpf(result.getString("cpf"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return colaborador; 
	}
	
	public Long inserir(String nome, String cpf) {
		String sql = "INSERT INTO colaborador (nome, cpf) VALUES (?, ?)";
		
		Connection conn;
		try {
			conn = dataSource.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, nome);
			statement.setString(2, cpf);
			statement.executeUpdate();

			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				return resultSet.getLong(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return 0L;
	}
}
