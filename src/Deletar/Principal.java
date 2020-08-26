package Deletar;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import db.DB;
import db.DbIntegridadeException;

public class Principal {

	public static void main(String args[]) {
		
	 Connection conexao = null;
	 PreparedStatement st = null;
	
	 
	 try {
		 conexao  = DB.getConexao();
		 st = conexao.prepareStatement("DELETE FROM seller "
				 +"WHERE "
				 +"Id = ?");
		
		 
		 st.setInt(1,9);
		 
		 int linhas = st.executeUpdate();
		 
		 System.out.println("feito "+linhas);
		 
		
		 
		 
		 
	 }
	 catch(SQLException e) {
		 throw new DbIntegridadeException(e.getMessage());
	 }
	 
	 finally {
		 DB.fecharConexao();
		 DB.fecharStatement(st);
	 }
	
	 

	 
	}

}