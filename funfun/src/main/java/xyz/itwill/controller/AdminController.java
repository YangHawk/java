package xyz.itwill.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dto.Account;
import xyz.itwill.exception.FestivalinfoNotFoundException;
import xyz.itwill.exception.UserinfoNotFoundException;
import xyz.itwill.service.AccountService;
import xyz.itwill.service.ExpectService;
import xyz.itwill.service.FestivalService;
import xyz.itwill.service.FilmService;
import xyz.itwill.service.NoticeService;
import xyz.itwill.service.QuestionService;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
	private final AccountService accountService;
	private final FestivalService festivalService;
	private final QuestionService questionService;
	private final FilmService filmService;
	private final ExpectService expectService;
	private final NoticeService noticeService;

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String getInfoList(HttpSession session) {
		Account loginAccount = (Account) session.getAttribute("loginAccount");
		if (loginAccount == null) {
			return "redirect:/account/login";
		}
		if (loginAccount.getStatus() != 0) {
			return "redirect:/";
		}
		return "admin/admin"; // JSP 파일의 경로와 이름
	}

	@RequestMapping(value = "/account_detail", method = RequestMethod.GET)
	public String getAccountDetail(@RequestParam String id, Model model, HttpSession session)
			throws UserinfoNotFoundException {
		model.addAttribute("account", accountService.getAccount(id));
		return "admin/detail/account_detail";
	}

	@RequestMapping(value = "/page_manage", method = RequestMethod.GET)
	public String getManagePage(HttpSession session) throws UserinfoNotFoundException {
		return "admin/detail/page_manage";
	}

	@RequestMapping(value = "/account_add", method = RequestMethod.GET)
	public String getAccountAdd(HttpSession session) {
		return "admin/detail/account_add";
	}

	@RequestMapping(value = "/notice_add", method = RequestMethod.GET)
	public String getNoticeAdd(HttpSession session) {
		return "admin/detail/notice_add";
	}

	@RequestMapping(value = "/festival_detail", method = RequestMethod.GET)
	public String getFestivalDetail(@RequestParam int idx, Model model, HttpSession session)
			throws FestivalinfoNotFoundException {

		model.addAttribute("festival", festivalService.getFestival(idx));
		Account loginAccount = (Account) session.getAttribute("loginAccount");
		model.addAttribute("loginAccount", loginAccount);
		double achievementPercentage = festivalService.calcAchievementPercentage(idx);
		model.addAttribute("filminfo", filmService.getFilmList(idx));
		model.addAttribute("expectinfo", expectService.getExpectList(idx));
		model.addAttribute("achievementPercentage", achievementPercentage);

		return "admin/detail/festival_detail";
	}

	@RequestMapping(value = "/question_detail", method = RequestMethod.GET)
	public String getQuestionDetail(@RequestParam int idx, Model model, HttpSession session) {
		model.addAttribute("question", questionService.getQuestion(idx));
		Account loginAccount = (Account) session.getAttribute("loginAccount");
		model.addAttribute("loginAccount", loginAccount);

		return "admin/detail/question_detail";
	}

	@RequestMapping(value = "/notice_detail", method = RequestMethod.GET)
	public String getnoticeDetail(@RequestParam int idx, Model model, HttpSession session) {
		model.addAttribute("notice", noticeService.getNotice(idx));

		return "admin/detail/notice_detail";
	}

}