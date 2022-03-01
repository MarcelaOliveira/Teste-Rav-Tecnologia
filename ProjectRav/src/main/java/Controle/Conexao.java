package Controle;

/*Importações*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*Class de Conexão*/

public class Conexao {
	private Connection com;
	
	public Connection getCom() {
		return com;
	}

	public void setCom(Connection com) {
		this.com = com;
	}
	public void FecharConexao() {
		try {
			this.getCom().close();	
		}catch(SQLException e) {
			System.out.println("Erro PDO:"+e.getMessage());
		}catch(Exception e) {
			System.out.println("Erro geral:"+e.getMessage());
		}
	}
	public Conexao() {
		try {
			String host ="localhost";
			String user = "root";
			String senha = "";
			String bd ="funcionarios";
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.setCom(DriverManager.getConnection("jdbc:mysql://"+host+"/"+bd,user,senha));
			
		}catch(SQLException e) {
			System.out.println("Erro PDO:"+e.getMessage());
		}catch(Exception e) {
			System.out.println("Erro geral:"+e.getMessage());
		}
	}
}
