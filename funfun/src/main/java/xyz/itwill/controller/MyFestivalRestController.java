package xyz.itwill.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
import xyz.itwill.security.CustomAccountDetails;
import xyz.itwill.service.FestivalService;
import xyz.itwill.service.FilmService;

@RestController
@RequiredArgsConstructor
public class MyFestivalRestController {
	private final FestivalService festivalService;
	private final FilmService filmService;
	
	@PreAuthorize("hasRole('ROLE_REGISTER') and principal.id eq #id")
	@GetMapping("/my_festival/{id}")
	public Map<String, Object> getMyFestival(@PathVariable String id, Model model) {
		   Map<String, Object> myFestivalMap = new HashMap<String, Object>();
	       myFestivalMap.put("approvedFestival", festivalService.getApprovedFestivalList(id));
	       myFestivalMap.put("pendingFestival", festivalService.getPendingFestivalList(id));
	       myFestivalMap.put("rejectedFestival", festivalService.getRejectedFestivalList(id));
	       
		   return myFestivalMap;
	}
	
	@PreAuthorize("hasRole('ROLE_REGISTER') and principal.id eq #festival.accountId")
	@GetMapping("/remove_festival")
	public String removeFestival(@ModelAttribute Festival festival) throws FestivalinfoNotFoundException {
		Festival deleteFestival=festivalService.getFestival(festival.getIdx());
		deleteFestival.setState(5);
		festivalService.modifyFestivalState(deleteFestival);
		filmService.modifyFilmStatusList(deleteFestival.getIdx());
		return "success";
	}
	
	@PreAuthorize("hasRole('ROLE_REGISTER')")
	@GetMapping("/get_film_list/{idx}")
	public List<Film> getFilm(@PathVariable int idx){
		List<Film> filmList = filmService.getFilmList(idx);
		return filmList;
	}
	
	@PreAuthorize("hasRole('ROLE_REGISTER') and principal.id eq #film.accountId")
	@GetMapping("/remove_film")
	public String removeFilm(@ModelAttribute Film film) {
		filmService.modifyFilmStatus(film.getIdx());
		return "success";
	}
	
}
