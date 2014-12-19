package customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import customer.datasource.CustomerMapper;
import customer.model.Customer;



@Service	
public class DefaultCustomerrService implements CustomerService {

	@Autowired
	private CustomerMapper mapper;
	
	@Override
	public void add(Customer customer) {
		mapper.add(customer);
	}

	@Override
	public List<Customer> findAll() {
		return mapper.findAll();
	}

	@Override
	public Customer find(Long id) {
		return mapper.find(id);
	}

}
