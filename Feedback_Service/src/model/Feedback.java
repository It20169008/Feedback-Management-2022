package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Feedback {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/customer_management","root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertFeedback(String CostomerName, String Branch, String Review, String Rate) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into feedback(`FEEDBACKID`,`CostomerName`,`Branch`,`Review`,`Rate`)" + " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, CostomerName);
			preparedStmt.setString(3, Branch);
			preparedStmt.setString(4, Review);
			preparedStmt.setString(5, Rate);

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the Feedback.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readFeedback() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Feedback ID</th><th>Customer Name</th><th>Branch</th><th>Review</th><th>Rate</th></tr>";
			String query = "select * from feedback";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String FEEDBACKID = Integer.toString(rs.getInt("FEEDBACKID"));
				String CostomerName = rs.getString("CostomerName");
				String Branch = rs.getString("Branch");
				String Review = rs.getString("Review");
				String Rate = rs.getString("Rate");

				output += "<tr><td>" + FEEDBACKID + "</td>";
				output += "<td>" + CostomerName + "</td>";
				output += "<td>" + Branch + "</td>";
				output += "<td>" + Review + "</td>";
				output += "<td>" + Rate + "</td>";
			}
			con.close();

			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Feedback.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateFeedback(String FEEDBACKID, String CostomerName, String Branch, String Review, String Rate) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE feedback SET CostomerName=?,Branch=?,Review=?,Rate=? WHERE FEEDBACKID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, CostomerName);
			preparedStmt.setString(2, Branch);
			preparedStmt.setString(3, Review);
			preparedStmt.setString(4, Rate);
			preparedStmt.setInt(5, Integer.parseInt(FEEDBACKID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the Feedback.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteFeedback(String FEEDBACKID) {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from feedback where FEEDBACKID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(FEEDBACKID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the Feedback.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
