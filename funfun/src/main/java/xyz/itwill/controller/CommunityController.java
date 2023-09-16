package xyz.itwill.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dto.Account;
import xyz.itwill.dto.Notice;
import xyz.itwill.dto.Question;
import xyz.itwill.security.CustomAccountDetails;
import xyz.itwill.service.NoticeService;
import xyz.itwill.service.QuestionService;

@Controller
@RequiredArgsConstructor
public class CommunityController {
	private final NoticeService noticeService;
	private final QuestionService questionService;
	
	/*faq 페이지 접속*/
	@RequestMapping(value = "/community/faq", method = RequestMethod.GET)
	public String faq() {
		return "community/faq";
	}
	
	/* 공지사항 목록 페이지 접속 */
	@RequestMapping(value = "/community/notice", method = RequestMethod.GET)
	public String getNoticeList(Model model, Authentication authentication) {
		CustomAccountDetails loginAccount = (CustomAccountDetails)authentication.getPrincipal();
		model.addAttribute("loginAccount", loginAccount);
		return "community/list";
	}
	
	/*공지사항 작성 페이지*/
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/community/notice/form", method = RequestMethod.GET)
	public String getNoticeForm(Authentication authentication) {
		
		return "community/notice/form";
	}
	
	/*공지사항 제출 시*/
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/community/notice/form", method = RequestMethod.POST)
    public String getNoticeForm(@RequestParam("title") String title,
                                @RequestParam("content") String content,
                                @RequestParam("uploadFile") MultipartFile uploadFile
                                ,HttpServletRequest request)  {
        Notice notice = new Notice();
        notice.setTitle(title);
        notice.setContent(content);

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

        noticeService.addNotice(notice);
        return "redirect:/community/notice";
	}
	
	 /*공지사항 글 상세페이지*/
	   @RequestMapping(value = "/community/notice/notice_detail", method = RequestMethod.GET)
	   public String noticeDetail(@RequestParam int idx, Model model) {
	      //조회수 count
	      noticeService.getNotice(idx);
	      noticeService.modifyNoticeCount(idx);
	      model.addAttribute("notice", noticeService.getNotice(idx));
	      model.addAttribute("preNextIdx", noticeService.getPreNextNoticeidx(idx));
	      return "community/notice/notice_detail";
	   }
	
	/*공지사항 글 삭제*/
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/community/notice/delete", method = RequestMethod.POST)
	public String deleteNotice(@RequestParam("idx") int idx) {
	    noticeService.removeNotice(idx);
	    return "redirect:/community/notice";
	}
	
	/*QNA 목록 페이지*/
	@RequestMapping(value = "/community/qna_list", method = RequestMethod.GET)
	public String getQnaList()  {
		return "community/qna_list";
	}
	
	/*QNA 작성 페이지*/
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/community/qna_form", method = RequestMethod.GET)
	public String getQnaForm() {
		return "community/qna_form";
	}
	
	/*QNA 제출 시*/
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/community/qna_form", method = RequestMethod.POST)
	public String getQnaForm(@RequestParam("title") String title,
			@RequestParam("content") String content,
            @RequestParam("uploadFile") MultipartFile uploadFile
            ,HttpServletRequest request, Authentication authentication)  {
		
				CustomAccountDetails loginAccount = (CustomAccountDetails)authentication.getPrincipal();
				
				Question question = new Question();
				question.setAccountId(loginAccount.getId());
				question.setTitle(title);
				question.setContent(content);
				
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
				
				question.setFileData(uniqueFilename);
					} catch (IOException e) {
					// 파일 업로드 중 오류 처리
					e.printStackTrace();
					}
				}
		questionService.addQuestion(question);
		return "redirect:/community/qna_list";
	}
	
	/*QNA 비밀글 제출 시*/
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/community/qna_form_secret", method = RequestMethod.POST)
	public String getSecretQnaForm(@RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("uploadFile") MultipartFile uploadFile, Authentication authentication)  {
		
				CustomAccountDetails loginAccount = (CustomAccountDetails)authentication.getPrincipal();
				Question question = new Question();
				question.setAccountId(loginAccount.getId());
				question.setTitle(title);
				question.setContent(content);
				
				// 파일을 업로드하려면 uploadFile 변수를 확인합니다.
				if (uploadFile != null && !uploadFile.isEmpty()) {
					try {
					String uploadDirectory = "${pageContext.request.contextPath}/resources/upload"; // 업로드할 디렉토리 경로
					String originalFilename = uploadFile.getOriginalFilename();
					String uniqueFilename = UUID.randomUUID().toString() + "_" + originalFilename;
					String filePath = uploadDirectory + File.separator + uniqueFilename;
				
				// 파일 저장
				File destFile = new File(filePath);
				uploadFile.transferTo(destFile);
				
				question.setFileData(uniqueFilename);
					} catch (IOException e) {
					// 파일 업로드 중 오류 처리
					e.printStackTrace();
					}
				}
		questionService.addSecretQuestion(question);
		return "redirect:/community/qna_list";
	}
	
	 	/*QNA 글 상세 페이지*/
	   @RequestMapping(value = "/community/qna_detail", method = RequestMethod.GET)
	   public String questionDetail(@RequestParam int idx, Model model, Authentication authentication)  {
		  CustomAccountDetails loginAccount = (CustomAccountDetails)authentication.getPrincipal();
	      Question question = questionService.getQuestion(idx);
	      
	      model.addAttribute("loginAccount", loginAccount);
		    model.addAttribute("question", question);
		    questionService.modifyQuestionCount(idx);
		    // 비밀글이라면
		    if (question.getSecret() == 1) {
		        // 비로그인 사용자라면
		        if (loginAccount == null) {
		            return "account/login"; // 로그인 페이지로 리다이렉트
		        }
		        // 관리자 계정이거나, 글 작성자라면
		        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")) || loginAccount.getId().equals(question.getAccountId())) {
	            return "community/qna_detail";
	         }
	         return "redirect:/community/qna_list";
	      }
	      
	      //이전글 다음글 - 추가
	      model.addAttribute("preNextIdx", questionService.getPreNextidx(idx)); 
	      
	      
	      return "community/qna_detail";
	   }
	
	/*QNA 글 삭제*/
	@PreAuthorize("hasRole('ROLE_ADMIN') or principal.id eq #question.accountId)")
	@RequestMapping(value = "/community/question/delete", method = RequestMethod.GET)
	public String deleteQuestion(@RequestParam("idx") int idx) {
	    questionService.removeQuestion(idx);
	    return "redirect:/community/qna_list";
	}
	

	/*QNA 답변 삭제*/
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/community/question/delete_by_admin", method = RequestMethod.GET)
	public String deleteQuestionByAdmin(@RequestParam("idx") int idx) {
	    questionService.removeQuestionByAdmin(idx);
	    return "redirect:/community/qna_detail?idx="+idx;
	}
}
