package xyz.itwill.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dao.NoticeDAO;
import xyz.itwill.dto.Notice;
import xyz.itwill.util.Pager;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
    private final NoticeDAO noticeDAO;

    @Override
    public int addNotice(Notice notice) {
        return noticeDAO.insertNotice(notice);
    }

    @Override
    public int modifyNotice(Notice notice) {
        return noticeDAO.updateNotice(notice);
    }

    @Override
    public int removeNotice(int idx) {
        return noticeDAO.deleteNotice(idx);
    }

    @Override
    public int modifyNoticeCount(int idx) {
    	return noticeDAO.updateNoticeCount(idx);
    }
    
    @Override
    public Notice getNotice(int idx) {
        return noticeDAO.selectNotice(idx);
    }
    
    @Override
    public Map<String, Object> getNoticeList(int pageNum, int pageSize, String selectKeyword) {
    	int totalBoard = noticeDAO.selectNoticeCount(selectKeyword); //전체 공지사항 개수 조회
    	int blockSize = 10; //페이지 블록 크기
    	
    	Pager pager = new Pager(pageNum, totalBoard, pageSize, blockSize);
    	
    	Map<String, Object> pageMap = new HashMap<String, Object>(); //페이지 정보 담을 맵 객체
		pageMap.put("startRow", pager.getStartRow()); //시작 행 번호 추가
		pageMap.put("endRow", pager.getEndRow()); //마지막 행 번호 추가
		pageMap.put("selectKeyword", selectKeyword); //검색 키워드 추가
    	
		List<Notice> noticeList = noticeDAO.selectNoticeList(pageMap);
		
        Map<String, Object> noticeMap = new HashMap<String, Object>();
        noticeMap.put("noticeList", noticeList);
        noticeMap.put("pager", pager);
        
        return noticeMap;
    }
    
    @Override
    public Notice getPreNextNoticeidx(int idx) {
       return noticeDAO.selectPreNextNoticeidx(idx);
    }
}
