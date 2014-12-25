package web.model;

import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class LoginUserDetails extends User{
    private static final long serialVersionUID = 1L;
    
    public LoginUserDetails(LoginUser loginUser){
        super(loginUser.getUsername(),
        	loginUser.getPassword(),
        	new HashSet<GrantedAuthority>(AuthorityUtils.createAuthorityList(loginUser.getAuthority())));
    }
}