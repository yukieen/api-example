package auth.service;

import auth.model.LoginUser;

public interface LoginService {
	LoginUser login(String username);
}
