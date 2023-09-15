package xyz.itwill.service;

import java.util.Map;
import xyz.itwill.dto.Notice;

public interface NoticeService {
    // 공지사항 추가
    int addNotice(Notice notice);

    // 공지사항 수정
    int modifyNotice(Notice notice);

    // 공지사항 삭제
    int removeNotice(int idx);

    int modifyNoticeCount(int idx);
    
    // 특정 공지사항 상세 조회
    Notice getNotice(int idx);

    // 공지사항 목록 조회
    Map<String, Object> getNoticeList(int pageNum, int pageSize, String selectKeyword);
    
    Notice getPreNextNoticeidx(int idx);
}
