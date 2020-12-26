package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Program2 {

	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement st = null;

		try {
			conn = DB.getConnection();

			st = conn.prepareStatement("INSERT INTO department (Name) VALUES ('D1'),('D2')",
					Statement.RETURN_GENERATED_KEYS);

			// operação que irá alterar dados
			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				// resgatando o id do elemento inserido - pode ser 1 ou mais valores (depende do
				// insert)
				ResultSet rs = st.getGeneratedKeys();
				while (rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Done! Id: " + id);
				}

			} else {
				System.out.println("No rown affected!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}

	}

}
