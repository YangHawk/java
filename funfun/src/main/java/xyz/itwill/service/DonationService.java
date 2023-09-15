package xyz.itwill.service;

import java.util.List;
import java.util.Map;

import xyz.itwill.dto.Donation;

public interface DonationService {
	void addDonation(Donation donation);
	//변경
	List<Donation> getDonation(String accountId, int festivalIdx);
	
	Map<String, Object> getMyDonationList(int pageNum, String accountId);
}
