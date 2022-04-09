package com.mv.breakfast.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mv.breakfast.Alimento;
import com.mv.breakfast.Colaborador;
import com.mv.breakfast.ColaboradorAlimento;

@Repository
public class ColaboradorAlimentoRepositorio {
	
	@Autowired
	private DataSource dataSource;
	
	public ColaboradorAlimentoRepositorio(DataSource db) {
		dataSource = db;
	}
	
    public Long inserir (Long idColaborador, Long idAlimento) {
    	String sql = "INSERT INTO colaborador_alimento (id_colaborador, id_alimento) VALUES (?, ?)";
    	
    	Connection conn;
    	
    	try {
    		conn = dataSource.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setLong(1, idColaborador);
			statement.setLong(2, idAlimento);
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
    	
    public ColaboradorAlimento buscarPorAlimentoId(Long idAlimento) {
    	ColaboradorAlimento colaboradorAlimento = new ColaboradorAlimento();
    	
    	String sql = "SELECT id, id_alimento, id_colaborador FROM colaborador_alimento WHERE id_alimento =" + idAlimento;
    	
    	Connection conn;
    	try {
			conn = dataSource.getConnection();
			Statement statement = conn.createStatement();

			ResultSet result = statement.executeQuery(sql);
			
			if(result.next()) {
				Alimento alimento = new Alimento();
				alimento.setId(result.getLong("id_alimento"));
				
				Colaborador colaborador = new Colaborador();
				colaborador.setId(result.getLong("id_colaborador"));
				
				colaboradorAlimento.setId(result.getLong("id"));
				colaboradorAlimento.setAlimento(alimento);
				colaboradorAlimento.setColaborador(colaborador);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
    	return colaboradorAlimento;
    }
    
    public List<ColaboradorAlimento> buscarTodos (){
    	List<ColaboradorAlimento> listaColaboradorAlimentos = new ArrayList<ColaboradorAlimento>();
		String sql = "SELECT ca.id as id_colaborador_alimento, c.id as id_colaborador, c.nome as nome_colaborador, c.cpf, "
				+ "a.id as id_alimento, a.nome as nome_alimento "
				+ "FROM colaborador_alimento as ca "
				+ "INNER JOIN colaborador as c on c.id = ca.id_colaborador "
				+ "INNER JOIN alimento as a on a.id = ca.id_alimento";
				
		Connection conn;
		try {
			conn = dataSource.getConnection();
			Statement statement = conn.createStatement();

			ResultSet result = statement.executeQuery(sql);
			
			while(result.next()) {
				Alimento alimento = new Alimento();
				alimento.setId(result.getLong("id_alimento"));
				alimento.setNome(result.getString("nome_alimento"));
				
				Colaborador colaborador = new Colaborador();
				colaborador.setId(result.getLong("id_colaborador"));
				colaborador.setNome(result.getString("nome_colaborador"));
				colaborador.setCpf(result.getString("cpf"));
				
				ColaboradorAlimento colaboradorAlimento = new ColaboradorAlimento();
				colaboradorAlimento.setId(result.getLong("id_colaborador_alimento"));
				colaboradorAlimento.setAlimento(alimento);
				colaboradorAlimento.setColaborador(colaborador);
				
				listaColaboradorAlimentos.add(colaboradorAlimento);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return listaColaboradorAlimentos;
    }
}
