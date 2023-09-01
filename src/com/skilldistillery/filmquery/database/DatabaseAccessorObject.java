package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
		private static final String USERNAME = "student";
		private static final String PASSWD = "student";
	static {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Film findFilmById(int filmId) throws SQLException {
		Film film = null;
		
		Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWD);
		
		String sql = "SELECT * FROM actor WHERE id = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		
		ResultSet filmResult = stmt.executeQuery();
		
		if (filmResult.next()) {
			film.setId(filmResult.getInt("id"));
			
		}
		return film;
	}

	@Override
	public Actor findActorById(int actorId) throws SQLException {
		Actor actor = null;
		
		Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWD);
		
		String sql = "SELECT * FROM actor WHERE id = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, actorId);
//		System.out.println(stmt);
		
		ResultSet actorResult = stmt.executeQuery();
		
		if (actorResult.next()) {
			actor = new Actor(actorResult.getInt("id"),
					actorResult.getString("first_name"),
					actorResult.getString("last_name"));// Create the object
			// Here is our mapping of query columns to our object fields:
//			actor.setId(actorResult.getInt("id"));
//			actor.setFirstName(actorResult.getString("first_name"));
//			actor.setLastName(actorResult.getString("last_name"));
			
			actor.setFilms(findFilmsByActorId(actorId)); 
			
		}
		
		actorResult.close();
		stmt.close();
		conn.close();
		
		return actor;
	}

	public List<Film> findFilmsByActorId(int actorId) {
		  List<Film> films = new ArrayList<>();
		  try {
		    Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWD);
		    String sql = "SELECT * FROM film JOIN film_actor ON film.id = film_actor.film_id WHERE actor_id = ?";
		    
		    PreparedStatement stmt = conn.prepareStatement(sql);
		    stmt.setInt(1, actorId);
		   
		    ResultSet rs = stmt.executeQuery();
		   
		    while (rs.next()) {
		      int filmId = rs.getInt("id");
		      String title = rs.getString("title");
		      String desc = rs.getString("description");
		      short releaseYear = rs.getShort("release_year");
		      int langId = rs.getInt(5);
		      int rentDur = rs.getInt(6);
		      double rate = rs.getDouble(7);
		      int length = rs.getInt(8);
		      double repCost = rs.getDouble(9);
		      String rating = rs.getString(10);
		      String features = rs.getString(11);
		      Film film = new Film();
//		      Film film = new Film(filmId, title, desc, releaseYear, langId,
//		                           rentDur, rate, length, repCost, rating, features);
		     
		      films.add(film);
		    }
		    rs.close();
		    stmt.close();
		    conn.close();
		  } catch (SQLException e) {
		    e.printStackTrace();
		  }
		  return films;
		}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
