package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LibrarianDao {
	public String findCustomerByName(String s) {
		if (nullCheck(s) || s.equals("")) {
			return null;
		}
		if (s.toLowerCase().equals("all")) {
			s = "";
		}
		try (Connection con = Database.getConnection();
				PreparedStatement ps = con.prepareStatement("SELECT * FROM borrower WHERE Name LIKE ?");
				ResultSet rs = getStatement(ps, s).executeQuery();) {
			String re = "";
			while (rs.next()) {
				re += rs.getString(1) + ", " + rs.getString(2) + ", " + rs.getString(3) + ", " + rs.getString(4) + ", "
						+ rs.getString(5) + "\n";
			}

			return re.equals("") ? null : re;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String findCustomerByBook(String s) {
		if (nullCheck(s) || s.equals("")) {
			return null;
		}
		if (s.toLowerCase().equals("all")) {
			s = "";
		}
		try (Connection con = Database.getConnection();
				PreparedStatement ps = con.prepareStatement("SELECT * FROM borrower WHERE borrowedBooks LIKE ?");
				ResultSet rs = getStatement(ps, "%" + s).executeQuery();) {

			String re = "";
			while (rs.next()) {
				re += rs.getString(1) + ", " + rs.getString(2) + ", " + rs.getString(3) + ", " + rs.getString(4) + ", "
						+ rs.getString(5) + "\n";
			}

			return re.equals("") ? null : re;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	private PreparedStatement getStatement(PreparedStatement ps, String s) throws SQLException {
		ps.setString(1, s + "%");
		return ps;
	}

	public static boolean nullCheck(Object... objects) {
		for (Object obj : objects) {
			if (obj == null) {
				return true;
			}
		}
		return false;
	}
}
