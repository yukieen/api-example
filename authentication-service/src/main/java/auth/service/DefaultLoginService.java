package auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import auth.datasource.LoginMapper;
import auth.model.LoginUser;

@Service
public class DefaultLoginService implements LoginService {

    @Autowired
    LoginMapper mapper;
    
    @Override
    public LoginUser login(String username) {
        LoginUser user = mapper.find(username);
        if(user == null){
            return new LoginUser("","","");
        }
        return cryptoPassword(user);
    }
    
    /**
    * パスワードを暗号化します.
    * @param user ログインユーザー情報
    * @return パスワード暗号化後のユーザー情報
    */
    private LoginUser cryptoPassword(LoginUser user){
        user.setPassword(bcrypt(user.getPassword()));
        return user;
    }
    private String bcrypt(String password){
        String salt = BCrypt.gensalt();	 
        return BCrypt.hashpw(password, salt);
    }
}
