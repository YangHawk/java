package xyz.itwill.dao;

import java.util.List;
import java.util.Map;

import xyz.itwill.dto.Notice;

public interface NoticeDAO {
    // 공지사항 추가
    int insertNotice(Notice notice);

    // 공지사항 수정
    int updateNotice(Notice notice);

    // 공지사항 삭제
    int deleteNotice(int idx);
    
    //공지사항 조회수
    int updateNoticeCount(int idx);

    // 특정 공지사항 상세 조회
    Notice selectNotice(int idx);

    // 전체 공지사항 개수 조회
    int selectNoticeCount(String selectKeyword);

    // 공지사항 목록 조회
    List<Notice> selectNoticeList(Map<String, Object> map);
    
    Notice selectPreNextNoticeidx(int idx);
}
