package com.gestion.bdd;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Insert_Methods {
	
	private static ArrayList<String[]> getInfosFile(String nameFile, String patternSeparation) throws FileNotFoundException {
		
		String thisLine;
		String[] groupes = new String[200];
		ArrayList<String[]> data = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(nameFile)) ;
			while ((thisLine = br.readLine()) != null) {
				groupes = thisLine.split(patternSeparation);
				for (int i = 0 ; i > groupes.length ; i++) {
					if (groupes[i].contains("'")) {
						groupes[i].replace("'", "\'");
					}
				}
				data.add(groupes) ;
			}
			br.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return data ;
	}
	
	private static String translateDateType(String dateFromFileItem) {
		// Format "01-Jan-1995"
		String nouveauFormat = null ;
		Pattern pattern = Pattern.compile("-");
		String[] groupes = pattern.split(dateFromFileItem);
		switch (groupes[1]) {
			case "Jan" : groupes[1] = "01" ; break ;
			case "Feb" : groupes[1] = "02" ; break ;
			case "Mar" : groupes[1] = "03" ; break ;
			case "Apr" : groupes[1] = "04" ; break ;
			case "May" : groupes[1] = "05" ; break ;
			case "Jun" : groupes[1] = "06" ; break ;
			case "Jul" : groupes[1] = "07" ; break ;
			case "Aug" : groupes[1] = "08" ; break ;
			case "Sep" : groupes[1] = "09" ; break ;
			case "Oct" : groupes[1] = "10" ; break ;
			case "Nov" : groupes[1] = "11" ; break ;
			case "Dec" : groupes[1] = "12" ; break ;
		}
		nouveauFormat = (groupes[2] + groupes[1] + groupes[0]) ;
		return nouveauFormat ;
	}
	
	public static void addUsers(Connection bdd) {
		if (bdd != null) {
			ArrayList<String[]> data = null ;
			int compteurRéussites = 0;
			int compteurEchecs = 0;
			try {
				data = getInfosFile("./ressources/u.user", "\\|") ;
				Statement statement = bdd.createStatement();
				for (String[] user : data) {
					String sql = ("INSERT INTO Utilisateur ("
							+ "idUtilisateur, "
							+ "age, "
							+ "sexe, "
							+ "occupation, "
							+ "zipCode) VALUES (?, ?, ?, ?, ?)");
					PreparedStatement stmt = bdd.prepareStatement(sql);
					stmt.setString(1, user[0]);
					stmt.setInt(2, Integer.parseInt(user[1]));
					stmt.setString(3, user[2]);
					stmt.setString(4, user[3]);
					stmt.setString(5, user[4]);
					int res = stmt.executeUpdate();
					if (res == 1) {
						compteurRéussites++;
					} else {
						compteurEchecs++;
					}
					if (statement != null) {
						statement.close();
					}
				}
				System.out.println("Ajout des utilisateurs \n");
				System.out.println("Nombre de requêtes réussies : " + compteurRéussites);
				System.out.println("Nombre de requêtes échouées : " + compteurEchecs);
				System.out.println((compteurEchecs+compteurRéussites) + " requêtes au total \n");
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static void addMovies(Connection bdd) {
		if (bdd != null) {
			ArrayList<String[]> data = null ;
			int compteurRéussites = 0;
			int compteurEchecs = 0;
			try {
				data = getInfosFile("./ressources/u.item", "\\|") ;
				Statement statement = bdd.createStatement();
				for (String[] film : data) {
					String sql = ("INSERT INTO Film ("
							+ "idFilm, "
							+ "titre, "
							+ "dateSortie, "
							+ "lienMovieLens) VALUES (?, ?, ?, ?)");
					PreparedStatement stmt = bdd.prepareStatement(sql);
					stmt.setInt(1, Integer.parseInt(film[0]));
					stmt.setString(2, film[1]);
					if (!film[2].equals("")) {
						SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd") ;
						java.util.Date parsed = null ;
						try {
							parsed = format.parse(translateDateType(film[2]));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						java.sql.Date dateSql = new java.sql.Date(parsed.getTime());
						stmt.setDate(3, dateSql) ;
					} else {
						stmt.setNull(3, java.sql.Types.DATE);
					}
					stmt.setString(4, film[4]);
					int res = stmt.executeUpdate();
					if (res == 1) {
						compteurRéussites++;
					} else {
						compteurEchecs++;
					}
					if (statement != null) {
						statement.close();
					}
				}
				System.out.println("Ajout des films \n");
				System.out.println("Nombre de requêtes réussies : " + compteurRéussites);
				System.out.println("Nombre de requêtes échouées : " + compteurEchecs);
				System.out.println((compteurEchecs+compteurRéussites) + " requêtes au total \n");
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} finally {
				Connection_methods.connectionClose(bdd);
			}
		}
	}
	
	public static void addGenres(Connection bdd) {
		if (bdd != null) {
			ArrayList<String[]> data = null ;
			int compteurRéussites = 0;
			int compteurEchecs = 0;
			try {
				data = getInfosFile("./ressources/u.genre", "\\|") ;
				Statement statement = bdd.createStatement();
				for (String[] genre : data) {
					String sql = ("INSERT INTO Genre ("
							+ "idGenre, "
							+ "libelle) VALUES (?, ?)");
					PreparedStatement stmt = bdd.prepareStatement(sql);
					stmt.setInt(1, Integer.parseInt(genre[1]));
					stmt.setString(2, genre[0]);
					int res = stmt.executeUpdate();
					if (res == 1) {
						compteurRéussites++;
					} else {
						compteurEchecs++;
					}
					if (statement != null) {
						statement.close();
					}
				}
				System.out.println("Ajout des genres \n");
				System.out.println("Nombre de requêtes réussies : " + compteurRéussites);
				System.out.println("Nombre de requêtes échouées : " + compteurEchecs);
				System.out.println((compteurEchecs+compteurRéussites) + " requêtes au total \n");
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public static void addNotes(Connection bdd) {
		if (bdd != null) {
			ArrayList<String[]> data = null ;
			int compteurRéussites = 0;
			int compteurEchecs = 0;
			try {
				data = getInfosFile("./ressources/u.data", "\\t") ;
				Statement statement = bdd.createStatement();
				for (String[] user : data) {
					String sql = ("INSERT INTO Note ("
							+ "idUtilisateur, "
							+ "idFilm, "
							+ "note, "
							+ "date) VALUES (?, ?, ?, ?)");
					PreparedStatement stmt = bdd.prepareStatement(sql);
					stmt.setInt(1, Integer.parseInt(user[0]));
					stmt.setInt(2, Integer.parseInt(user[1]));
					stmt.setInt(3, Integer.parseInt(user[2]));
					java.sql.Date dateNote = new java.sql.Date((long)Long.parseLong(user[3])*1000);
					// setDateTime ? => Besoin de l'heure
					stmt.setDate(4, dateNote);
					int res = stmt.executeUpdate();
					if (res == 1) {
						compteurRéussites++;
					} else {
						compteurEchecs++;
					}
					if (statement != null) {
						statement.close();
					}
				}
				System.out.println("Ajout des notes \n");
				System.out.println("Nombre de requêtes réussies : " + compteurRéussites);
				System.out.println("Nombre de requêtes échouées : " + compteurEchecs);
				System.out.println((compteurEchecs+compteurRéussites) + " requêtes au total \n");
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public static void addAssociationFilmGenre(Connection bdd) {
		if (bdd != null) {
			ArrayList<String[]> data = null ;
			int compteurRéussites = 0;
			int compteurEchecs = 0;
			try {
				data = getInfosFile("./ressources/u.item", "\\|") ;
				Statement statement = bdd.createStatement();
				for (String[] film : data) {
					String sql = ("INSERT INTO AssociationFilmGenre ("
							+ "idGenre, "
							+ "idFilm) VALUES (?, ?)");
					for (int i = 5 ; i < film.length ; i++) {
						if (Integer.parseInt(film[i]) == 1) {
							PreparedStatement stmt = bdd.prepareStatement(sql);
							stmt.setInt(1, i-5);
							stmt.setInt(2, Integer.parseInt(film[0]));
							int res = stmt.executeUpdate();
							if (res == 1) {
								compteurRéussites++;
							} else {
								compteurEchecs++;
							}
							if (statement != null) {
								statement.close();
							}
						}
						
					}
					
				}
				System.out.println("Ajout des associations \n");
				System.out.println("Nombre de requêtes réussies : " + compteurRéussites);
				System.out.println("Nombre de requêtes échouées : " + compteurEchecs);
				System.out.println((compteurEchecs+compteurRéussites) + " requêtes au total \n");
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static void addAvgFilm(Connection bdd) {
		if (bdd != null) {
			try {
				Statement statement = bdd.createStatement();
				String query = "select f.idFilm, sum(n.note), count(n.note) from Film f, Note n where n.idFilm = f.idFilm group by f.idFilm" ;
				statement = bdd.createStatement();
		        ResultSet rs = statement.executeQuery(query);
		        int compteurRéussites = 0 ;
		        int compteurEchecs = 0 ;
		        while (rs.next()) {
		        	float somme = rs.getFloat(2) ;
		        	float compte = rs.getFloat(3) ;
		        	float avg = somme/compte ;
		        	Statement ajout = bdd.createStatement() ;
		        	String sql = "update Film set noteMoyenne = " + avg + " where idFilm = " + rs.getInt(1) ;
		        	int res = ajout.executeUpdate(sql) ;
		        	if (res == 1) {
		        		compteurRéussites++;
		        	} else {
		        		compteurEchecs++;
		        	}
		        }
		        System.out.println("Ajout des moyennes \n");
				System.out.println("Nombre de requêtes réussies : " + compteurRéussites);
				System.out.println("Nombre de requêtes échouées : " + compteurEchecs);
				System.out.println((compteurEchecs+compteurRéussites) + " requêtes au total \n");
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
}
