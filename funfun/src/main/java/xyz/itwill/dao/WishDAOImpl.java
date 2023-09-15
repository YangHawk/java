package xyz.itwill.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dto.Wish;
import xyz.itwill.mapper.WishMapper;

@Repository
@RequiredArgsConstructor
public class WishDAOImpl implements WishDAO {
	private final SqlSession sqlSession;

	@Override
	public int insertWish(Wish wish) {
		return sqlSession.getMapper(WishMapper.class).insertWish(wish);
	}

	@Override
	public int deleteWish(int festivalIdx, String accountId) {
		return sqlSession.getMapper(WishMapper.class).deleteWish(festivalIdx,accountId);
	}

	@Override
	public Wish selectWishCount(int festivalIdx) {
		return sqlSession.getMapper(WishMapper.class).selectWishCount(festivalIdx);
	}
	
	@Override
	public Wish selectMyWishFestival(int festivalIdx, String accountId) {
		return sqlSession.getMapper(WishMapper.class).selectMyWishFestival(festivalIdx, accountId);
	}

	@Override
	public List<Wish> selectMyWishList(String accountId) {
		return sqlSession.getMapper(WishMapper.class).selectMyWishList(accountId);
	}

	//추가
	@Override
	public int selectMyWishCount(String accountId) {
		return sqlSession.getMapper(WishMapper.class).selectMyWishCount(accountId);
	}
	
	
	//추가
	@Override
	public List<Wish> selectWishList(Map<String, Object> map) {
		return sqlSession.getMapper(WishMapper.class).selectWishList(map);
	}

}
