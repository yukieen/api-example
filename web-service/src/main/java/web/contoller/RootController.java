package web.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/***
 * Rootディレクトリにアクセスしたときはログインページを表示します.
 */
@Controller
@RequestMapping("/")
public class RootController {
	
	@RequestMapping
	public String init(){
		return "login";
	}

}