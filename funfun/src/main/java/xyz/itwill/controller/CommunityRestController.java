package xyz.itwill.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dto.Notice;
import xyz.itwill.dto.Question;
import xyz.itwill.service.NoticeService;
import xyz.itwill.service.QuestionService;

@RestController
@RequestMapping("/community")
@RequiredArgsConstructor
public class CommunityRestController {
   private final NoticeService noticeService;
   private final QuestionService questionService;

   @RequestMapping(value = "/notice_list", method = RequestMethod.GET)//수정해야하는 부분
   public Map<String, Object> getNotice(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "") String selectKeyword) {
      return noticeService.getNoticeList(pageNum, pageSize, selectKeyword);
   }
   @RequestMapping(value = "/qna_lists", method = RequestMethod.GET)
   public Map<String, Object> getQuestion(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "") String selectKeyword) {
      return questionService.getQuestionList(pageNum, pageSize, selectKeyword);
   }
   
   /*
   @PutMapping("/notice_modify")
   public String modifyNotice(@RequestBody Notice notice, HttpSession httpSession) {
      notice.setTitle(HtmlUtils.htmlEscape(notice.getTitle()));
      notice.setContent(HtmlUtils.htmlEscape(notice.getContent()));
       
      noticeService.modifyNotice(notice); 
       
       return "success";
    }
   */
   @PreAuthorize("hasRole('ROLE_ADMIN')")
   @PostMapping("/notice/notice_modify")
   public String modifyNotice(@ModelAttribute Notice notice,
                              @RequestParam("uploadFile") MultipartFile uploadFile,
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
       // 공지사항 수정 로직
       noticeService.modifyNotice(notice);

       return "success"; // 클라이언트에게 성공 메시지를 반환
   }
   
   @PreAuthorize("hasRole('ROLE_USER')")
   @PostMapping("/question_modify")
   public String modifyQuestion(@ModelAttribute Question question,
           @RequestParam("uploadFile") MultipartFile uploadFile,
           @RequestParam("fileDeleted") boolean fileDeleted,
           HttpServletRequest request) throws IllegalStateException, IOException {
       Question existingQuestion = questionService.getQuestion(question.getIdx());

       if (fileDeleted) { // 파일 삭제되었을 경우
    	    question.setFileData(null); // 파일을 삭제하므로 null로 설정
    	} else { // 파일 삭제되지 않았을 경우
    	    if (!uploadFile.isEmpty()) { // 업로드 파일이 있을 경우에만 업로드 처리
    	        String uploadDirectory = request.getServletContext().getRealPath("/resources/upload");
    	        String originalFilename = uploadFile.getOriginalFilename();
    	        String uniqueFilename = UUID.randomUUID().toString() + "_" + originalFilename;

    	        // 새로 업로드한 파일로 설정
    	        question.setFileData(uniqueFilename);

    	        // 파일 저장
    	        uploadFile.transferTo(new File(uploadDirectory, uniqueFilename));
    	    } else { // 업로드 파일이 없으면 기존 파일 정보를 그대로 사용
    	        question.setFileData(existingQuestion.getFileData());
    	    }
    	}

       // 문의글 수정 로직
       questionService.modifyQuestionByUser(question);

       return "success"; // 클라이언트에게 성공 메시지를 반환
   }
}