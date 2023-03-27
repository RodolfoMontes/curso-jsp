package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOLoginRepository {
	
	private Connection connection;
	public DAOLoginRepository() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public boolean validarAutenticacao(ModelLogin modelLogin) throws Exception {
		String sql = "select * from usuario where upper(login) = upper(?) and upper(senha) = upper(?)";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, modelLogin.getLogin());
		preparedStatement.setString(2, modelLogin.getSenha());
		ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) {
			return true;
		}
		return false;
	}
}
