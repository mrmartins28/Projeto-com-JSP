package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnection;
import model.ModelLogin;

public class DAOLoginRepository {

	private Connection connection;

	public DAOLoginRepository() {
		connection = SingleConnection.getConnection();
	}

	public boolean validaAutenticacao(ModelLogin login) {
		boolean isValid= false;
		try {
			String sql = "select * from model_login where login = ? and senha = ? ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, login.getLogin());
			preparedStatement.setString(2, login.getSenha());
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				isValid = true;
				
			} else {
				isValid = false;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return isValid;

	}

}
