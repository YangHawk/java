package xyz.itwill.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import xyz.itwill.dto.Account;
import xyz.itwill.dto.Festival;
import xyz.itwill.dto.Film;
import xyz.itwill.exception.FestivalinfoNotFoundException;
import xyz.itwill.service.FestivalService;
import xyz.itwill.service.FilmService;

@RestController
@RequiredArgsConstructor
public class MyFestivalRestController {
	private final FestivalService festivalService;
	private final FilmService filmService;
	
	@GetMapping("/my_festival")
	public Map<String, Object> getMyFestival(Model model,HttpSession session) {
		   Account loginAccount = (Account)session.getAttribute("loginAccount");
		   if (loginAccount == null) {
				return null;
		   }
		   model.addAttribute("loginAccount", loginAccount);
	       Map<String, Object> myFestivalMap = new HashMap<String, Object>();
	       myFestivalMap.put("approvedFestival", festivalService.getApprovedFestivalList(loginAccount.getId()));
	       myFestivalMap.put("pendingFestival", festivalService.getPendingFestivalList(loginAccount.getId()));
	       myFestivalMap.put("rejectedFestival", festivalService.getRejectedFestivalList(loginAccount.getId()));
	       
		   return myFestivalMap;
	}
	
	@GetMapping("/remove_festival/{idx}")
	public String removeFestival(@PathVariable int idx) throws FestivalinfoNotFoundException {
		Festival festival=festivalService.getFestival(idx);
		festival.setState(5);
		festivalService.modifyFestivalState(festival);
		filmService.modifyFilmStatusList(festival.getIdx());
		return "success";
	}
	
	
	@GetMapping("/get_film_list/{idx}")
	public List<Film> getFilm(@PathVariable int idx){
		List<Film> filmList = filmService.getFilmList(idx);
		return filmList;
	}
	
	@GetMapping("/remove_film/{idx}")
	public String removeFilm(@PathVariable int idx) {
		filmService.modifyFilmStatus(idx);
		return "success";
	}
	
}
