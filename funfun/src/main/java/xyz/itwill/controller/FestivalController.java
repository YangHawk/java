package xyz.itwill.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dto.Account;
import xyz.itwill.dto.Donation;
import xyz.itwill.dto.Expect;
import xyz.itwill.dto.Festival;
import xyz.itwill.dto.Wish;
import xyz.itwill.exception.FestivalinfoNotFoundException;
import xyz.itwill.security.CustomAccountDetails;
import xyz.itwill.service.DonationService;
import xyz.itwill.service.ExpectService;
import xyz.itwill.service.FestivalService;
import xyz.itwill.service.FilmService;
import xyz.itwill.service.WishService;

@Controller
@RequestMapping("/donation")
@RequiredArgsConstructor
public class FestivalController {
	private final WebApplicationContext context;
	
	private final FestivalService festivalService;
	private final FilmService filmService;
	private final WishService wishService;
	private final ExpectService expectService;
	private final DonationService donationService;

	@RequestMapping(value = "/single-festival", method = RequestMethod.GET)
	public String showSingleFestival(@RequestParam int idx, Model model, Authentication authentication) throws FestivalinfoNotFoundException {
		CustomAccountDetails loginAccount = null;
		    
		//현재 로그인한 사용자 객체가 CustomAccountDetails 클래스의 인스턴스인지 확인
		if (authentication != null && authentication.getPrincipal() instanceof CustomAccountDetails) {
		    loginAccount = (CustomAccountDetails) authentication.getPrincipal();
		}
		double achievementPercentage = festivalService.calcAchievementPercentage(idx);
		model.addAttribute("festivalinfo", festivalService.getFestival(idx));
		model.addAttribute("filminfo", filmService.getFilmList(idx));
		model.addAttribute("expectinfo", expectService.getExpectList(idx));
		model.addAttribute("achievementPercentage", achievementPercentage);
		if(loginAccount != null) {
			List<Donation> donationinfo = donationService.getDonation(loginAccount.getId(), idx);
			if (donationinfo != null && !donationinfo.isEmpty()) {
				model.addAttribute("donationinfo", donationinfo);
			}
			Expect expectaccount = expectService.getExpectAccountId(loginAccount.getId(), idx);
			
			if(expectaccount != null) {
				model.addAttribute("expectAccountId", expectaccount);
			}
			//오류 발생 : Request processing failed; nested exception is org.mybatis.spring.MyBatisSystemException: nested exception is org.apache.ibatis.binding.BindingException: 
			//Parameter 'festivalIdx' not found. Available parameters are [arg1, arg0, param1, param2]
			//=> MyBatis를 이용한 SQL Mapper의 메소드는 하나의 파라미터만을 읽어 발생하는 오류
			//해결방안 :Mapper에서 변수명을 arg로 맞추어 사용하기 
			Wish wishThisFestival = wishService.getMyWishFestival(idx, loginAccount.getId());
			if(wishThisFestival != null) {
				model.addAttribute("wishThisFestival", wishThisFestival);
			}
		}
		
		return "donation/single-festival";
	}

	@PreAuthorize("hasRole('ROLE_REGISTER')")
	@GetMapping(value = "/festival_register")
	public String addfestival(Authentication authentication) {
		CustomAccountDetails loginAccount = (CustomAccountDetails)authentication.getPrincipal();
		if(loginAccount == null) {
			return "redirect:/account/login";
		}
		//account_auth에서 가져오기
		if(loginAccount.getStatus() != 1) {
			return "redirect:/";
		}
		return "donation/festival_register";
	}


	@PreAuthorize("hasRole('ROLE_REGISTER')")
	@PostMapping(value = "/festival_register")
	public String addfestival(@ModelAttribute("festival") Festival festival, @RequestParam("mainMultipartFile") MultipartFile mainMultipartFile,
            @RequestParam("subMultipartFile") MultipartFile subMultipartFile, Model model, RedirectAttributes redirectAttributes) throws IllegalStateException, IOException, FestivalinfoNotFoundException {
		if (festival.getMainMultipartFile().isEmpty() || festival.getSubMultipartFile().isEmpty()) {
	        // 이미지 파일이 업로드되지 않은 경우 처리
			int festivalIdx = festival.getIdx();
			model.addAttribute("festivalinfo", festivalService.getFestival(festivalIdx));
			model.addAttribute("message", "이미지 파일이 업로드되지 않았습니다.");
	        return "donation/festival_register";
	    }
		//전달파일을 저장하기 위한 서버 디렉토리의 시스템 경로 반환
		String uploadDirectory=context.getServletContext().getRealPath("/resources/upload");
		
		//서버 디렉토리에 업로드 처리되어 저장된 파일의 이름을 반환받아 Command 객체의 필드값 변경
		String uploadMainImg=UUID.randomUUID().toString()+"_"+mainMultipartFile.getOriginalFilename();
		festival.setMainImg(uploadMainImg);
		String uploadSubImg=UUID.randomUUID().toString()+"_"+subMultipartFile.getOriginalFilename();
		festival.setSubImg(uploadSubImg);
		
		//파일 업로드 처리
		festival.getMainMultipartFile().transferTo(new File(uploadDirectory, uploadMainImg));
		festival.getSubMultipartFile().transferTo(new File(uploadDirectory, uploadSubImg));
		
		//FESTIVAL 테이블에 행 삽입
		festivalService.addFestival(festival);
		
		redirectAttributes.addAttribute("festivalIdx", festival.getIdx());
		redirectAttributes.addFlashAttribute("message", "영화제가 등록되었습니다.");
		
		return "redirect:/donation/film_register";
	}
	
	@PreAuthorize("hasRole('ROLE_REGISTER') and principal.id eq #id")
	@RequestMapping(value = "/festival_update", method = RequestMethod.GET)
	public String modifyFestival(@RequestParam int idx, @RequestParam String id, Model model, HttpServletRequest request) throws FestivalinfoNotFoundException {
		Festival festivalinfo = festivalService.getFestival(idx);
		if(festivalinfo == null) {
			throw new FestivalinfoNotFoundException("해당 영화제가 존재하지 않습니다.");
		}
		
		model.addAttribute("festivalinfo",festivalinfo);
		String command=request.getHeader("referer");
		model.addAttribute("command", command);
		return "donation/festival_update";
	}
	
	@PreAuthorize("hasRole('ROLE_REGISTER')")
	@RequestMapping(value = "/festival_update", method = RequestMethod.POST)
	public String modifyFestival(@ModelAttribute("festival") Festival festival, @RequestParam("mainMultipartFile") MultipartFile mainMultipartFile,
            @RequestParam("subMultipartFile") MultipartFile subMultipartFile, Model model, @RequestParam("command") String command) throws FestivalinfoNotFoundException, IllegalStateException, IOException {
		//전달파일을 저장하기 위한 서버 디렉토리의 시스템 경로 반환
		String uploadDirectory=context.getServletContext().getRealPath("/resources/upload");
		Festival originalFestival = festivalService.getFestival(festival.getIdx());
		
		if (festival.getMainMultipartFile().isEmpty()) {
			String originalMainImg = originalFestival.getMainImg();
			festival.setMainImg(originalMainImg);
		}else {
			//서버 디렉토리에 업로드 처리되어 저장된 파일의 이름을 반환받아 Command 객체의 필드값 변경
			String uploadMainImg=UUID.randomUUID().toString()+"_"+mainMultipartFile.getOriginalFilename();
			festival.setMainImg(uploadMainImg);
			//파일 업로드 처리
			festival.getMainMultipartFile().transferTo(new File(uploadDirectory, uploadMainImg));
		}
		
		if(festival.getSubMultipartFile().isEmpty()) {
			String originalSubImg = originalFestival.getSubImg();
			festival.setSubImg(originalSubImg);
	    }else {
	    	String uploadSubImg=UUID.randomUUID().toString()+"_"+subMultipartFile.getOriginalFilename();
			festival.setSubImg(uploadSubImg);
			festival.getSubMultipartFile().transferTo(new File(uploadDirectory, uploadSubImg));
	    }
		
		//FESTIVAL 테이블에 행 삽입
		festivalService.modifyFestival(festival);
		
		return "redirect:"+command;
	}
	
	
}