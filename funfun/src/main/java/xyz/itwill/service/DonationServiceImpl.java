package xyz.itwill.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dao.DonationDAO;
import xyz.itwill.dto.Donation;
import xyz.itwill.util.Pager;

@Service
@RequiredArgsConstructor
public class DonationServiceImpl implements DonationService {
	private final DonationDAO donationDAO;
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void addDonation(Donation donation) {
		donationDAO.insertDonation(donation);
		
	}
	
	//변경
	@Override
	public List<Donation> getDonation(String accountId, int festivalIdx) {
		return donationDAO.selectDonation(accountId, festivalIdx);
	}
	
	@Override
	public Map<String, Object> getMyDonationList(int pageNum, String accountId) {
		int totalBoard = donationDAO.selectMyDonationCount(accountId);
		int blockSize = 5;
		int pageSize = 10;
		Pager pager = new Pager(pageNum, totalBoard, pageSize, blockSize);
		
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("startRow", pager.getStartRow());
		pageMap.put("endRow", pager.getEndRow());
		pageMap.put("accountId", accountId);
		
		List<Donation> donationList = donationDAO.selectMyDonationList(pageMap);
		
		Map<String, Object> wishMap = new HashMap<String, Object>();
		wishMap.put("donationList", donationList);
		wishMap.put("pager", pager);
		
		return wishMap;
	}
}
