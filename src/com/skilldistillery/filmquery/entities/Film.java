package com.skilldistillery.filmquery.entities;

import java.util.List;
import java.util.Objects;

public class Film {
	public int id;
	public String title;
	public String description;
	public int releaseYear;
	public int langId;
	public int rentDuration;
	public int rentRate;
	public int length;
	public int replacementCost;
	private List<Actor> actors;

	public Film() {
	}

	public Film(int id) {
		super();
		this.id = id;
	}

	public Film(int id, String title, String description, int releaseYear, int langId, int rentDuration, int rentRate,
			int length, int replacementCost, List<Actor> actors) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.langId = langId;
		this.rentDuration = rentDuration;
		this.rentRate = rentRate;
		this.length = length;
		this.replacementCost = replacementCost;
		this.actors = actors;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public int getLangId() {
		return langId;
	}

	public void setLangId(int langId) {
		this.langId = langId;
	}

	public int getRentDuration() {
		return rentDuration;
	}

	public void setRentDuration(int rentDuration) {
		this.rentDuration = rentDuration;
	}

	public int getRentRate() {
		return rentRate;
	}

	public void setRentRate(int rentRate) {
		this.rentRate = rentRate;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getReplacementCost() {
		return replacementCost;
	}

	public void setReplacementCost(int replacementCost) {
		this.replacementCost = replacementCost;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}


	@Override
	public String toString() {
		return "Film [id=" + id + ", title=" + title + ", description=" + description + ", releaseYear=" + releaseYear
				+ ", langId=" + langId + ", rentDuration=" + rentDuration + ", rentRate=" + rentRate + ", length="
				+ length + ", replacementCost=" + replacementCost + ", actors=" + actors + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(actors, description, id, langId, length, releaseYear, rentDuration, rentRate,
				replacementCost, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return Objects.equals(actors, other.actors) && Objects.equals(description, other.description) && id == other.id
				&& langId == other.langId && length == other.length && releaseYear == other.releaseYear
				&& rentDuration == other.rentDuration && rentRate == other.rentRate
				&& replacementCost == other.replacementCost && Objects.equals(title, other.title);
	}

}
