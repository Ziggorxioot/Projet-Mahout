package com.gestion.bdd;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Create_Structure {
	
	public static void createTable(String tableName) {
		Connection bdd = Connection_methods.connectionDatabase();
		if (bdd != null) {
			try {
				Statement statement = bdd.createStatement();
				String sql = ("create table " + tableName + " (id" + tableName + " int not null primary key)") ;
				statement.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Connexion impossible");
		}
		Connection_methods.connectionClose(bdd);
	}
	
	public static void createAssociativeTable(String tableName, String tableAssociatedOne, String tableAssociatedTwo) {
		Connection bdd = Connection_methods.connectionDatabase();
		if (bdd != null) {
			try {
				Statement statement = bdd.createStatement();
				String idTableOne = ("id" + tableAssociatedOne);
				String idTableTwo = ("id" + tableAssociatedTwo) ;
				String sql = ("create table " + tableName + " ( " + idTableOne + " int not null, "
						+ idTableTwo + " int not null, "
								+ "primary key (" + idTableOne + ", " + idTableTwo + "), "
										+ "foreign key (" + idTableOne + ") references " + 
								tableAssociatedOne + "(" + idTableOne + "), "
										+ "foreign key (" + idTableTwo + ") references " + 
										tableAssociatedTwo + "(" + idTableTwo + ") )") ;
				statement.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Connexion impossible");
		}
		Connection_methods.connectionClose(bdd);
	}
	
	public static void dropTable(String tableName) {
		Connection bdd = Connection_methods.connectionDatabase();
		if (bdd != null) {
			try {
				Statement statement = bdd.createStatement();
				String sql = ("drop table " + tableName);
				statement.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Connexion impossible");
		}
		Connection_methods.connectionClose(bdd);
	}
	
	public static void addColumn(String tableName, String column, String columnType, boolean nullAuthorized) {
		Connection bdd = Connection_methods.connectionDatabase();
		if (bdd != null) {
			try {
				String columnCombo = (column + " " + columnType);
				Statement statement = bdd.createStatement();
				String sql = ("alter table " + tableName + " add " + columnCombo) ;
				if (!nullAuthorized) {
					sql += " not null" ;
				}
				statement.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Connexion impossible");
		}
		Connection_methods.connectionClose(bdd);
	}

	public static void deleteFromTable(String tableName) {
		Connection bdd = Connection_methods.connectionDatabase();
		if (bdd != null) {
			try {
				Statement statement = bdd.createStatement();
				String sql = ("delete from " + tableName) ;
				statement.executeUpdate(sql);
				System.out.println("Contenu de la table " + tableName + " supprimé");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Connexion impossible");
		}
		Connection_methods.connectionClose(bdd);
	}
	
}
