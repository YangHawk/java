package xyz.itwill.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import xyz.itwill.dto.MyMember;
import xyz.itwill.mapper.MyMemberMapper;

public class MyMemberDAO {
  private static MyMemberDAO _dao;

  public MyMemberDAO() {
    // TODO Auto-generated constructor stub
  }

  static {
    _dao = new MyMemberDAO();
  }

  public static MyMemberDAO getDAO() {
    return _dao;
  }

  private SqlSessionFactory getSqlSessionFactory() {
    String resource = "mybatis-config.xml";
    InputStream inputStream = null;
    try {
      inputStream = Resources.getResourceAsStream(resource);
    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
    return new SqlSessionFactoryBuilder().build(inputStream);
  }

  // 회원 정보를 전달받아 MYMMEMBER 테이블의 회원 정보로 삽입하고 삽입 행의 갯수를 반환하는 메소드
  public int insertMyMember(MyMember member) {
    SqlSession sqlSession = getSqlSessionFactory().openSession(true);
    try {
      return sqlSession.getMapper(MyMemberMapper.class).insertMyMember(member);
    } finally {
      sqlSession.close();
    }

  }

  // 회원 정보를 전달받아 MYMMEMBER 테이블에 저장된 회원 정보를 변경하고 변경 행의 갯수를 반환하는 메소드
  public int updateMyMember(MyMember member) {
    SqlSession sqlSession = getSqlSessionFactory().openSession(true);
    try {
      return sqlSession.getMapper(MyMemberMapper.class).updateMyMember(member);
    } finally {
      sqlSession.close();
    }
  }

  // 아이디를 전달받아 MYMMEMBER 테이블에 저장된 회원 정보를 삭제하고 삭제 행의 갯수를 반환하는 메소드
  public int deleteMyMember(String id) {
    SqlSession sqlSession = getSqlSessionFactory().openSession(true);
    try {
      return sqlSession.getMapper(MyMemberMapper.class).deleteMyMember(id);
    } finally {
      sqlSession.close();
    }
  }

  // 아이디를 전달받아 MYMMEMBER 테이블의 저장된 회원 정보를 검색하여 DTO 객체로 반환하는 메소드
  public MyMember selectMyMember(String id) {
    SqlSession sqlSession = getSqlSessionFactory().openSession(true);
    try {
      return sqlSession.getMapper(MyMemberMapper.class).selectMyMember(id);
    } finally {
      sqlSession.close();
    }
  }

  // MYMEMBER 테이블에 저장된 모든 회원 정보를 검색하여 List 객체로 반환하는 메소드
  public List<MyMember> selectMyMemberList() {
    SqlSession sqlSession = getSqlSessionFactory().openSession(true);
    try {
      return sqlSession.getMapper(MyMemberMapper.class).selectMyMemberList();
    } finally {
      sqlSession.close();
    }
  }
}
