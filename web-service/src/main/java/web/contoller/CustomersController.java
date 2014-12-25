package web.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import web.model.Customer;
import web.properties.CustomerServiceProperties;

/**
 * 顧客情報に関するリクエストを受け付けます.
 */
@Controller
@RequestMapping("/customers")
public class CustomersController {

    @Autowired
    private CustomerServiceProperties properties;

    /**
     * 顧客情報を一覧表示します.
     * @param model
     * @return 顧客情報一覧画面のファイル名
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
	RestTemplate rt = new RestTemplate();		
	ResponseEntity<Customer[]> responseEntity = rt.getForEntity(properties.getUrl() + "customers", Customer[].class);
	model.addAttribute("customers",responseEntity.getBody());
        return "customers";
    }

}
