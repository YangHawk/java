package xyz.itwill.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dto.Film;
import xyz.itwill.exception.FestivalinfoNotFoundException;
import xyz.itwill.service.FilmService;

@Controller
@RequestMapping("/donation")
@RequiredArgsConstructor
public class FilmController {
	private final WebApplicationContext context;
	private final FilmService filmService;
	
	@PreAuthorize("hasRole('ROLE_REGISTER')")
	@RequestMapping(value = "/film_register", method = RequestMethod.GET)
	public String filmRegister(@RequestParam int festivalIdx, Model model, HttpServletRequest request) {
		model.addAttribute("festivalIdx", festivalIdx);
		String command=request.getHeader("referer");
		model.addAttribute("command", command);
		return "donation/film_register";
	}
	
	@PreAuthorize("hasRole('ROLE_REGISTER')")
	@RequestMapping(value = "/film_register", method = RequestMethod.POST)
	public String filmRegister(@ModelAttribute Film film, @RequestParam("multipartFile") MultipartFile multipartFile
			, Model model, @RequestParam("command") String command )  throws IllegalStateException, IOException  {
		if(film.getMultipartFile().isEmpty()) {
			int filmIdx = film.getIdx();
			model.addAttribute("filminfo", filmService.getFilm(filmIdx));
			model.addAttribute("message", "이미지 파일이 업로드되지 않았습니다.");
			return "donation/film_register";
		}
		
		//전달파일을 저장하기 위한 서버 디렉토리의 시스템 경로 반환
		String uploadDirectory=context.getServletContext().getRealPath("/resources/upload");
		
		String uploadImg=UUID.randomUUID().toString()+"_"+multipartFile.getOriginalFilename();
		film.setImg(uploadImg);
		
		film.getMultipartFile().transferTo(new File(uploadDirectory, uploadImg));
		
		//유튜브 api 사용해보기 - 추가로 구현해볼것
		String video = film.getVideo().replace("https://youtu.be/", "https://www.youtube.com/embed/");
		film.setVideo(video);
		model.addAttribute("command", command);
		
		if(filmService.checkDuplicated(film)) {
			model.addAttribute("message","제목이 중복되었습니다.");
			return "donation/film_register";
		}
		
		filmService.addFilm(film);
		model.addAttribute("festivalIdx", film.getFestivalIdx());
		return "donation/film_register";
	}
	
	@PreAuthorize("hasRole('ROLE_REGISTER')")
	@RequestMapping(value = "/film_update", method = RequestMethod.GET)
	public String modifyFilm(@RequestParam int idx, Model model, HttpServletRequest request) throws FestivalinfoNotFoundException {
		model.addAttribute("filminfo", filmService.getFilm(idx));
		String command=request.getHeader("referer");
		model.addAttribute("command", command);
		return "donation/film_update";
	}
	
	@PreAuthorize("hasRole('ROLE_REGISTER') and principal.id eq #film.accountId")
	@RequestMapping(value = "/film_update", method = RequestMethod.POST)
	public String modifyFilm(@ModelAttribute Film film, @RequestParam("multipartFile") MultipartFile multipartFile
			, Model model,@RequestParam("command") String command, HttpServletRequest request) throws IllegalStateException, IOException {
		if(film.getMultipartFile().isEmpty()) {
			// 이미지 파일이 업로드되지 않은 경우 처리
			//model.addAttribute("message", "이미지 파일이 업로드되지 않았습니다.");
			//return "donation/film_update";
			String origianlImg =filmService.getFilm(film.getIdx()).getImg();
			film.setImg(origianlImg);
		}else {
			//전달파일을 저장하기 위한 서버 디렉토리의 시스템 경로 반환
			String uploadDirectory=context.getServletContext().getRealPath("/resources/upload");
			
			String uploadImg=UUID.randomUUID().toString()+"_"+multipartFile.getOriginalFilename();
			film.setImg(uploadImg);
			
			film.getMultipartFile().transferTo(new File(uploadDirectory, uploadImg));
		}
		
		//유튜브 api 사용해보기 - 추가로 구현해볼것
		String video = film.getVideo().replace("https://youtu.be/", "https://www.youtube.com/embed/");
		film.setVideo(video);
		
		filmService.modifyFilm(film);
		
		return "redirect:"+command;
	}
	
	@PreAuthorize("hasRole('ROLE_REGISTER')")
	@RequestMapping("/film_remove")
	public String removeFilm(@RequestParam int idx, Model model) {
		filmService.modifyFilmStatus(idx);
		model.addAttribute("idx", filmService.getFilm(idx).getFestivalIdx());
		return "redirect:/donation/single-festival";
	}
}