package Controle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Modelo.FuncionariosModelo;

public class FuncionariosControle {
		public boolean insertFuncionarios(FuncionariosModelo funcionarios) {
			boolean retorno = false;
			try {
				Conexao com = new Conexao();
				PreparedStatement ps = com.getCom().prepareStatement("INSERT INTO funcionario(nome,email,endereco,telefone) VALUES(?,?,?,?);");
				ps.setString(1, funcionarios.getNome());
				ps.setString(2, funcionarios.getEmail());
				ps.setString(3, funcionarios.getEndereco());
				ps.setString(4, funcionarios.getTelefone());
				if(!ps.execute()) {
					retorno = true;
				}
				com.FecharConexao();
			}catch(SQLException e) {
				System.out.println("Erro de PDO: "+e.getMessage());
			}catch(Exception e) {
				System.out.println("Erro geral: "+e.getMessage());
			}
			
			return retorno;
		}
		public boolean consultarEmail(String email){
			boolean retorno = false;
			try {
				Conexao com = new Conexao();
				PreparedStatement ps = com.getCom().prepareStatement("SELECT * FROM funcionario WHERE email=?;");
				ps.setString(1, email);
				ResultSet rs = ps.executeQuery();
				 if(rs.next()){ 
					if(rs.getString("email").equals(email)) { 
						retorno = true;
					}
				 } else {   
				    retorno = false;
				 }
			}catch(SQLException e) {
				System.out.println("Erro de PDO: "+e.getMessage());
			}catch(Exception e) {
				System.out.println("Erro geral: "+e.getMessage());
			}
			return retorno;
		}
		public ArrayList<FuncionariosModelo> consultarFuncionarios(){
			ArrayList<FuncionariosModelo> lista = null;
			try {
				Conexao com = new Conexao();
				PreparedStatement ps = com.getCom().prepareStatement("SELECT*FROM funcionario");
				ResultSet rs = ps.executeQuery();
				if(rs != null) {
						lista = new ArrayList<FuncionariosModelo>();
						while(rs.next()) {
							FuncionariosModelo funci = new FuncionariosModelo();
							funci.setId(rs.getInt("id"));
							funci.setNome(rs.getString("nome"));
							funci.setEmail(rs.getString("email"));
							funci.setEndereco(rs.getString("endereco"));
							funci.setTelefone(rs.getString("telefone"));
							lista.add(funci);
						}
				}
				com.FecharConexao();
			}catch(SQLException e) {
				System.out.println("Erro de SQL: " + e.getMessage());
			}
			return lista;
		}

		
		public boolean updateFunc(FuncionariosModelo funcionario) {
			boolean retorno = false;
			try {
				Conexao com = new Conexao();
				PreparedStatement ps = com.getCom().prepareStatement("UPDATE funcionario SET nome=?, email=?, endereco=?, telefone=? WHERE id=?;");
				ps.setString(1, funcionario.getNome());
				ps.setString(2, funcionario.getEmail());
				ps.setString(3, funcionario.getEndereco());
				ps.setString(4, funcionario.getTelefone());
				ps.setInt(5, funcionario.getId());
				if(!ps.execute()) {
					retorno = true;
				}
				com.FecharConexao();
			}catch(SQLException e) {
				System.out.println("Erro de SQL:"+e.getMessage());
			}catch(Exception e) {
				System.out.println("Erro geral:"+e.getMessage());
			}
			return retorno;
		}

		public boolean deletarFuncionario(int Id) {
			boolean retorno = false;
			try {
				Conexao com = new Conexao();
				PreparedStatement ps = com.getCom().prepareStatement("DELETE FROM funcionario WHERE id=?");
				ps.setInt(1, Id);
				if(!ps.execute()) {
					retorno = true;
				}
				com.FecharConexao();
			}catch(SQLException e) {
				System.out.println("Erro de SQL:"+e.getMessage());
			}catch(Exception e) {
				System.out.println("Erro geral:"+e.getMessage());
			}
			return retorno;
		}
		public boolean deletarFuncionarios(String email) {
			boolean retorno = false;
			try {
				Conexao com = new Conexao();
				PreparedStatement ps = com.getCom().prepareStatement("DELETE FROM funcionario WHERE email=?");
				ps.setString(1, email);
				if(!ps.execute()) {
					retorno = true;
				}
				com.FecharConexao();
			}catch(SQLException e) {
				System.out.println("Erro de SQL:"+e.getMessage());
			}catch(Exception e) {
				System.out.println("Erro geral:"+e.getMessage());
			}
			return retorno;
		}

}
