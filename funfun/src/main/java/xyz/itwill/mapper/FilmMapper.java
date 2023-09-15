package xyz.itwill.mapper;

import java.util.List;

import xyz.itwill.dto.Film;

public interface FilmMapper {
	int insertFilm(Film film);

	int updateFilm(Film film);

	int deleteFilm(int idx);

	Film selectFilm(int idx);

	List<Film> selectFilmList(int festivalIdx);

	int updateFilmStatus(int idx);

	int updateFilmStatusList(int festivalIdx);

	boolean filmDuplicated(Film film);
}
