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

	public ModelLogin gravarUser(ModelLogin modelLogin, Long userLogado) throws SQLException {
		if (modelLogin.isNovo()) {
			String sql = "INSERT INTO usuario(login, senha, nome, email, usuariocadastroid, perfil, sexo, cep, logradouro, complemento, bairro, cidade, uf) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, modelLogin.getLogin());
			preparedStatement.setString(2, modelLogin.getSenha());
			preparedStatement.setString(3, modelLogin.getNome());
			preparedStatement.setString(4, modelLogin.getEmail());
			preparedStatement.setLong(5, userLogado);
			preparedStatement.setString(6, modelLogin.getPerfil());
			preparedStatement.setString(7, modelLogin.getSexo());
			
			preparedStatement.setString(8, modelLogin.getCep());
			preparedStatement.setString(9, modelLogin.getLogradouro());
			preparedStatement.setString(10, modelLogin.getComplemento());
			preparedStatement.setString(11, modelLogin.getBairro());
			preparedStatement.setString(12, modelLogin.getCidade());
			preparedStatement.setString(13, modelLogin.getUf());
			
			preparedStatement.execute();
			connection.commit();
			
			if (modelLogin.getFotousuario() != null && !modelLogin.getFotousuario().isEmpty()) {
				sql = "update usuario set fotouser = ?, extensaousuario = ? where login = ?;";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, modelLogin.getFotousuario());
				preparedStatement.setString(2, modelLogin.getExtensaousuario());
				preparedStatement.setString(3, modelLogin.getLogin());
				preparedStatement.execute();
				connection.commit();
			}
		} else {
			String sql = "UPDATE usuario SET login=?, senha=?, nome=?, email=?, perfil=?, sexo=?, cep=? ,  logradouro=? ,  complemento=? ,  bairro=? ,  cidade=? ,  uf=? WHERE id = " + modelLogin.getId() + ";";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, modelLogin.getLogin());
			preparedStatement.setString(2, modelLogin.getSenha());
			preparedStatement.setString(3, modelLogin.getNome());
			preparedStatement.setString(4, modelLogin.getEmail());
			preparedStatement.setString(5, modelLogin.getPerfil());
			preparedStatement.setString(6, modelLogin.getSexo());
			preparedStatement.setString(7, modelLogin.getCep());
			preparedStatement.setString(8, modelLogin.getLogradouro());
			preparedStatement.setString(9, modelLogin.getComplemento());
			preparedStatement.setString(10, modelLogin.getBairro());
			preparedStatement.setString(11, modelLogin.getCidade());
			preparedStatement.setString(12, modelLogin.getUf());
			
			preparedStatement.executeUpdate();
			connection.commit();
			
			if (modelLogin.getFotousuario() != null && !modelLogin.getFotousuario().isEmpty()) {
				sql = "update usuario set fotouser = ?, extensaousuario = ? where id = ?;";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, modelLogin.getFotousuario());
				preparedStatement.setString(2, modelLogin.getExtensaousuario());
				preparedStatement.setLong(3, modelLogin.getId());
				preparedStatement.execute();
				connection.commit();
			}
		}
		return this.consultaUsuario(modelLogin.getLogin(), userLogado);
	}
	
	
	
	
	public List<ModelLogin> consultaUsuarioListPaginado(Long usuarioLogado, Integer offset) throws SQLException {
		List<ModelLogin> lista = new ArrayList<ModelLogin>();
		String sql = "select * from usuario where useradmin is false and usuariocadastroid = " + usuarioLogado + "order by nome offset "+ offset +" limit 5;";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultado = preparedStatement.executeQuery();

		while (resultado.next()) {
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
			lista.add(modelLogin);
		}

		return lista;
	}
	
    public int totalPagina(Long userLogado) throws SQLException {
    	String sql = "select count(*) as qtd from usuario where usuariocadastroid =" + userLogado;
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultado = preparedStatement.executeQuery();
		resultado.next();
		Double total = resultado.getDouble("qtd");
		Double pagina = total / 5.0;
		Double resto = pagina % 2;
		
		if (resto > 0) {
			pagina++;
		}
		return pagina.intValue();
    }
	
	

	public List<ModelLogin> consultaUsuarioList(Long usuarioLogado) throws SQLException {
		List<ModelLogin> lista = new ArrayList<ModelLogin>();
		String sql = "select * from usuario where useradmin is false and usuariocadastroid = " + usuarioLogado +" limit 5";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultado = preparedStatement.executeQuery();

		while (resultado.next()) {
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
			lista.add(modelLogin);
		}

		return lista;
	}

	public List<ModelLogin> consultaUsuarioList(String nome, Long usuarioLogado) throws SQLException {
		List<ModelLogin> lista = new ArrayList<ModelLogin>();
		String sql = "select * from usuario where nome ilike '%" + nome
				+ "%' and useradmin is false and usuariocadastroid = " + usuarioLogado;
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultado = preparedStatement.executeQuery();

		while (resultado.next()) {
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));

			lista.add(modelLogin);
		}

		return lista;
	}

	public ModelLogin consultaUsuarioLogado(String login) throws SQLException {

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
			modelLogin.setUseradmin(resultado.getBoolean("useradmin"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
			modelLogin.setFotousuario(resultado.getString("fotouser"));
			modelLogin.setCep(resultado.getString("cep"));
			modelLogin.setCidade(resultado.getString("cidade"));
			modelLogin.setBairro(resultado.getString("bairro"));
			modelLogin.setLogradouro(resultado.getString("logradouro"));
			modelLogin.setComplemento(resultado.getString("complemento"));
			modelLogin.setUf(resultado.getString("uf"));



			

		}
		return modelLogin;
	}

	public ModelLogin consultaUsuario(String login) throws SQLException {

		ModelLogin modelLogin = new ModelLogin();
		String sql = "select * from usuario where upper(login) = upper('" + login + "') and useradmin is false";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultado = preparedStatement.executeQuery();

		while (resultado.next()) {
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
			modelLogin.setFotousuario(resultado.getString("fotouser"));
			modelLogin.setCep(resultado.getString("cep"));
			modelLogin.setCidade(resultado.getString("cidade"));
			modelLogin.setBairro(resultado.getString("bairro"));
			modelLogin.setLogradouro(resultado.getString("logradouro"));
			modelLogin.setComplemento(resultado.getString("complemento"));
			modelLogin.setUf(resultado.getString("uf"));
		}
		return modelLogin;
	}

	public ModelLogin consultaUsuario(String login, Long userLogado) throws SQLException {

		ModelLogin modelLogin = new ModelLogin();
		String sql = "select * from usuario where upper(login) = upper('" + login
				+ "') and useradmin is false and usuariocadastroid = " + userLogado;
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultado = preparedStatement.executeQuery();

		while (resultado.next()) {
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
			modelLogin.setFotousuario(resultado.getString("fotouser"));
			modelLogin.setCep(resultado.getString("cep"));
			modelLogin.setCidade(resultado.getString("cidade"));
			modelLogin.setBairro(resultado.getString("bairro"));
			modelLogin.setLogradouro(resultado.getString("logradouro"));
			modelLogin.setComplemento(resultado.getString("complemento"));
			modelLogin.setUf(resultado.getString("uf"));


		}
		return modelLogin;
	}

	public ModelLogin consultaUsuarioId(String id, Long userLogado) throws SQLException {

		ModelLogin modelLogin = new ModelLogin();
		String sql = "select * from usuario where id = " + id + " and useradmin is false and usuariocadastroid = "
				+ userLogado;
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultado = preparedStatement.executeQuery();

		while (resultado.next()) {
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
			modelLogin.setFotousuario(resultado.getString("fotouser"));
			modelLogin.setExtensaousuario(resultado.getString("extensaousuario"));
			modelLogin.setCep(resultado.getString("cep"));
			modelLogin.setCidade(resultado.getString("cidade"));
			modelLogin.setBairro(resultado.getString("bairro"));
			modelLogin.setLogradouro(resultado.getString("logradouro"));
			modelLogin.setComplemento(resultado.getString("complemento"));
			modelLogin.setUf(resultado.getString("uf"));
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
		String sql = "DELETE FROM usuario WHERE id = ? and useradmin is false;";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setLong(1, Long.parseLong(idUser));
		preparedStatement.executeUpdate();
		connection.commit();
	}
}
