package customer.service;

import java.util.List;

import customer.model.Customer;


/**
 * 顧客
 */
public interface CustomerService {

	/**
	 * 顧客の追加を行います.
	 * @param customer 顧客
	 */
	public void add(Customer customer);

	/**
	 * すべての顧客を取得します.
	 * @return すべての顧客
	 */
	public List<Customer> findAll();

	/**
	 * 指定したIDの顧客を取得します.
	 * @param id
	 * @return 指定したIDの顧客
	 */
	public Customer find(Long id);
}
