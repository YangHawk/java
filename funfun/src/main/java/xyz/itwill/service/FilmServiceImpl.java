package xyz.itwill.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dao.FilmDAO;
import xyz.itwill.dto.Film;

@Service
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {
	private final FilmDAO filmDAO;
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void addFilm(Film film) {
		filmDAO.insertFilm(film);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void modifyFilm(Film film) {
		filmDAO.updateFilm(film);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void removeFilm(int idx) {
		
	}

	@Override
	public Film getFilm(int idx) {
		return filmDAO.selectFilm(idx);
	}

	@Override
	public List<Film> getFilmList(int festivalIdx) {
		return filmDAO.selectFilmList(festivalIdx);
	}

	@Override
	public void modifyFilmStatus(int idx) {
		filmDAO.updateFilmStatus(idx);
	}

	@Override
	public void modifyFilmStatusList(int festivalIdx) {
		filmDAO.updateFilmStatusList(festivalIdx);
	}

	@Override
	public boolean checkDuplicated(Film film) {
		return filmDAO.filmDuplicated(film);
	}

}
