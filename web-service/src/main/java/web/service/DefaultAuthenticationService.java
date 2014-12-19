package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import web.model.LoginUser;
import web.properties.AuthenticationServiceProperties;

@Service
public class DefaultAuthenticationService implements AuthenticationService {

	@Autowired
	private AuthenticationServiceProperties properties;
	
	@Override
	public LoginUser auth(String username) {
		RestTemplate rt = new RestTemplate();	
		return rt.postForObject(properties.getUrl() + "login",username, LoginUser.class);		
	}

}
