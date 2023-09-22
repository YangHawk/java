package xyz.itwill.mapper;

import java.util.List;
import java.util.Map;

import xyz.itwill.dto.Donation;

public interface DonationMapper {
	int insertDonation(Donation donation);
	//변경 - 하나의 영화제에 여러번 후원가능하도록 구현?
	int updateDonation(Donation donation);
	
	Donation selectDonationOne(int idx, int festivalIdx);
	
	List<Donation> selectDonation(String accountId, int festivalIdx);
	
	int selectMyDonationCount(String accountId);
	
	List<Donation> selectMyDonationList(Map<String, Object> map);
	
	//결제 취소
	int deleteDonation(int idx);
}
