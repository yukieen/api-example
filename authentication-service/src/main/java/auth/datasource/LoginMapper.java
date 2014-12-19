package auth.datasource;

import org.apache.ibatis.annotations.Select;

import auth.model.LoginUser;

public interface LoginMapper {
    @Select("select * from users where username=#{username}")
    LoginUser find(String username);
}
