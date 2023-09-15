package xyz.itwill.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import xyz.itwill.dto.Payment;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Override
	public void addPayment(Payment payment) {
		System.out.println("PAYMENT 테이블에 결제 정보를 삽입하는 명령");
	}

	@Override
	public void modifyPayment(Payment payment) {
		System.out.println("PAYMENT 테이블에 결제 정보를 변경하는 명령");
	}

	// 결제 확인 및 결제 취소를 위하여 필요한 토큰을 제공받아 반환하는 메소드
	@Override
	public String getAccessToken(Payment payment) {
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
	public Payment getPayment(String accessToken, Payment payment) {
		Payment responsePayment = new Payment(); // 응답 결과를 저장하기 위한 객체를 생성
		String apiUrl = "https://api.iamport.kr/payments";
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

				// 응답 결과(JSON 형식의 문자열)를 Java 객체로 변환하여 파싱 처리
				JSONParser parser = new JSONParser();
				JSONObject jsonObject = (JSONObject) parser.parse(result);
				JSONObject resopnseObject = (JSONObject) jsonObject.get("response");

				responsePayment.setImpUid((String) resopnseObject.get("imp_uid"));
				responsePayment.setMerchantUid((String) resopnseObject.get("merchant_uid"));
				responsePayment.setAmount((Long) resopnseObject.get("amount"));
				responsePayment.setStatus((String) resopnseObject.get("status"));
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responsePayment;
	}

	// 결제를 취소하는 API를 요청하여 결과를 반환하는 메소드
	@Override
	public String cancelPayment(String accessToken, Payment payment) {
		// 결제 취소를 요청하기 위한 API의 URL 주소
		String apiUrl = "https://api.iamport.kr/payments/cancel";

		// 요청 API에게 전달될 값을 JSON 형식의 문자열로 표현하여 저장
		// ▶ {"imp_uid" : 결제 고유값, "checksum" : 취소 금액}
		String data = "{\"imp_uid\":\"" + payment.getImpUid() + "\", \"checksum\":\"" + payment.getAmount() + "\"}";

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
}
