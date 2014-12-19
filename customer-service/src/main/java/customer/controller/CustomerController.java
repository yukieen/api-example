package customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import customer.model.Customer;
import customer.service.CustomerService;

/**
 * 顧客情報に関するリクエストを受け付けます.
 */
@Controller
@RequestMapping(value = "/customers")
public class CustomerController {
	
	@Autowired
	CustomerService service;
	
	/**
	 * 顧客一覧情報を取得します.
	 * @return 顧客一覧情報
	 */
	@RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<Customer> findAll() {
		return service.findAll();
    }

	/**
	 * 顧客情報を登録します.
	 * @param customer 顧客
	 */
	@RequestMapping(method = RequestMethod.POST)
    public @ResponseBody void add(@RequestBody Customer customer) {
		service.add(customer);
    }
	
	/**
	 * 特定の顧客情報を取得します.
	 */
	@RequestMapping(method = RequestMethod.GET,value = "/{id}")
    public @ResponseBody Customer find(@PathVariable Long id) {
		return service.find(id);
    }
}
