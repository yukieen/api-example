package example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import example.model.User;
import example.service.UserService;

/**
 * ユーザー情報に関するリクエストを受け付けます.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	UserService service;
	
	/**
	 * ユーザーを追加します.
	 * @param model
	 * @param user
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
    public String add(Model model, @RequestBody final User user) {
		service.add(user);
		model.addAttribute("users", service.findAll());
        return "index";
    }
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String find(Model model, @PathVariable Long id) {
		model.addAttribute("users", service.find(id));
		return "index";
	}

}
