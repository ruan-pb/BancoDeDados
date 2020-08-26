package Transacao;

import java.sql.Connection;




import java.sql.SQLException;
import java.sql.Statement;
import db.DB;
import db.DbIntegridadeException;

public class Principal {
	
	public static void main(String args[]) {
		
	 Connection conexao = null; 
	 Statement st = null;
	 
	 try {
		 conexao  = DB.getConexao();
		 
		 //01
		 conexao.setAutoCommit(false);
		 
		 st = conexao.createStatement();
		 
		 int linhas01 =st.executeUpdate("UPDATE seller SET BaseSalary = 2900 WHERE DepartmentID = 1");
		 
		 
		 // nesse exemplo mostra que apenas a linha01 vai ser alterada no banco de dados, por que na linha de baixa vai acontecer um erro
		 //fazendo com que a operação acabe
		
		 /*int x = 1;
		 
		 if(x < 2) {
			 throw new SQLException("Erro ");
		 }
		*/		 
		 
		 int linhas02 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");
		 
		 
		 // depois que eu terminar as operações, para confirma que agora sim a minha transação terminou
		 //02
		 conexao.commit();
		 
		 // desse modo todas as pessoas do departamento 1 alteraram o salario e todas as pessoas do departamento 2 também
		 //as linhas abaixo vai mostra a quantidade de pessoas que teve salarios alterados de cada departamento
		 System.out.println("linha 01 "+linhas01);
		 
		 System.out.println("linhas 02 "+linhas02);
		 
		// System.out.println("total "+linhas02+linhas01);
		 
		 
		 
	 }
	 catch(SQLException e) {
		 try {
			 	//03
			 	conexao.rollback();
			 	throw new DbIntegridadeException("Transação recusada e voltou para o começo: CAUSA: "+e.getMessage());
			 	
		 }catch(SQLException e1) {
			 throw new DbIntegridadeException("Erro inesperado AGUARDE: CAUSA "+e1.getMessage());
		 }
	 
	 finally {
		 DB.fecharConexao();
		 DB.fecharStatement(st);
	 }
	}
	}
}
	
	
