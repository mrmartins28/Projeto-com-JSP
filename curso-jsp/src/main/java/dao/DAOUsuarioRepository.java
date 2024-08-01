package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import connection.SingleConnection;
import model.ModelLogin;

public class DAOUsuarioRepository {
	
	Connection connection;
	
	public DAOUsuarioRepository() {
		
		connection = SingleConnection.getConnection();
		
	}
	
	public ModelLogin salvarUsuario(ModelLogin usuario) throws Exception {
		
		String sql = "INSERT INTO model_login(login, senha, nome, email) VALUES (?, ?, ?, ?);";
		
		
		PreparedStatement statement = connection.prepareStatement(sql);	
		statement.setString(1, usuario.getLogin());
		statement.setString(2, usuario.getSenha());
		statement.setString(3, usuario.getNome());
		statement.setString(4, usuario.getEmail());
		
		statement.execute();
		
		connection.commit();
		
		return this.buscarUsuario(usuario.getLogin());
		
		
		
	}
	
	public ModelLogin buscarUsuario(String login) throws Exception{
		
		ModelLogin modelLogin = new ModelLogin();
		
		String sql = "select * from model_login where upper(login) = upper(?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, login);
		
		ResultSet resultSet= statement.executeQuery();
		
		while(resultSet.next()) {
			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setNome(resultSet.getString("nome"));
			modelLogin.setSenha(resultSet.getString("senha"));
			
			
		}
		
		
		
		return modelLogin;
	}
	
	public boolean validaLogin(String login) throws Exception {
		
		ModelLogin modelLogin = buscarUsuario(login);
		if(modelLogin.equals(null)) {
			
			if(modelLogin.getLogin().equals(login)) {
				
				return true;
			} 
			
			
		}
		return false;
		
		
	}
}
