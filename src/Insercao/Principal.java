package Insercao;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import db.DB;

public class Principal {
	
	public static void main(String args[]) {
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Scanner leitor = new Scanner(System.in);
		
		Connection conexao = null;
		
		PreparedStatement ps = null;
		
		//Date data = new Date(1993,04,25);
		
		try {
			conexao = DB.getConexao();
			
			ps = conexao.prepareStatement("INSERT INTO seller "
			+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
			+"VALUES"
			+"(?,?,?,?,?)"
			//Statement.RETURN_GENERATED_KEYS retorna o Id Acrescentado
			,Statement.RETURN_GENERATED_KEYS);
		
			//primeiro eu coloco o numero correspondente a interrogação "?"e depois o que vem no valor
			
			/*String nome = leitor.nextLine();
			String email = leitor.nextLine();
			double salario = leitor.nextDouble();
			int departamento = leitor.nextInt();
			*/
			
			
			
			
			ps.setString(1, "valdir");
			ps.setString(2, "valdirCruz@gmail.com");
			//ps.setDate(3, data);
			ps.setDate(3, new java.sql.Date(format.parse("03/18/25").getTime()));
			ps.setDouble(4,9800);
			ps.setInt(5, 1);
			
			
			//ps = conexao.prepareStatement("delete from seller where id=14");
			
			// comando para inserir dois novos departamentos na coluna Name com os valores moda e ofiicina
			//ps = conexao.prepareStatement("insert into department (Name) values ('Moda'),('Oficina')",Statement.RETURN_GENERATED_KEYS);
		
			int linhasAlteradas = ps.executeUpdate();
			
			//System.out.println("Feito a quantidade de linhas alterads foi: "+linhasAlteradas);
			
			// esse comando junto com o Statement.RETURN_GENERATED_KEYS); serve para dizer qual foi o Id de nova pessoa Inserida
			if(linhasAlteradas > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				while(rs.next()) {
					// colocou o numero 1 pq vai pegar apenas informações da coluna posicionada na 1
					int id = rs.getInt(1);
					System.out.println("Feito! o Id = "+id);
					
				}
 		}
			else {
				System.out.println("Não houve linhas alteradas");
			}
			
		
		
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(ParseException e) {
			e.printStackTrace();
		}
		finally {
			DB.fecharStatement(ps);
			DB.fecharConexao();
			leitor.close();
		}
		
		
	}

}