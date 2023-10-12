package connection;

import java.sql.Connection;
import java.sql.DriverManager;

import org.postgresql.Driver;

public class SingleConnectionBanco {

	// Conexao com o banco postgres
	private static String banco = "jdbc:postgresql://localhost:5432/curso-jsp?autoReconnect=true";
	// (autoReconnect=true) - caso a conexao com o banco cair, ele ira recuperar
	// novamente
	private static String user = "postgres";
	private static String password = "V@ldir";
	private static Connection connection = null;

	public SingleConnectionBanco() {
		conectar();// Quando tiver uma instancia vai conectar
	}

	// bloco estatico para quando for chamar de QUALQUER LUGAR, ele vai estar
	// disponivel para conectar
	static {
		conectar();
	}

	
	//TODO VERIFICAR DEPOIS O CLASSPATH DO DRIVER DO POSTGRESQL
	private static void conectar() {
		try {
			if (connection == null) {
				Class.forName("org.postgresql.Driver");// carrega o driver de conexao com o banco
				
				DriverManager.registerDriver(new org.postgresql.Driver());//Tive que fazer esse comando devido ao erro de ClassNotFoundException
				connection = DriverManager.getConnection(banco, user, password);
				connection.setAutoCommit(false);// para nao efetuar alteracoes no banco sem nossa permissao
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// retornar a conexao existente
	public static Connection getConnection() {
		return connection;
	}
}
