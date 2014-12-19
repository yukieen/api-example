package customer.datasource;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import customer.model.Customer;

/***
 * 顧客情報のリソースマッパー
 */
public interface CustomerMapper {
	
    @Select("select * from customers")
    List<Customer> findAll();
    
    @Insert("insert into customers(firstName,lastName) values(#{firstName},#{lastName})")
    void add(Customer user);

    @Select("select * from customers where id=#{id}")
	Customer find(Long id);
    
}
