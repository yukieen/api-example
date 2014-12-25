package web.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/***
 * ログインページを表示します.
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    @RequestMapping
    public String init(){
        return "login";
    }
}