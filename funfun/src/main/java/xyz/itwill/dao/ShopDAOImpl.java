package xyz.itwill.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dto.Festival;
import xyz.itwill.mapper.ShopMapper;

@Repository
@RequiredArgsConstructor
public class ShopDAOImpl implements ShopDAO{
	private final SqlSession sqlSession;
	
	@Override
	public List<Festival> selectAllFestivalList(Map<String, Object> map) {
		return sqlSession.getMapper(ShopMapper.class).selectAllFestivalList(map);
	}
	@Override
	public int selectAllFestivalCount() {
		return sqlSession.getMapper(ShopMapper.class).selectAllFestivalCount();
	}
	

	
}
