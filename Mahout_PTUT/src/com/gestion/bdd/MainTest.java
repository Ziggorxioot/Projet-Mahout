package com.gestion.bdd;

import java.sql.Connection;

public class MainTest {

	public static void main(String[] args) {
		
		Connection connexion = Connection_methods.connectionDatabase();
		System.out.println("Vous êtes connecté !");
		System.out.println("--------------------");
		
		/*
		 * Création de la structure effectuée
		 * 
		 * Create_Structure.createTable("Utilisateur");
		 * Create_Structure.createTable("Film");
		 * Create_Structure.createAssociativeTable("Note", "Utilisateur", "Film");
		 * Create_Structure.createTable("Genre");
		 * Create_Structure.createAssociativeTable("AssociationFilmGenre", "Film", "Genre");
		 * Create_Structure.addColumn("Genre", "libelle", "varchar(100)", false);
		 * 
		 */
		
		
		
		/*
		 * Création des colonnes de la table Utilisateur effectuée
		 * 
		 * HashMap<String, String> colonnes = new HashMap<>();
		 * colonnes.put("age", "int") ;
		 * colonnes.put("sexe", "char(1)") ;
		 * colonnes.put("occupation", "varchar(30)") ;
		 * colonnes.put("zipCode", "char(15)") ;
		 * for (String colonne : colonnes.keySet()) {
		 * 		Create_Structure.addColumn("Utilisateur", colonne, colonnes.get(colonne), false);
		 * }
		 * 
		 */
		
		/*
		 * Création des colonnes de la table Note effectuée
		 * 
		 * HashMap<String, String> colonnes = new HashMap<>();
		 * colonnes.put("note", "int") ;
		 * colonnes.put("date", "datetime") ;
		 * for (String colonne : colonnes.keySet()) {
		 * 		Create_Structure.addColumn("Note", colonne, colonnes.get(colonne), false);
		 * }
		 * 
		 */
		
		/*
		 * Création des colonnes de la table Film effectuée
		 * 
		 * HashMap<String, String> colonnes = new HashMap<>();
		 * colonnes.put("titre", "varchar(120)") ;
		 * colonnes.put("dateSortie", "datetime") ;
		 * colonnes.put("lienMovieLens", "varchar(150)") ;
		 * for (String colonne : colonnes.keySet()) {
		 *		Create_Structure.addColumn("Film", colonne, colonnes.get(colonne), false);
		 * }
		 * Create_Structure.addColumn("Film", "noteMoyenne", "float", true) ;
		 * 
		 */
		
		/*
		 * Utilisateurs insérés dans la table
		 * 
		 * Insert_Methods.addUsers(connexion);
		 * 
		 */
		
		/*
		 * Genres insérés dans la table
		 * 
		 * Insert_Methods.addGenres(connexion);
		 * 
		 */
		
		/*
		 * Films insérés dans la table
		 * 
		 * Insert_Methods.addMovies(connexion);
		 * 
		 */
		
		/*
		 * Notes insérées dans la table
		 * 
		 * Insert_Methods.addNotes(connexion);
		 * 
		 */
		
		/*
		 * Associations Film/Genre insérées dans la tables
		 * 
		 * Insert_Methods.addAssociationFilmGenre(connexion);
		 * 
		 */
		
		/*
		 * Moyennes insérées dans la table
		 * 
		 * Insert_Methods.addAvgFilm(connexion);
		 * 
		 */
		
		Select_Methods.sqlSelectQuery("select titre, dateSortie from Film where dateSortie > '1998-01-01'");
		
		
		Connection_methods.connectionClose(connexion);
		System.out.println("Déconnexion");

	}

}
