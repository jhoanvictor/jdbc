package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement st = null;

		try {
			conn = DB.getConnection();

			st = conn.prepareStatement("INSERT INTO seller " + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+ "VALUES " + "(?,?,?,?,?)");

			st.setString(1, "Carl Purple");
			st.setString(2, "carl@gmail.com");

			/*
			 * Utilizar o java.sql.Date para instanciar datas que v�o para o jdbc e
			 * inseridas no banco ao passar dentro da instancia um simpleDateFormta .parse,
			 * usar o m�todo getTime
			 */
			st.setDate(3, new java.sql.Date(sdf.parse("22/04/1985").getTime()));
			st.setDouble(4, 2135.0);
			st.setInt(5, 4);

			// opera��o que ir� alterar dados
			int rowsAffected = st.executeUpdate();

			System.out.println("Done! Rows Affected: " + rowsAffected);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}

	}

}
