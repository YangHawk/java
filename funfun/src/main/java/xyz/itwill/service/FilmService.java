package xyz.itwill.service;

import java.util.List;

import xyz.itwill.dto.Film;

public interface FilmService {
	void addFilm(Film film);

	void modifyFilm(Film film);

	void removeFilm(int idx);

	Film getFilm(int idx);

	List<Film> getFilmList(int festivalIdx);

	void modifyFilmStatus(int idx);

	void modifyFilmStatusList(int festivalIdx);
	
	boolean checkDuplicated(Film film);
}
