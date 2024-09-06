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

	public ModelLogin salvarUsuario(ModelLogin usuario, Long userLogado) throws Exception {

		String sql = "INSERT INTO model_login(login, senha, nome, email, usuario_id, perfil, sexo, cep, rua, bairro, localidade, uf, numero) VALUES (?, ?, ?, ?, ?,?,?,?,?,?,?,?,?)";

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, usuario.getLogin());
		statement.setString(2, usuario.getSenha());
		statement.setString(3, usuario.getNome());
		statement.setString(4, usuario.getEmail());
		statement.setLong(5, userLogado);
		statement.setString(6, usuario.getPerfil());
		statement.setString(7, usuario.getSexo());
		
		statement.setString(8, usuario.getCep());
		statement.setString(9, usuario.getRua());
		statement.setString(10, usuario.getBairro());
		statement.setString(11, usuario.getLocalidade());
		statement.setString(12, usuario.getUf());
		statement.setString(13, usuario.getNumero());

		statement.execute();

		connection.commit();
		
		

		if (usuario.getImagemUser() != null && !usuario.getImagemUser().isEmpty()) {

			sql = "update model_login set fotouser=?, extensaofotouser=? where login=? ";

			statement = connection.prepareStatement(sql);
			statement.setString(1, usuario.getImagemUser());
			statement.setString(2, usuario.getExtensaoImagemUser());
			statement.setString(3, usuario.getLogin());

			statement.execute();

			connection.commit();
		}

		return this.buscarUsuario(usuario.getLogin(), userLogado);

	}

	public List<ModelLogin> consultaUsuariolist(Long userLogado) throws Exception {

		String sql = "select * from model_login where useradmin is false and usuario_id=" + userLogado;
		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultSet = statement.executeQuery();

		List<ModelLogin> listaUsuarios = new ArrayList<ModelLogin>();

		while (resultSet.next()) {
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setNome(resultSet.getString("nome"));
			// modelLogin.setSenha(resultSet.getString("senha"));
			modelLogin.setPerfil(resultSet.getString("perfil"));
			modelLogin.setSexo(resultSet.getString("sexo"));

			listaUsuarios.add(modelLogin);

		}

		return listaUsuarios;

	}

	public List<ModelLogin> consultaUsuariolist(String nome, Long userLogado) throws Exception {

		String sql = "select * from model_login where upper(nome) like upper(?) and useradmin is false and usuario_id=";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, "%" + nome + "%");
		statement.setLong(2, userLogado);

		ResultSet resultSet = statement.executeQuery();

		List<ModelLogin> listaUsuarios = new ArrayList<ModelLogin>();

		while (resultSet.next()) {
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setNome(resultSet.getString("nome"));
			// modelLogin.setSenha(resultSet.getString("senha"));
			modelLogin.setPerfil(resultSet.getString("perfil"));
			modelLogin.setSexo(resultSet.getString("sexo"));

			listaUsuarios.add(modelLogin);

		}

		return listaUsuarios;

	}

	public ModelLogin buscarUsuarioId(String id, Long userLogado) throws Exception {

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
			modelLogin.setPerfil(resultSet.getString("perfil"));
			modelLogin.setSexo(resultSet.getString("sexo"));
			modelLogin.setImagemUser(resultSet.getString("fotouser"));
			modelLogin.setExtensaoImagemUser(resultSet.getString("extensaofotouser"));
			
			modelLogin.setCep(resultSet.getString("cep"));
			modelLogin.setRua(resultSet.getString("rua"));
			modelLogin.setBairro(resultSet.getString("bairro"));
			modelLogin.setLocalidade(resultSet.getString("localidade"));
			modelLogin.setUf(resultSet.getString("uf"));
			modelLogin.setNumero(resultSet.getString("numero"));

		}

		return modelLogin;
	}

	public ModelLogin buscarUsuario(String login, Long userLogado) throws Exception {

		ModelLogin modelLogin = new ModelLogin();

		String sql = "select * from model_login where upper(login) = upper(?) and useradmin is false and usuario_id="
				+ userLogado;
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, login);
		// statement.setLong(2, userLogado);

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setNome(resultSet.getString("nome"));
			modelLogin.setSenha(resultSet.getString("senha"));
			modelLogin.setPerfil(resultSet.getString("perfil"));
			modelLogin.setSexo(resultSet.getString("sexo"));
			modelLogin.setImagemUser(resultSet.getString("fotouser"));
			
			modelLogin.setCep(resultSet.getString("cep"));
			modelLogin.setRua(resultSet.getString("rua"));
			modelLogin.setBairro(resultSet.getString("bairro"));
			modelLogin.setLocalidade(resultSet.getString("localidade"));
			modelLogin.setUf(resultSet.getString("uf"));
			modelLogin.setNumero(resultSet.getString("numero"));

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
			modelLogin.setPerfil(resultSet.getString("perfil"));
			modelLogin.setSexo(resultSet.getString("sexo"));
			modelLogin.setImagemUser(resultSet.getString("fotouser"));
			
			modelLogin.setCep(resultSet.getString("cep"));
			modelLogin.setRua(resultSet.getString("rua"));
			modelLogin.setBairro(resultSet.getString("bairro"));
			modelLogin.setLocalidade(resultSet.getString("localidade"));
			modelLogin.setUf(resultSet.getString("uf"));
			modelLogin.setNumero(resultSet.getString("numero"));

		}

		return modelLogin;
	}

	public ModelLogin buscarUsuarioAdmin(String login) throws Exception {

		ModelLogin modelLogin = new ModelLogin();

		String sql = "select * from model_login where upper(login) = upper(?) and useradmin is true";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, login);

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setNome(resultSet.getString("nome"));
			modelLogin.setSenha(resultSet.getString("senha"));
			modelLogin.setPerfil(resultSet.getString("perfil"));
			modelLogin.setSexo(resultSet.getString("sexo"));
			modelLogin.setImagemUser(resultSet.getString("fotouser"));
			
			modelLogin.setCep(resultSet.getString("cep"));
			modelLogin.setRua(resultSet.getString("rua"));
			modelLogin.setBairro(resultSet.getString("bairro"));
			modelLogin.setLocalidade(resultSet.getString("localidade"));
			modelLogin.setUf(resultSet.getString("uf"));
			modelLogin.setNumero(resultSet.getString("numero"));

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

		String sql = "update model_login set senha = ?, nome = ?, email= ?, perfil=?, sexo=?, cep=?, rua=?, bairro=?, localidade=?, uf=?, numero=? where upper(login) = upper(?)";

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, modelLogin.getSenha());
		statement.setString(2, modelLogin.getNome());
		statement.setString(3, modelLogin.getEmail());
		statement.setString(4, modelLogin.getPerfil());
		statement.setString(5, modelLogin.getSexo());

		statement.setString(6, modelLogin.getCep());
		statement.setString(7, modelLogin.getRua());
		statement.setString(8, modelLogin.getBairro());
		statement.setString(9, modelLogin.getLocalidade());
		statement.setString(10, modelLogin.getUf());
		statement.setString(11, modelLogin.getNumero());
		
		statement.setString(12, modelLogin.getLogin());

		statement.execute();

		connection.commit();
		if (modelLogin.getImagemUser() != null && !modelLogin.getImagemUser().isEmpty()) {

			sql = "update model_login set fotouser=?, extensaofotouser=? where id=? ";

			statement = connection.prepareStatement(sql);
			statement.setString(1, modelLogin.getImagemUser());
			statement.setString(2, modelLogin.getExtensaoImagemUser());
			statement.setLong(3, modelLogin.getId());

			statement.execute();

			connection.commit();
		}

		return this.buscarGenerico(modelLogin.getLogin());

	}

	public void deletarUsuario(String id) throws Exception {

		String sql = "delete from model_login where id =" + id + " and useradmin is false";
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.execute();

		connection.commit();

	}

	public ModelLogin buscarGenerico(String login) throws Exception {

		ModelLogin modelLogin = new ModelLogin();

		String sql = "select * from model_login where login = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, login);

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setNome(resultSet.getString("nome"));
			modelLogin.setSenha(resultSet.getString("senha"));
			modelLogin.setUserAdmin(resultSet.getBoolean("useradmin"));
			modelLogin.setPerfil(resultSet.getString("perfil"));
			modelLogin.setSexo(resultSet.getString("sexo"));
			modelLogin.setImagemUser(resultSet.getString("fotouser"));
			
			modelLogin.setCep(resultSet.getString("cep"));
			modelLogin.setRua(resultSet.getString("rua"));
			modelLogin.setBairro(resultSet.getString("bairro"));
			modelLogin.setLocalidade(resultSet.getString("localidade"));
			modelLogin.setUf(resultSet.getString("uf"));
			modelLogin.setNumero(resultSet.getString("numero"));

		}

		return modelLogin;

	}
}
