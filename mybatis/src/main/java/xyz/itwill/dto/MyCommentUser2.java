package xyz.itwill.dto;

// MYCOMMENT 테이블과 MYUSER 테이블의 컬럼값을 저장하기 위한 클래스
// ▶ 1:1 관계의 테이블 조인에 대한 검색 결과를 저장하기 위한 클래스
// 포함 관계를 이용하여 클래스 작성 - 기존에 선언된 클래스를 재사용하여 새로운 클래스 작성
// ▶ 생산성 및 유지 보수의 효율성 증가
public class MyCommentUser2 {
  // MYCOMMENT 테이블(게시글)의 검색행을 객체로 제공받아 저장하기 위한 필드
  // ▶ 필드에 반드시 객체를 저장해야만 포함 관계가 성립
  // ▶ 검색행을 객체로 제공받아 필드에 저장하기 위하여 반드시 수동 매핑 이용
  private MyComment1 comment;

  // MYUSER 테이블(회원 정보)의 검색행을 객체로 제공받아 저장하기 위한 필드
  private MyUser user;

  public MyCommentUser2() {
    // TODO Auto-generated constructor stub
  }

  public MyComment1 getComment() {
    return comment;
  }

  public void setComment(MyComment1 comment) {
    this.comment = comment;
  }

  public MyUser getUser() {
    return user;
  }

  public void setUser(MyUser user) {
    this.user = user;
  }
}
