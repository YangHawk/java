package xyz.itwill.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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
	
	@Override
	public Donation getDonationOne(int idx, int festivalIdx) {
		return donationDAO.selectDonationOne(idx, festivalIdx);
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

	// 결제 확인 및 결제 취소를 위하여 필요한 토큰을 제공받아 반환하는 메소드
	@Override
	public String getAccessToken(Donation donation) {
		String accessToken = "";
		// 토큰 발급을 요청하기 위한 API의 URL 주소
		String apiUrl = "https://api.iamport.kr/users/getToken";

		// 요청 API에게 전달될 값을 JSON 형식의 문자열로 표현하여 저장
		// ▶ {"imp_key" : REST API KEY, "imp_secret" : REST API Secret}
		String data = "{\"imp_key\":\"6280638234120602\", \"imp_secret\":\"XQHX27Wuyg9rYCLYF1IBevvQKDjkrSA8mIz6xbtZjZ3rbbXHwbjt8yNCEihF68fRKfXTAFS62hm6cLsW\"}";

		try {
			URL url = new URL(apiUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true); // 응답 결과를 반환하기 위하여 필드값 변경
			connection.setRequestMethod("POST"); // 요청 방식을 변경하기 위한 필드값 변경
			connection.setRequestProperty("Content-Type", "application/json"); // 전달값의 형식을 변경하기 위한 필드값 변경

			// API 요청에 필요한 값을 출력 스트림을 제공받아 전달(JSON 형식의 문자열)
			try (OutputStream out = connection.getOutputStream()) {
				byte[] requestaData = data.getBytes("utf-8");
				out.write(requestaData);
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 응답 코드를 반환받아 저장
			int responseCode = connection.getResponseCode();

			if (responseCode == 200) { // 정상적인 응답 결과를 제공받은 경우
				// 응답 결과를 제공받기 위한 입력 스트림을 확장하여 저장
				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

				// 입력 스트림으로 응답 결과를 얻어와 저장
				String input = "";
				String result = "";
				while ((input = br.readLine()) != null) {
					result += input;
				}
				br.close();

				System.out.println("result(Token) = " + result);

				// 응답 결과(JSON 형식의 문자열)를 Java 객체로 변환하여 파싱 처리
				JSONParser parser = new JSONParser();
				JSONObject jsonObject = (JSONObject) parser.parse(result);
				JSONObject resopnseObject = (JSONObject) jsonObject.get("response");
				accessToken = (String) resopnseObject.get("access_token");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return accessToken;
	}

	// 하나의 결제 정보를 제공하는 API를 요청하여 결제 정보를 반환하는 메소드
	@Override
	public Donation getRealDonation(String accessToken, Donation donation) {
		Donation responseDonation= new Donation(); // // 응답 결과를 저장하기 위한 객체를 생성
		
		// 결제 번호를 전달하여 결제 정보를 제공받기 위한 API 의 URL 주소
		String apiUrl = "https://api.iamport.kr/payments/" + donation.getImpUid();
		
		try {
			URL url = new URL(apiUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			connection.setRequestMethod("GET");
			connection.setRequestProperty("Authorization", accessToken);

			int responseCode = connection.getResponseCode();

			if (responseCode == 200) { // 정상적인 응답 결과를 제공받은 경우
				// 응답 결과를 제공받기 위한 입력 스트림을 확장하여 저장
				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

				// 입력 스트림으로 응답 결과를 얻어와 저장
				String input = "";
				String result = "";
				while ((input = br.readLine()) != null) {
					result += input;
				}
				br.close();

				System.out.println("result(payment) = " + result);

				// 응답 결과(JSON 형식의 문자열)를 Java 객체로 변환하여 파싱 처리
				JSONParser parser = new JSONParser();
				JSONObject jsonObject = (JSONObject) parser.parse(result);
				JSONObject resopnseObject = (JSONObject) jsonObject.get("response");

				responseDonation.setImpUid((String) resopnseObject.get("imp_uid"));
				responseDonation.setMerchantUid((String) resopnseObject.get("merchant_uid"));
				responseDonation.setMoney(String.valueOf(resopnseObject.get("amount")));
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseDonation;
	}

	@Override
	public String cancelDonation(String accessToken, Donation donation) {
		// 결제 취소를 요청하기 위한 API의 URL 주소
				String apiUrl = "https://api.iamport.kr/payments/cancel";

				// 요청 API에게 전달될 값을 JSON 형식의 문자열로 표현하여 저장
				// ▶ {"imp_uid" : 결제 고유값, "checksum" : 취소 금액}
				String data = "{\"imp_uid\":\"" + donation.getImpUid() + "\", \"checksum\":\"" + donation.getMoney() + "\"}";

				try {
					URL url = new URL(apiUrl);
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					connection.setDoOutput(true); // 응답 결과를 반환하기 위하여 필드값 변경
					connection.setRequestMethod("POST"); // 요청 방식을 변경하기 위한 필드값 변경
					connection.setRequestProperty("Content-Type", "application/json"); // 전달값의 형식을 변경하기 위한 필드값 변경

					// API 요청에 필요한 값을 출력 스트림을 제공받아 전달(JSON 형식의 문자열)
					try (OutputStream out = connection.getOutputStream()) {
						byte[] requestaData = data.getBytes("utf-8");
						out.write(requestaData);
						out.close();
					} catch (Exception e) {
						e.printStackTrace();
					}

					// 응답 코드를 반환받아 저장
					int responseCode = connection.getResponseCode();

					if (responseCode == 200) { // 정상적인 응답 결과를 제공받은 경우
						return "success";
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
	}

	@Override
	public void modifyDonation(Donation donation) {
		donationDAO.updateDonation(donation);
	}
}
