package com.gestion.bdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Select_Methods {
	
	private static void affichageResulSet(ResultSet rs) throws SQLException {
		ResultSetMetaData metaData = rs.getMetaData();
		while (rs.next()) {
			String res = "" ;
			for (int i = 1 ; i <= metaData.getColumnCount() ; i++) {
				res += (rs.getString(i) + "\t") ;
			}
			System.out.println(res);
		}
		System.out.println("--------------------");
	}
	
	public static void sqlSelectQuery(String query) {
		Connection connexion = Connection_methods.connectionDatabase() ;
		for (int i = 0 ; i < query.length() ; i++) {
			if (query.charAt(i) == '\'') {
				if (i > 0 && query.charAt(i-1) != '\\') {
					query.replace("\'", "\\'") ;
				}
			}
		}
		try {
			Statement statement = connexion.createStatement();
			statement = connexion.createStatement();
	        ResultSet rs = statement.executeQuery(query);
	        affichageResulSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        Connection_methods.connectionClose(connexion);
	}

}
