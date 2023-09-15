package xyz.itwill.dao;

import java.util.List;
import java.util.Map;

import xyz.itwill.dto.Donation;

public interface DonationDAO {
	int insertDonation(Donation donation);
	//변경
	List<Donation> selectDonation(String accountId, int festivalIdx);
	
	int selectMyDonationCount(String accountId);
	List<Donation> selectMyDonationList(Map<String, Object> map);
}
