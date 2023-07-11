package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class minitema {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "exemplu_user",
				"exemplu_user")) {
			if (conn != null) {
				System.out.println("Connection successful");
			}
			/*
			 * insertArticol(conn, 1, "Laptop"); insertArticol(conn, 2, "Telefon");
			 * 
			 * insertMagazin(conn, 1, "Altex"); insertMagazin(conn, 2, "Emag");
			 * 
			 * insertPret(conn, 1, 1, 1000); insertPret(conn, 2, 2, 7000);
			 */
			String query = "SELECT a.name AS nume_articol, s.name AS nume_magazin, p.pret\r\n" + "FROM (\r\n"
					+ "  SELECT id_articol, MIN(pret) AS min_pret\r\n" + "  FROM articol_magazin\r\n"
					+ "  GROUP BY id_articol\r\n" + ") min_preturi\r\n"
					+ "JOIN articol_magazin p ON p.id_articol = min_preturi.id_articol AND p.pret = min_preturi.min_pret\r\n"
					+ "JOIN articol a ON a.id = p.id_articol\r\n" + "JOIN magazin s ON s.id = p.id_magazin";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String numeArt = rs.getString(1);
				String numeMag = rs.getString(2);
				int pret = rs.getInt(3);
				System.out.println("articol: " + numeArt + ", magazin: " + numeMag + ", pret: " + pret + "\n");
			}

		}

	}

	public static void insertArticol(Connection conn, int id, String name) throws SQLException {
		PreparedStatement statement = conn.prepareStatement("insert into articol values (?, ?)");
		statement.setInt(1, id);
		statement.setString(2, name);
		statement.execute();
	}

	public static void insertMagazin(Connection conn, int id, String name) throws SQLException {
		PreparedStatement statement = conn.prepareStatement("insert into magazin values (?, ?)");
		statement.setInt(1, id);
		statement.setString(2, name);
		statement.execute();
	}

	public static void insertPret(Connection conn, int idArt, int idMag, int pret) throws SQLException {
		PreparedStatement statement = conn.prepareStatement("insert into articol_magazin values (?, ?, ?)");
		statement.setInt(1, idArt);
		statement.setInt(2, idMag);
		statement.setInt(3, pret);
		statement.execute();
	}
}
