package Selecao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Principal {
	
	public static void main(String args[]) {
		
		
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		
		// DB é uma classe do banco de dados, o getConexao() e um metodo statico porriso pode ser chamado de outra classe e de outro pacote
		//sem ser instanciado
		
		try {
			conn = DB.getConexao();
			
			//Instancie o Statement com esse comando
			// para ele poder executar a função de busca no banco de dados
			st = conn.createStatement();
			
			
			//executeUpdate() = executa uma atualização/inserção/delete no banco
			//executeQuery() = executa uma pesquisa no banco
			//vai selecionar todos os dados de departamento, e vai armazenar na variavel "rs" de ResultSet
			rs = st.executeQuery("select * from seller");
			
			//vai ler no banco de dados enquanto tiver elemento na tabela
			while(rs.next()) {
				
				//vai pegar um inteiro aonde na coluna estiver "ID" e vai pegar um String com nomes quando a coluna se chamar name
				System.out.println(rs.getInt("Id")+ " , " +rs.getString("Name") + " , "+rs.getString("Email"));
				
				
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.fecharConexao();
			DB.fecharStatement(st);
			DB.fecharResultSet(rs);
		}
		
		
		}
		
	}


