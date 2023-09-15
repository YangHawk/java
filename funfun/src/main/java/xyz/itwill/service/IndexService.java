package xyz.itwill.service;

import java.util.List;
import java.util.Map;

import xyz.itwill.dto.Expect;
import xyz.itwill.dto.Festival;
import xyz.itwill.dto.Film;
import xyz.itwill.exception.FestivalinfoNotFoundException;

public interface IndexService {
	Festival getFestival(int idx) throws FestivalinfoNotFoundException;
	Map<String, Object> getFestivalList(int pageNum, int pageSize, String selectKeyword);
	Film getFilm(int idx);
	List<Film> getFilmList(int festivalIdx);
	Expect getExpect(int idx);
	List<Expect> getExpectList(int festivalIdx);

}
