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

	Connection connection;

	public DAOUsuarioRepository() {
		connection = SingleConnectionBanco.getConnection();
	}

	public ModelLogin gravarUsuario(ModelLogin usuario) throws Exception {
		
		if(usuario.isNovo()) { //Grava o usuario
			
			String sql = "insert into model_login (nome, email, login, senha) values (?, ?, ?, ?)";
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getEmail());
			stmt.setString(3, usuario.getLogin());
			stmt.setString(4, usuario.getSenha());

			stmt.execute();
			connection.commit();
			
		} else {
			
			String sql = "update model_login set nome = ?, email = ?, login = ?, senha = ? where id = "+ usuario.getId()+" ";
			
			PreparedStatement preparaSql = connection.prepareStatement(sql);
			preparaSql.setString(1, usuario.getNome());
			preparaSql.setString(2, usuario.getEmail());
			preparaSql.setString(3, usuario.getLogin());
			preparaSql.setString(4, usuario.getSenha());
			
			preparaSql.executeUpdate();
			connection.commit();
		}

		return this.consultarUsuarioLogin(usuario.getLogin());
	}

	public ModelLogin consultarUsuarioLogin(String login) throws Exception {

		ModelLogin modelLogin = new ModelLogin();

		String sql = "select * from model_login where login = '"+login+"'";
		PreparedStatement stmt = connection.prepareStatement(sql);

		ResultSet resultado = stmt.executeQuery();

		if (resultado.next()) {
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setSenha(resultado.getString("senha"));
		}

		return modelLogin;
	}
	
	//TODO VERIFICAR O PORQUE NAO APARECE A RELACAO DOS USUARIOS NA TELA
	public List<ModelLogin> consultaUsuarioList(String nome) throws SQLException{
		
		List<ModelLogin> retorno = new ArrayList<>();
		
		String sql = "select * from model_login where upper(nome) like upper(?)";
		PreparedStatement preparaSql = connection.prepareStatement(sql);
		preparaSql.setString(1, "%" + nome + "%");
		
		ResultSet resultado = preparaSql.executeQuery();
		
		while (resultado.next()) {
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setLogin(resultado.getString("login"));
			
			retorno.add(modelLogin);
		}
		return retorno;
	}
	
	public boolean validarLogin(String login) throws Exception {
		
		String sql = "select count (1) > 0 as existe from model_login where upper (login) = upper ('"+login+"')";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet resultado = stmt.executeQuery();
		
		if(resultado.next()) {
			return resultado.getBoolean("existe");
		}
		return false;
	}
	
	public void deletarUsuario (String id) throws SQLException {
		String sql = "delete from model_login where id = ?";
		PreparedStatement prepareSQL = connection.prepareStatement(sql);
		prepareSQL.setLong(1, Long.parseLong(id));
		prepareSQL.executeUpdate();
		connection.commit();
	}

	public ModelLogin consultarUsuarioID(String id) throws SQLException {
		
		ModelLogin modelLogin = new ModelLogin();
		
		String sql = "select * from model_login where id = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setLong(1, Long.parseLong(id));//convertendo para string
		
		ResultSet resultado = stmt.executeQuery();
		
		if (resultado.next()) { //se houver proximo
			
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setId(resultado.getLong("id"));
		}
		return modelLogin;
	}
	
	public List<ModelLogin> consultaUsuarios() throws SQLException{

		List<ModelLogin> modelLogins = new ArrayList<>();
		
		String sql = "select * from model_login";
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		ResultSet resultado = stmt.executeQuery();
		
		while(resultado.next()) {
			ModelLogin modelLogin = new ModelLogin();
			
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setId(resultado.getLong("id"));
			
			modelLogins.add(modelLogin);
		}
		return modelLogins;
	}
}
