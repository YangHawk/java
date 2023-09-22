package xyz.itwill.service;

import java.util.List;
import java.util.Map;

import xyz.itwill.dto.Donation;

public interface DonationService {
	void addDonation(Donation donation);
	//변경
	void modifyDonation(Donation donation);
	
	Donation getDonationOne(int idx, int festivalIdx);
	
	List<Donation> getDonation(String accountId, int festivalIdx);
	
	Map<String, Object> getMyDonationList(int pageNum, String accountId);
	
	// // 결제 확인 및 결제 취소를 위하여 필요한 토큰을 제공받아 반환하는 메소드
	String getAccessToken(Donation donation);
	
	// 하나의 결제 정보를 제공하는 API를 요청하여 결제 정보를 반환하는 메소드
	Donation getRealDonation(String accessToken, Donation donation);
	
	String cancelDonation(String accessToken, Donation donation);
	
	void removeDonation(int idx);
}
