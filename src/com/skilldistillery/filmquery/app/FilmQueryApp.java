package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {
	boolean exit;
	Scanner sc = new Scanner(System.in);

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) throws SQLException {
		FilmQueryApp app = new FilmQueryApp();
		app.launch();
	}

	private void launch() throws SQLException {
		startUserInterface();
		sc.close();
	}

	private void startUserInterface() throws SQLException {
		while (!exit) {
			userMenu();
			int choice = sc.nextInt();
			sc.nextLine();

			menuChoice(choice);
		}

	}

	private void userMenu() {
		System.out.println("|===============================|");
		System.out.println("|           Menu                |");
		System.out.println("| 1)   Look Up Film by ID       |");
		System.out.println("| 2) Look Up Film by Keyword    |");
		System.out.println("| 3)        Exit                |");
		System.out.println("|===============================|");
	}

	private void menuChoice(int choice) throws SQLException {
		switch (choice) {
		case 1:
			Film filmById = filmByID();
			if (filmById == null) {
				System.err.println("Could not find Film ID.");
			} else {
				filmById.setLanguage(db.findFilmLanguage(filmById.getId()));
				filmById.setActors(db.findActorsByFilmId(filmById.getId()));
				System.out.println(filmById.userView());
			}
			break;
		case 2:
			List<Film> films = filmsByKeyword();
			if (films == null) {
				System.err.println("No films match this search.");
			} else {
				for (Film film : films) {
					film.setLanguage(db.findFilmLanguage(film.getId()));
					film.setActors(db.findActorsByFilmId(film.getId()));
					System.out.println(film.userView());
				}
			}
			break;
		case 3:
			System.out.println("Thank you for using the Film Query App! Goodbye.");
			exit = true;
			break;
		default:
			System.err.println("Invalid Input");
			break;
		}
	}

	private Film filmByID() throws SQLException {
		System.out.print("Enter Film ID: ");
		int id = sc.nextInt();
		sc.nextLine();

		return db.findFilmById(id);

	}

	private List<Film> filmsByKeyword() {
		System.out.print("Enter Keyword: ");
		String keyword = sc.nextLine();

		return db.findFilmsByKeyword(keyword);

	}

}