package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOUsuarioRepository {
	private Connection connection;

	public DAOUsuarioRepository() {
		connection = SingleConnectionBanco.getConnection();
	}

	public ModelLogin gravarUser(ModelLogin modelLogin) throws SQLException {
		if (modelLogin.isNovo()) {
			String sql = "INSERT INTO usuario(login, senha, nome, email) VALUES (?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, modelLogin.getLogin());
			preparedStatement.setString(2, modelLogin.getSenha());
			preparedStatement.setString(3, modelLogin.getNome());
			preparedStatement.setString(4, modelLogin.getEmail());

			preparedStatement.execute();
			connection.commit();
		} else {
			String sql = "UPDATE usuario SET login=?, senha=?, nome=?, email=? WHERE id = " + modelLogin.getId() + ";";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, modelLogin.getLogin());
			preparedStatement.setString(2, modelLogin.getSenha());
			preparedStatement.setString(3, modelLogin.getNome());
			preparedStatement.setString(4, modelLogin.getEmail());
			preparedStatement.executeUpdate();
			connection.commit();
		}
		return this.consultaUsuario(modelLogin.getLogin());
	}

	public List<ModelLogin> consultaUsuarioList() throws SQLException {
		List<ModelLogin> lista = new ArrayList<ModelLogin>();
		String sql = "select * from usuario";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultado = preparedStatement.executeQuery();

		while (resultado.next()) {
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setLogin(resultado.getString("login"));
			lista.add(modelLogin);
		}

		return lista;
	}

	public List<ModelLogin> consultaUsuarioList(String nome) throws SQLException {
		List<ModelLogin> lista = new ArrayList<ModelLogin>(); 
		String sql = "select * from usuario where nome ilike '%" + nome + "%'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultado = preparedStatement.executeQuery();

		while (resultado.next()) {
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setLogin(resultado.getString("login"));
			lista.add(modelLogin);
		}

		return lista;
	}

	public ModelLogin consultaUsuario(String login) throws SQLException {

		ModelLogin modelLogin = new ModelLogin();
		String sql = "select * from usuario where upper(login) = upper('" + login + "')";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultado = preparedStatement.executeQuery();

		while (resultado.next()) {
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setSenha(resultado.getString("senha"));
		}
		return modelLogin;
	}

	public ModelLogin consultaUsuarioId(String id) throws SQLException {

		ModelLogin modelLogin = new ModelLogin();
		String sql = "select * from usuario where id = " + id;
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultado = preparedStatement.executeQuery();

		while (resultado.next()) {
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setSenha(resultado.getString("senha"));
		}
		return modelLogin;
	}

	public boolean verificarLogin(String login) throws SQLException {
		String sql = "select count(*) > 0 as existe from usuario where upper(login) = upper('" + login + "')";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultado = preparedStatement.executeQuery();
		resultado.next();
		return resultado.getBoolean("existe");
	}

	public void deletarUsuario(String idUser) throws SQLException {
		String sql = "DELETE FROM usuario WHERE id = ? ;";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setLong(1, Long.parseLong(idUser));
		preparedStatement.executeUpdate();
		connection.commit();
	}
}
