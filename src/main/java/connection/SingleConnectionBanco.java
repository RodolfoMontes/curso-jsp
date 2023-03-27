package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnectionBanco {
	
	private static String banco = "jdbc:postgresql://containers-us-west-127.railway.app:6039/railway";
	private static String user = "postgres";
	private static String senha = "3YP1kWtdGSdmwZNu4pzR";
	private static Connection connection = null;
	
	
	public static Connection getConnection() {
		return connection;
	}
	
	static {
		conectar();
	}
	
	public SingleConnectionBanco() {
		conectar();
	}
	
	private static void conectar() {
		try {
			if (connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(banco, user, senha);
				connection.setAutoCommit(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}