package xyz.itwill.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dto.Account;
import xyz.itwill.dto.Festival;
import xyz.itwill.dto.IndexShow;
import xyz.itwill.dto.Notice;
import xyz.itwill.dto.Question;
import xyz.itwill.exception.ExistsUserinfoException;
import xyz.itwill.exception.FestivalinfoNotFoundException;
import xyz.itwill.exception.UserinfoNotFoundException;
import xyz.itwill.service.AccountService;
import xyz.itwill.service.DonationService;
import xyz.itwill.service.FestivalService;
import xyz.itwill.service.NoticeService;
import xyz.itwill.service.QuestionService;
import xyz.itwill.service.WishService;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminRestController {
	private final AccountService accountService;
	private final FestivalService festivalService;
	private final QuestionService questionService;
	private final NoticeService noticeService;
	private final WishService wishService;
	private final DonationService donationService;
	private final WebApplicationContext context;

	@GetMapping("/account_list")
	public Map<String, Object> getAccounts(@RequestParam(defaultValue = "1") int pageNum,
			@RequestParam(defaultValue = "20") int pageSize, @RequestParam(defaultValue = "") String selectKeyword) {
		return accountService.getAccountList(pageNum, pageSize, selectKeyword);
	}

	@GetMapping("/account_details")
	public Map<String, Object> getMyAccounts(@RequestParam String id, @RequestParam int questionPageNum,
			@RequestParam int donationPageNum, @RequestParam int wishPageNum) {
		Map<String, Object> myWish;
		myWish = wishService.getMyWishList(wishPageNum, id);
		Map<String, Object> myQuestion;
		myQuestion = questionService.getMyQuestionList(questionPageNum, id);
		Map<String, Object> myDonation;
		myDonation = donationService.getMyDonationList(donationPageNum, id);
		Map<String, Object> myMap = new HashMap<String, Object>();
		myMap.put("myWish", myWish);
		myMap.put("myQuestion", myQuestion);
		myMap.put("myDonation", myDonation);
		return myMap;
	}

	@GetMapping("/selected_list")
	public Map<String, Object> getSelectedFestival() {
		List<Festival> indexMainFestival = festivalService.getIndexMainFestival();
		List<Festival> indexExpectFestival = festivalService.getIndexExpectFestival();

		Map<String, Object> pageMap = new HashMap<String, Object>();

		pageMap.put("indexMainFestival", indexMainFestival);
		pageMap.put("indexExpectFestival", indexExpectFestival);

		return pageMap;
	}

	@GetMapping("/main_list")
	public Map<String, Object> getMainFestivalList(@RequestParam(defaultValue = "1") int mainPageNum) {
		return festivalService.getMainFestivalList(mainPageNum);
	}

	@GetMapping("/expect_list")
	public Map<String, Object> getExpectFestivalList(@RequestParam(defaultValue = "1") int expectPageNum) {
		return festivalService.getExpectFestivalList(expectPageNum);
	}

	@PutMapping("/main_modify")
	public String modifyMainFestival(@RequestBody IndexShow indexShow) {
		List<Integer> checkedMainFestivalList = indexShow.getCheckedMainFestivalList();
		
		festivalService.modifyFestivalIndexShow1(checkedMainFestivalList);
		return "success";
	}

	@PutMapping("/expect_modify")
	public String modifyExpectFestival(@RequestBody IndexShow indexShow) {
		List<Integer> checkedExpectFestivalList = indexShow.getCheckedExpectFestivalList();
		
		festivalService.modifyFestivalIndexShow2(checkedExpectFestivalList);
		return "success";
	}

	@PostMapping("/admin_register")
	public String adminRegister(@RequestBody Account account) throws ExistsUserinfoException {
		//account.setStatus(0);
		accountService.addAccount(account, "ROLE_ADMIN");
		return "success";
	}

	@PutMapping("/account_modify")
	public String modifyAcocunt(@RequestBody Account account) throws UserinfoNotFoundException {
		accountService.modifyAccountByAdmin(account);
		return "success";
	}

	@PutMapping("/account_remove")
	public String removeAccount(@RequestParam String id) throws UserinfoNotFoundException {
		accountService.removeAccount(id);
		return "success";
	}

	@GetMapping("/festival_list")
	public Map<String, Object> getFestivals(@RequestParam(defaultValue = "1") int pageNum,
			@RequestParam(defaultValue = "20") int pageSize, @RequestParam(defaultValue = "") String selectKeyword) {
		return festivalService.getFestivalList(pageNum, pageSize, selectKeyword);
	}

	@PutMapping("/festival_accept")
	public String modifyNotice(@RequestBody Festival festival)
			throws FestivalinfoNotFoundException {
		festival.setState(1);
		festivalService.modifyFestivalState(festival);

		return "success";
	}

	@PutMapping("/festival_reject")
	public String acceptFestival(@RequestBody Festival festival)
			throws FestivalinfoNotFoundException {
		festival.setState(4);
		festivalService.modifyFestivalState(festival);

		return "success";
	}

	@GetMapping("/question_list")
	public Map<String, Object> getQuestions(@RequestParam(defaultValue = "1") int pageNum,
			@RequestParam(defaultValue = "20") int pageSize, @RequestParam(defaultValue = "") String selectKeyword) {
		return questionService.getQuestionList(pageNum, pageSize, selectKeyword);
	}

	@PostMapping("/question_add")
	public String addQuestion(@RequestBody Question question, @RequestPart MultipartFile uploadFile) throws IllegalStateException, IOException {
		String uploadDirectory = context.getServletContext().getRealPath("/resources/upload");
		String uploadFilename = UUID.randomUUID().toString() + "_" + uploadFile.getOriginalFilename();

		question.setFileData(uploadFilename);
		question.getUploadFile().transferTo(new File(uploadDirectory, uploadFilename));

		questionService.addQuestion(question);

		return "success";
	}


	@PutMapping("/question_modify_by_user")
	public String modifyQuestionByUser(@RequestBody Question question) {
		question.setTitle(HtmlUtils.htmlEscape(question.getTitle()));
		question.setContent(HtmlUtils.htmlEscape(question.getContent()));

		questionService.modifyQuestionByUser(question);

		return "success";
	}

	@PutMapping("/question_modify_by_admin")
	public String modifyQuestionByAdmin(@RequestBody Question question) {
		question.setAnswerContent(HtmlUtils.htmlEscape(question.getAnswerContent()));

		questionService.modifyQuestionByAdmin(question);

		return "success";
	}

	@PutMapping("/question_remove")
	public String removeQuestion(@RequestParam int idx) {
		questionService.removeQuestion(idx);
		return "success";
	}

	@GetMapping("/notice_list")
	public Map<String, Object> getNotices(@RequestParam(defaultValue = "1") int pageNum,
			@RequestParam(defaultValue = "20") int pageSize, @RequestParam(defaultValue = "") String selectKeyword) {
		return noticeService.getNoticeList(pageNum, pageSize, selectKeyword);
	}

	@PostMapping("/notice_add")
	public String addNotice(@RequestBody Notice notice, @RequestParam("uploadFile") MultipartFile uploadFile, HttpServletRequest request)
			throws IllegalStateException, IOException {
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		// 파일을 업로드하려면 uploadFile 변수를 확인합니다.
        if (uploadFile != null && !uploadFile.isEmpty()) {
            try {
                String uploadDirectory = request.getServletContext().getRealPath("/resources/upload"); // 업로드할 디렉토리 경로
                String originalFilename = uploadFile.getOriginalFilename();
                String uniqueFilename = UUID.randomUUID().toString() + "_" + originalFilename;
                String filePath = uploadDirectory + File.separator + uniqueFilename;

                // 파일 저장
                File destFile = new File(filePath);
                uploadFile.transferTo(destFile);

                notice.setFileData(uniqueFilename);
            } catch (IOException e) {
                // 파일 업로드 중 오류 처리
                e.printStackTrace();
            }
        }
        
		notice.setTitle(title);
		notice.setContent(content);
		noticeService.addNotice(notice);

		// 나머지 공지사항 등록 로직을 수행합니다.
		return "success";
	}

	@PostMapping("/notice_modify")
	public String modifyNotice(@ModelAttribute Notice notice, @RequestParam("uploadFile") MultipartFile uploadFile,
            @RequestParam("fileDeleted") boolean fileDeleted,
            HttpServletRequest request) throws IllegalStateException, IOException {
		// 기존 공지사항 정보를 가져옵니다.
		Notice existingNotice = noticeService.getNotice(notice.getIdx());
		
		// 이미지 파일이 업로드되지 않았을 때 기존 이미지를 유지합니다.
	       if (fileDeleted) {
	           notice.setFileData(null); // 파일을 삭제하므로 null로 설정
	       } else {
	    	   if(!uploadFile.isEmpty()) {
	    	   String uploadDirectory = request.getServletContext().getRealPath("/resources/upload"); // 업로드할 디렉토리 경로
	               String originalFilename = uploadFile.getOriginalFilename();
	               String uniqueFilename = UUID.randomUUID().toString() + "_" + originalFilename;
	               //String filePath = uploadDirectory + File.separator + uniqueFilename;

	               // 새로 업로드한 파일로 설정
	               notice.setFileData(uniqueFilename);
	               // 파일 저장
	               uploadFile.transferTo(new File(uploadDirectory, uniqueFilename));

	    	   		} else {
	    	   			// 이미지 파일이 업로드되지 않았을 때 기존 파일 정보를 그대로 사용
	    	   			notice.setFileData(existingNotice.getFileData());
	    	   		}
	       		}

		noticeService.modifyNotice(notice);

		return "success";
	}

	@PutMapping("/notice_remove")
	public String removeNotice(@RequestParam int idx) {
		noticeService.removeNotice(idx);
		return "success";
	}
}