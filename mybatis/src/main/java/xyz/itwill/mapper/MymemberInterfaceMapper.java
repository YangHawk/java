package xyz.itwill.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import xyz.itwill.dto.MyMember;

// mybatis 프레임워크에서는 인터페이스를 이용하여 매퍼 파일을 작성 가능
// ▶ 추상 메소드에 mybatis 어노테이션(Annotation)을 사용하여 SQL 명령 등록
public interface MymemberInterfaceMapper {
  // @Insert: 추상 메소드에 INSERT 명령을 등록하기 위한 어노테이션
  // value 속성: 추상 메소드에 등록될 SQL 명령을 속성값으로 설정
  // ▶ value 속성 외 다른 속성이 없는 경우 속성값만 설정 가능
  @Insert(value = "insert into mymemeber values(#{id}, #{name}, #{phone}, #{email})")
  // 추상 메소드의 매개 변수에서 SQL 명령 작성에 필요한 객체(값)을 전달받기 위하여 선언하며 추상 메소드의
  // 반환형은 실행 결과를 객체(값)로 제공받기 위한 Java 자료형을 선언
  int insertMyMember(MyMember member);

  // @Update: 추상 메소드에 UPDATE 명령을 등록하기 위한 어노테이션
  @Update("update mymember set name=#{name}, phone=#{phone}, email=#{email} where id=#{id}")
  int updateMyMember(MyMember member);

  // @Delete: 추상 메소드에 DELETE 명령을 등록하기 위한 어노테이션
  @Delete("delete from mymember where id=#{id}")
  int deleteMyMember(String id);

  // @Select: 추상 메소드에 SELECT 명령을 등록하기 위한 어노테이션
  @Select("select id, name, phone, email from mymember where id=#{id}")
  MyMember selectMyMember(String id);

  @Select("select id, name, phone, email from mymember order by id")
  List<MyMember> selectMyMemberList();
}
