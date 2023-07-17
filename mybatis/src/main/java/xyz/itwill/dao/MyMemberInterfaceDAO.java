package xyz.itwill.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import xyz.itwill.dto.MyMember;
import xyz.itwill.mapper.MymemberInterfaceMapper;

public class MyMemberInterfaceDAO {
  private static MyMemberInterfaceDAO _dao;

  public MyMemberInterfaceDAO() {
    // TODO Auto-generated constructor stub
  }

  static {
    _dao = new MyMemberInterfaceDAO();
  }

  public static MyMemberInterfaceDAO getDAO() {
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
      // SqlSession.getMapper(Class<T> clazz): 매개변수 메모리에 저장된 인터페이스(Class 객체 = Clazz)를 전달받아 Mapper
      // 객체로 생성하여 반환하는 메소드
      // ▶ 매개 변수에 [XXX.class] 형식으로 인터페이스를 Class 객체로 직접 표현하여 전달
      // Mapper 객체: 인터페이스 기반의 매퍼 파일을 제공받아 Mapper 객체로 생성되어 추상 메소드를 호출하여 추상 메소드에 등록된 SQL 명령을 DBMS 서버에
      // 전달하여 실행하고 실행 결과를 Java 객체로 매핑하여 반환하는 기능을 제공하는 객체
      return sqlSession.getMapper(MymemberInterfaceMapper.class).insertMyMember(member);
    } finally {
      sqlSession.close();
    }

  }

  // 회원 정보를 전달받아 MYMMEMBER 테이블에 저장된 회원 정보를 변경하고 변경 행의 갯수를 반환하는 메소드
  public int updateMyMember(MyMember member) {
    SqlSession sqlSession = getSqlSessionFactory().openSession(true);
    try {
      return sqlSession.getMapper(MymemberInterfaceMapper.class).updateMyMember(member);
    } finally {
      sqlSession.close();
    }
  }

  // 아이디를 전달받아 MYMMEMBER 테이블에 저장된 회원 정보를 삭제하고 삭제 행의 갯수를 반환하는 메소드
  public int deleteMyMember(String id) {
    SqlSession sqlSession = getSqlSessionFactory().openSession(true);
    try {
      return sqlSession.getMapper(MymemberInterfaceMapper.class).deleteMyMember(id);
    } finally {
      sqlSession.close();
    }
  }

  // 아이디를 전달받아 MYMMEMBER 테이블의 저장된 회원 정보를 검색하여 DTO 객체로 반환하는 메소드
  public MyMember selectMyMember(String id) {
    SqlSession sqlSession = getSqlSessionFactory().openSession(true);
    try {
      return sqlSession.getMapper(MymemberInterfaceMapper.class).selectMyMember(id);
    } finally {
      sqlSession.close();
    }
  }

  // MYMEMBER 테이블에 저장된 모든 회원 정보를 검색하여 List 객체로 반환하는 메소드
  public List<MyMember> selectMyMemberList() {
    SqlSession sqlSession = getSqlSessionFactory().openSession(true);
    try {
      return sqlSession.getMapper(MymemberInterfaceMapper.class).selectMyMemberList();
    } finally {
      sqlSession.close();
    }
  }
}
