package web.service;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import web.model.LoginUser;
import web.model.LoginUserDetails;

@Service
public class DefaultLoginService implements LoginService {
	
	private Log logger = LogFactory.getLog(DefaultLoginService.class);
	
	@Autowired
	private DefaultAuthenticationService service;
	
	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		LoginUser loginUser = service.auth(username);
		
		logger.debug(loginUser);
		
		if(StringUtils.isEmpty(loginUser.getUsername())){
			throw new UsernameNotFoundException("Invalid username and password");
		}
		return new LoginUserDetails(loginUser);
	}
}