package Atualizacao;

import java.sql.Connection;


import java.sql.PreparedStatement;

import java.sql.SQLException;

import db.DB;

public class Principal {
	
	public static void main(String args[]) {
		
		Connection conexao = null;
		PreparedStatement st = null;
		
		try {
			conexao = DB.getConexao();
			
			// atualize o banco de dados do VENDEDOR(seller) alterando a base salarial no Department que o usuario colocar
			st = conexao.prepareStatement("UPDATE seller"
					+" SET BaseSalary = BaseSalary + ? "
					+"WHERE "
					+"(DepartmentId = ?)");
			
			st.setDouble(1, 500);
			st.setInt(2, 1);
			
			int linhasAlteradas = st.executeUpdate();
			
			System.out.println("Linhas alteradas>: "+linhasAlteradas);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.fecharConexao();
			DB.fecharStatement(st);
		}
	}

}