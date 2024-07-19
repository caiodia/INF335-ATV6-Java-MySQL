package br.unicamp.ic.INF335;

import java.sql.Connection;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ExemploJDBC loja = new ExemploJDBC();
		Connection conn = loja.conectar("root", "INF335UNICAMP", "jdbc:mysql://localhost/loja");
	
		if (conn != null) {
			System.out.println("Lista Original de Produtos");
			//Lista os produtos da loja
			loja.listaProdutos(conn);
		}
		
		//Insere novo produto
		loja.insereProduto(conn, "7", "Prod7", "Bla Bla", "500.0", "Bla Bla");
		
		System.out.println("Lista com novo Produto");
		//Lista os produtos da loja
		loja.listaProdutos(conn);
		
		//Altera valor do produto
		loja.alteraValorProduto(conn, "7", "400");
		
		System.out.println("Lista com valor do Produto alterado");
		//Lista os produtos da loja
		loja.listaProdutos(conn);
		
		System.out.println("Apaga Produto Numero 7");
		//Apaga produto
		loja.apagaProduto(conn, "7");
		
		System.out.println("Volta a Lista Original de Produtos");
		//Lista os produtos da loja
		loja.listaProdutos(conn);
		
		loja.close(conn);
	}
}
