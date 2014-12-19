package example.datasource;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import example.model.User;

/***
 * ユーザー情報のリソースマッパー
 */
public interface UserMapper {
	
    @Select("select * from users")
    List<User> findAll();
    
    @Insert("insert into users(firstName,lastName) values(#{firstName},#{lastName})")
    void add(User user);

    @Select("select * from users where id=#{id}")
	User find(Long id);
    
}
