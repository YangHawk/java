package xyz.itwill.dao;

import java.util.List;
import java.util.Map;

import xyz.itwill.dto.Donation;

public interface DonationDAO {
	int insertDonation(Donation donation);
	//변경
	int updateDonation(Donation donation);
	
	Donation selectDonationOne(int idx, int festivalIdx);
	
	List<Donation> selectDonation(String accountId, int festivalIdx);
	
	int selectMyDonationCount(String accountId);
	List<Donation> selectMyDonationList(Map<String, Object> map);
	
	//결제 취소
	int deleteDonation(int idx);
}
