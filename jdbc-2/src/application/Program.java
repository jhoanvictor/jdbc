package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Program {

	public static void main(String[] args) {

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			// criando a conexão com banco de
			conn = DB.getConnection();

			// instanciando o objeto Statment
			st = conn.createStatement();
			
			//realizando consulta sql
			rs = st.executeQuery("SELECT * FROM department;");

			while (rs.next()) {
				System.out.println(rs.getInt("Id") + " - " + rs.getString("Name"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
			DB.closeConnection();
		}

	}

}
