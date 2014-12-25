package auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import auth.model.LoginUser;
import auth.service.LoginService;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private LoginService service;

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody LoginUser login(@RequestBody String username) {
	return service.login(username);
    } 

}
