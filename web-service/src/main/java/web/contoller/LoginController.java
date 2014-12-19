package web.contoller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/***
 * ログアウトします.
 */
@Controller
@RequestMapping("/login")
public class LoginController {
	
	
	@RequestMapping
	public String init(){
		return "login";
	}

}