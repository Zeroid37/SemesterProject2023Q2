package gui;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import controller.LoginController;
import database.DBConnection;

public class tui {
	public static void main(String[] args) {
		
		try {
			DBConnection dbc = DBConnection.getInstance();
			Connection con = dbc.getConnection();
			
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("select * from Apartment");
			
			while(rs.next()) {
				System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
			}
		} catch (Exception e) {
			
		}
	}
}
