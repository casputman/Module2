package searches;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import core.User;

public class CalorieGoal extends core.MyServlet {
	private static final long serialVersionUID = 1L;
	private double totalCalorieIntake;
	private double totalCalorieBurned;

	public double getIntakeCalorie(int iduser) {
		PreparedStatement ps;
		try {

			ps = getConnection()
					.prepareStatement(
							"SELECT idfood, amount "
									+ "FROM uber.intake "
									+ "WHERE user_iduser = ? "
									+ "AND intaketime::timestamp::date = current_date ");
			ps.setInt(1, iduser);
			ResultSet rs = ps.executeQuery();
			PreparedStatement gs;

			while (rs.next()) {
				gs = getConnection().prepareStatement(
						"SELECT calorie, name " + "FROM uber.stdfood "
								+ "WHERE idfood = ?");

				gs.setInt(1, rs.getInt(1));
				int amount = rs.getInt(2);
				ResultSet hs = gs.executeQuery();
				while (hs.next()) {
					System.out.println(hs.getString(2));
					totalCalorieIntake = totalCalorieIntake + (hs.getInt(1) * amount) ;
				}
				gs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("totalCalorieIntake: " + totalCalorieIntake);
		return totalCalorieIntake;
	}

	public double getBurnedCalorie(int iduser) {
		PreparedStatement ps;
		DetermineActivityColumn activitykg = new DetermineActivityColumn();

		try {

			ps = getConnection().prepareStatement(
					"SELECT activities_name " + "FROM    uber.usage "
							+ "WHERE user_iduser = ? "
							+ "AND usagedate = current_date");
			ps.setInt(1, iduser);
			ResultSet rs2 = ps.executeQuery();
			PreparedStatement ks2;
			int activityColumn = activitykg.determineActivityColumn(iduser);
			String column = "kg" + Integer.toString(activityColumn);
			System.out.println("column: " + column);
			while (rs2.next()) {
				ks2 = getConnection().prepareStatement(
						"SELECT ?, name " + "FROM uber.activities "
								+ "WHERE name = ?");

				ks2.setString(1, column);
				ks2.setString(2, rs2.getString(1));
				ResultSet rs3 = ks2.executeQuery();
				System.out.println("start outputting activity");
				while (rs3.next()) {
					totalCalorieBurned = totalCalorieBurned + rs3.getDouble(1);
					String name = rs3.getString(2);
					System.out.println("activity: " + name);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("totalCalorieBurned: " + totalCalorieBurned);
		return totalCalorieBurned;
	}

	public double balance(int iduser) {
		PreparedStatement ps;
		double length = 0;
		int age = 0;
		String gender = null;
		double weight = 0;
		double BMR = 0;
		try {
			ps = getConnection()
					.prepareStatement(
							"SELECT u.length, u.age, u.gender, w.weight "
									+ "FROM uber.user u, uber.weight w "
									+ "WHERE u.iduser = ?"
									+ "AND w.weightdate = ( SELECT MAX(w.weightdate) FROM uber.weight w, uber.user u WHERE w.user_IDuser = ?)");
			ps.setInt(1, iduser);
			ps.setInt(2, iduser);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				length = rs.getDouble(1);
				age = rs.getInt(2);
				gender = rs.getString(3);
				weight = rs.getDouble(4);
			}
			if (gender != null && age != 0 && length != 0 && weight != 0) {
				if (gender == "m") {
					BMR = 88.362 + (13.397 * weight) + (4.799 * length)
							- (5.677 * age);
				} else {
					BMR = 447.593 + (9.247 * weight) + (3.098 * length)
							- (4.330 * age);
				}
			} else {
				System.out.println("a field is empty");
				return -1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		double balance = getIntakeCalorie(iduser) - getBurnedCalorie(iduser);
		double togoal = balance - BMR;
		GoalShow goal = new GoalShow();
		double goaldouble = goal.calculateGoal(iduser);
		double remaining = togoal - goaldouble;
		System.out.println("balance: " + balance);
		System.out.println("BMR: " + BMR);
		System.out.println("togoal: " + togoal);
		System.out.println("remaining: " + remaining);
		return remaining;
	}

}
