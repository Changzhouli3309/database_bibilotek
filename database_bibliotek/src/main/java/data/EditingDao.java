package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Employee;

public class EditingDao {
	public Employee getEmployeeById(int id) {
		if (nullCheck(id)) {
			return null;
		}
		try (Connection con = Database.getConnection();
				PreparedStatement ps = con.prepareStatement("SELECT * FROM employees WHERE id = ?");
				ResultSet rs = getStatement(ps, id).executeQuery();) {

			while (rs.next()) {
				return new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5),
						rs.getInt(6), rs.getInt(7), rs.getInt(8));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean uppdateEmploee(Employee emp) {
		if (nullCheck(emp)) {
			return false;
		}
		try (Connection con = Database.getConnection();
				PreparedStatement ps = con.prepareStatement(
						"UPDATE employees SET name = ?, adress = ?, phoneNumber1 = ?, phoneNumber2 = ?, phoneNumber3 = ?, salary = ?, holidaysLeft = ? WHERE id = ?");) {
			getStatement(ps, emp).executeUpdate();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	private PreparedStatement getStatement(PreparedStatement ps, int id) throws SQLException {
		ps.setInt(1, id);
		return ps;
	}

	private PreparedStatement getStatement(PreparedStatement ps, Employee emp) throws SQLException {
		ps.setString(1, emp.getName());
		ps.setString(2, emp.getAdress());
		ps.setInt(3, emp.getTel1());
		ps.setInt(4, emp.getTel2());
		ps.setInt(5, emp.getTel3());
		ps.setInt(6, emp.getSalary());
		ps.setInt(7, emp.getHolidayLeft());
		ps.setInt(8, emp.getId());
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
