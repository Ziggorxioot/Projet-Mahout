package com.gestion.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connection_methods {
	
	private static void chargementDriver() {
		try {
			Class.forName("org.hsqldb.jdbcDriver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static Connection connectionDatabase() {
		chargementDriver();
		Connection connexion = null ;
		try {
			connexion = DriverManager.getConnection("jdbc:hsqldb:file:./bdd/bdd_mahout_ptut", "sa",  "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connexion ;
	}
	
	public static void connectionClose(Connection connexion) {
		if (connexion != null) {
			try {
				Statement statement = connexion.createStatement();
				statement.executeQuery("SHUTDOWN");
				statement.close();
				connexion.close() ;
			} catch (SQLException Ignore) {	}
		}
	}

}
