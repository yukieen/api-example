package web.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import web.model.LoginUser;
import web.model.LoginUserDetails;

@Service
public class DefaultLoginService implements LoginService {

    @Autowired
    private DefaultAuthenticationService service;
    
    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginUser loginUser = service.auth(username);
        
        if(notExists(loginUser)){
            throw new UsernameNotFoundException("Invalid username and password");
        }
        
        return new LoginUserDetails(loginUser);
    }

    private boolean notExists(LoginUser loginUser) {
	return StringUtils.isEmpty(loginUser.getUsername());
    }
}