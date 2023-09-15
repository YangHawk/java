package xyz.itwill.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dto.Donation;
import xyz.itwill.mapper.DonationMapper;

@Repository
@RequiredArgsConstructor
public class DonationDAOImpl implements DonationDAO {
	private final SqlSession sqlSession;
	
	@Override
	public int insertDonation(Donation donation) {
		return sqlSession.getMapper(DonationMapper.class).insertDonation(donation);
	}

	//변경
	@Override
	public List<Donation> selectDonation(String accountId, int festivalIdx) {
		return sqlSession.getMapper(DonationMapper.class).selectDonation(accountId, festivalIdx);
	}

	@Override
	public int selectMyDonationCount(String accountId) {
		return sqlSession.getMapper(DonationMapper.class).selectMyDonationCount(accountId);
	}
	
	@Override
	public List<Donation> selectMyDonationList(Map<String, Object> map) {
		return sqlSession.getMapper(DonationMapper.class).selectMyDonationList(map);
	}

	

}
