package xyz.itwill.mapper;

import java.util.List;
import java.util.Map;

import xyz.itwill.dto.Notice;

public interface NoticeMapper {
	int insertNotice(Notice notice);

	int updateNotice(Notice notice);

	int deleteNotice(int idx);

	int updateNoticeCount(int idx);
	
	Notice selectNotice(int idx);

	int selectNoticeCount(String selectKeyword);

	List<Notice> selectNoticeList(Map<String, Object> map);
	
	Notice selectPreNextNoticeidx(int idx);
}

