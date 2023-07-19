package xyz.itwill.dto;

// MYREPLY 테이블과 MYUSER 테이블의 컬럼값을 저장하기 위한 클래스
// ▶ 1:1 관계의 테이블 조인에 대한 검색 결과를 저장하기 위한 클래스
public class MyReplyUser {
  
  /*
  
  private int replyNo;
  private String replyId;
  private String replyContent;
  private String replyDate;
  private int replyCommentNo;
  
  private String userId;
  private String userName;
  
   */

  // MYREPLY 테이블(댓글 정보)의 검색행을 객체로 제공받아 저장하기 위한 필드 - 검색행 1개
  private MyReply reply;

  // MYUSER 테이블(회원 정보)의 검색행을 객체로 제공받아 저장하기 위한 필드 - 검색행 1개
  private MyUser user;

  public MyReplyUser() {
    // TODO Auto-generated constructor stub
  }

  public MyReply getReply() {
    return reply;
  }

  public void setReply(MyReply reply) {
    this.reply = reply;
  }

  public MyUser getUser() {
    return user;
  }

  public void setUser(MyUser user) {
    this.user = user;
  }
}