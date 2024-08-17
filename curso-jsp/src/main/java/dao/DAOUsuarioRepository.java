package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	
	public List<ModelLogin> consultaUsuariolist() throws Exception { 

		

		String sql = "select * from model_login where useradmin is false" ;
		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultSet = statement.executeQuery();
		

		List<ModelLogin> listaUsuarios = new ArrayList<ModelLogin>();

		while (resultSet.next()) {
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setNome(resultSet.getString("nome"));
			//modelLogin.setSenha(resultSet.getString("senha"));
			
			listaUsuarios.add(modelLogin);

		}

		return listaUsuarios;

	}

	public List<ModelLogin> consultaUsuariolist(String nome) throws Exception { 

		

		String sql = "select * from model_login where upper(nome) like upper(?) and useradmin is false";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, "%" + nome + "%");

		ResultSet resultSet = statement.executeQuery();
		

		List<ModelLogin> listaUsuarios = new ArrayList<ModelLogin>();

		while (resultSet.next()) {
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setNome(resultSet.getString("nome"));
			//modelLogin.setSenha(resultSet.getString("senha"));
			
			listaUsuarios.add(modelLogin);

		}

		return listaUsuarios;

	}
	
	public ModelLogin buscarUsuarioId(String id) throws Exception {

		ModelLogin modelLogin = new ModelLogin();

		String sql = "select * from model_login where id = ? and useradmin is false";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, Long.parseLong(id));

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setNome(resultSet.getString("nome"));
			modelLogin.setSenha(resultSet.getString("senha"));
			modelLogin.setUserAdmin(resultSet.getBoolean("useradmin"));

		}

		return modelLogin;
	}


	public ModelLogin buscarUsuario(String login) throws Exception {

		ModelLogin modelLogin = new ModelLogin();

		String sql = "select * from model_login where upper(login) = upper(?) and useradmin is false";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, login);

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setNome(resultSet.getString("nome"));
			modelLogin.setSenha(resultSet.getString("senha"));

		}

		return modelLogin;
	}
	
	public boolean isAdmin(String login) throws Exception {


		String sql = "select * from model_login where upper(login) = upper(?) and useradmin is true";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, login);

		ResultSet resultSet = statement.executeQuery();

	

		return resultSet.next();
	}

	public boolean validaLogin(String login) throws Exception {

		String sql = "select count(1) > 0 as exist from model_login where upper(login) = upper(?); ";

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, login);

		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {

			return resultSet.getBoolean("exist");
		}

		return false;
	}

	public ModelLogin atualizarCadastro(ModelLogin modelLogin) throws Exception {

		String sql = "update model_login set senha = ?, nome = ?, email= ? where upper(login) = upper(?)";

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, modelLogin.getSenha());
		statement.setString(2, modelLogin.getNome());
		statement.setString(3, modelLogin.getEmail());
		statement.setString(4, modelLogin.getLogin());

		statement.execute();

		connection.commit();

		return this.buscarUsuario(modelLogin.getLogin());
	}

	public void deletarUsuario(String id) throws Exception {

		String sql = "delete from model_login where id =" + id + " and useradmin is false";
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.execute();

		connection.commit();

	}
}
