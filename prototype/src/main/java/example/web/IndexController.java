package example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import example.service.UserService;

/***
 * Rootディレクトリにアクセスされたときにインデックスページを表示します.
 */
@Controller
@RequestMapping("/")
public class IndexController {
	
	@Autowired
	UserService service;
	
	/**
	 * インデックスページを表示します.
	 * @param model レスポンスオブジェクト
	 * @return 表示するページ名
	 */
	@RequestMapping
	public String init(Model model){
		model.addAttribute("users", service.findAll());
		return "index";
	}

}