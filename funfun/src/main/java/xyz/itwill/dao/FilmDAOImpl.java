package xyz.itwill.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dto.Film;
import xyz.itwill.mapper.FilmMapper;

@Repository
@RequiredArgsConstructor
public class FilmDAOImpl implements FilmDAO {
	private final SqlSession sqlSession;
	
	@Override
	public int insertFilm(Film film) {
		return sqlSession.getMapper(FilmMapper.class).insertFilm(film);
	}

	@Override
	public int updateFilm(Film film) {
		return sqlSession.getMapper(FilmMapper.class).updateFilm(film);
	}

	@Override
	public int deleteFilm(int idx) {
		return sqlSession.getMapper(FilmMapper.class).deleteFilm(idx);
	}

	@Override
	public Film selectFilm(int idx) {
		return sqlSession.getMapper(FilmMapper.class).selectFilm(idx);
	}

	@Override
	public List<Film> selectFilmList(int festivalIdx) {
		return sqlSession.getMapper(FilmMapper.class).selectFilmList(festivalIdx);
	}

	@Override
	public int updateFilmStatus(int idx) {
		return sqlSession.getMapper(FilmMapper.class).updateFilmStatus(idx);
	}

	@Override
	public int updateFilmStatusList(int festivalIdx) {
		return sqlSession.getMapper(FilmMapper.class).updateFilmStatusList(festivalIdx);
	}

	@Override
	public boolean filmDuplicated(Film film) {
		return sqlSession.getMapper(FilmMapper.class).filmDuplicated(film);
	}

}
