package br.unicamp.ic.INF335;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ExemploJDBC {

	@SuppressWarnings("unused")
	protected static Connection conectar(String usuario, String senha, String url) {
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, usuario, senha);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Erro de Conexão : " + e);
			e.printStackTrace();
		}
		
		return conn;
	}
	
	protected void close(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void listaProdutos(Connection conn) {
		 //Cria statement SQL
		PreparedStatement stmt;
		
		try {
			stmt = (PreparedStatement) conn.prepareStatement("select * from Produto;");
			
			// Executa select e coloca resultados em uma variavel java em memória
			ResultSet rs = stmt.executeQuery();
			
			// Pega resultado e imprime os Campos
			while (rs.next()) {
				String idProduto = rs.getString("idProduto");
				String nome = rs.getString("nome");
				String descricao = rs.getString("descricao");
				String valor = rs.getString("valor");
				String estado = rs.getString("estado");
				System.out.println(idProduto + " -- " + nome + " -- " + descricao + " -- " + valor + " -- " + estado);
			}
					
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Erro ao executar select :" + e);
			e.printStackTrace();
		}
	}
	
	public void insereProduto(Connection conn, String idProduto, String nome, String descricao, String valor, String estado) {
		// Cria statement SQL
		Statement stmt;
		
		try {
			stmt = (Statement) conn.createStatement();
			String insere = "insert into Produto VALUES ('"
					+ idProduto + "','"
					+ nome + "','"
					+ descricao + "','"
					+ valor + "','"
					+ estado + "');";
			
			stmt.executeUpdate(insere);
			System.out.println("produto inserido " + insere);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void alteraValorProduto(Connection conn, String idProduto, String valor) {
		// Cria statement SQL
		Statement stmt;
		
		try {
			stmt = (Statement) conn.createStatement();
			String atualiza = "update Produto set valor = '"
					+ valor + "' where idProduto = '"
					+ idProduto + "';";
			
			stmt.executeUpdate(atualiza);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void apagaProduto(Connection conn, String idProduto) {
		//Cria statement SQL
		Statement stmt;
		
		try {
			stmt = (Statement) conn.createStatement();
			
			String delete = "delete from Produto where idProduto = '"
					+ idProduto + "';";
			
			stmt.executeUpdate(delete);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}	
}
