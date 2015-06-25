package test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RowCounter extends core.MyServlet {

	private static final long serialVersionUID = 1L;

	public int countRow(String input) {
		int size = 0;
		PreparedStatement ps;
		
		try {
			//vulnerable for SQL injections, but only called for by the test classes,
			//setString does not accept a parameter in this instance.
			ps = getConnection().prepareStatement("SELECT * FROM uber." + input);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				size += 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return size;
	}
}
