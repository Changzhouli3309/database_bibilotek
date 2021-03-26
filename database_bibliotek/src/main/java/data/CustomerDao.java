package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDao {
	public String findBookByTitel(String s) {
		if (nullCheck(s) || s.equals("")) {
			return null;
		}
		if(s.toLowerCase().equals("all")) {
			s="";
		}
		try (Connection con = Database.getConnection();
				PreparedStatement ps = con.prepareStatement("SELECT * FROM books WHERE Title LIKE ?");
				ResultSet rs = getStatement(ps, s).executeQuery();) {
			String re = "";
			while (rs.next()) {
				boolean borrowed = checkBook(rs.getString(1));
				String bor = borrowed ? "borrowed" : "In stock";
				re += rs.getString(1) + ", " + rs.getString(2) + ", " + rs.getString(3) + ", " + rs.getString(4) + ", "
						+ bor + "\n";
			}

			return re.equals("") ? null : re;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private boolean checkBook(String s) {
		try (Connection con = Database.getConnection();
				PreparedStatement ps = con.prepareStatement("SELECT Name FROM borrower WHERE borrowedBooks LIKE ?");
				ResultSet rs = getStatement(ps, "%" + s).executeQuery();) {

			return rs.next();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public String findMagazineByTitel(String s) {
		if (nullCheck(s) || s.equals("")) {
			return null;
		}
		if(s.toLowerCase().equals("all")) {
			s="";
		}
		try (Connection con = Database.getConnection();
				PreparedStatement ps = con.prepareStatement("SELECT * FROM magazines WHERE Title LIKE ?");
				ResultSet rs = getStatement(ps, s).executeQuery();) {
			String re = "";
			while (rs.next()) {
				re += rs.getString(2) + ", " + rs.getString(3) + ", " + rs.getString(4) + "\n";
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

	private boolean nullCheck(Object... objects) {
		for (Object obj : objects) {
			if (obj == null) {
				return true;
			}
		}
		return false;
	}

}
