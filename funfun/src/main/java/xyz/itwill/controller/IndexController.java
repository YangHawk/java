package xyz.itwill.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import xyz.itwill.dto.Expect;
import xyz.itwill.dto.Festival;
import xyz.itwill.exception.FestivalinfoNotFoundException;
import xyz.itwill.service.ExpectService;
import xyz.itwill.service.FestivalService;

@Controller
@RequiredArgsConstructor
public class IndexController {
	private final FestivalService festivalService;
	private final ExpectService expectService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model) throws FestivalinfoNotFoundException {
		List<Festival> festivalList = festivalService.getOngoingFestival();
		model.addAttribute("festivalList", festivalList);
		
		List<Festival> indexExpectFestival = festivalService.getIndexExpectFestival();
		Map<Integer, List<Expect>> expectListMap = new HashMap<>();
		
		List<Double> starAvgList=new ArrayList<Double>();
		for (Festival expectFestival : indexExpectFestival) {
			List<Expect> expectList = expectService.getExpectList(expectFestival.getIdx());
			double starAvg = expectService.getStarAvg(expectFestival.getIdx());
			starAvgList.add(starAvg);
			expectListMap.put(expectFestival.getIdx(), expectList);
		}
		
		model.addAttribute("indexMainFestival", festivalService.getIndexMainFestival());
		model.addAttribute("expectList", expectService.getRandomExpectList());
		model.addAttribute("indexExpectFestival", indexExpectFestival);
		model.addAttribute("expectListMap", expectListMap); // Map을 모델에 추가
		model.addAttribute("starAvgList", starAvgList);
		return "index";
	}
}
