package xyz.itwill.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
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

	@PreAuthorize("hasRole('ROLE_REGISTER')")
	@GetMapping("/my_festival")
	public Map<String, Object> getMyFestival(Model model, Authentication authentication) {
		CustomAccountDetails loginAccount = (CustomAccountDetails)authentication.getPrincipal();
		
		model.addAttribute("loginAccount", loginAccount);
		Map<String, Object> myFestivalMap = new HashMap<String, Object>();
		myFestivalMap.put("approvedFestival", festivalService.getApprovedFestivalList(loginAccount.getId()));
		myFestivalMap.put("pendingFestival", festivalService.getPendingFestivalList(loginAccount.getId()));
		myFestivalMap.put("rejectedFestival", festivalService.getRejectedFestivalList(loginAccount.getId()));

		return myFestivalMap;
	}

	@PreAuthorize("hasRole('ROLE_REGISTER')")
	@GetMapping("/remove_festival/{idx}")
	public String removeFestival(@PathVariable int idx) throws FestivalinfoNotFoundException {
		Festival festival = festivalService.getFestival(idx);
		festival.setState(5);
		festivalService.modifyFestivalState(festival);
		filmService.modifyFilmStatusList(festival.getIdx());
		return "success";
	}

	@PreAuthorize("hasRole('ROLE_REGISTER')")
	@GetMapping("/get_film_list/{idx}")
	public List<Film> getFilm(@PathVariable int idx) {
		List<Film> filmList = filmService.getFilmList(idx);
		return filmList;
	}

	@PreAuthorize("hasRole('ROLE_REGISTER')")
	@GetMapping("/remove_film/{idx}")
	public String removeFilm(@PathVariable int idx) {
		filmService.modifyFilmStatus(idx);
		return "success";
	}

}
