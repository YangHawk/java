package xyz.itwill.mapper;

import java.util.List;
import xyz.itwill.dto.MyUser;

public interface MyUserMapper {
  int insertMyUser(MyUser user);

  List<MyUser> selectMyUserList();
}
