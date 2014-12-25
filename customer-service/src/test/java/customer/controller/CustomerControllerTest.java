package customer.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import customer.Application;
import customer.model.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class CustomerControllerTest {
    private TestRestTemplate rt;
    
    @Value("${local.server.port}")
    private int port;
    
    @Before
    public void setUp(){
	rt = new TestRestTemplate();
    }
    
    @Test
    public void すべての顧客情報を取得できる(){
	Customer[] actual = rt.getForObject("http://localhost:" + port + "/customers", Customer[].class);
	
	assertThat(actual.length >= 3,is(true));
    }
    
    @Test
    public void 特定の顧客情報を取得できる(){
	Customer actual = rt.getForObject("http://localhost:" + port + "/customers/1", Customer.class);
	
	assertThat(actual.getId(),is(1L));
	assertThat(actual.getFirstName(),is("taro"));
	assertThat(actual.getLastName(),is("yamada"));
	
    }
    
    @Test
    public void 顧客情報を新規登録できる(){
	Customer customer = new Customer();
	customer.setFirstName("hanayo");
	customer.setLastName("koizumi");
	
	rt.exchange("http://localhost:" + port + "/customers", 
		HttpMethod.POST,
		new HttpEntity<Customer>(customer),
		Customer.class);

	Customer[] actual = rt.getForObject("http://localhost:" + port + "/customers", Customer[].class);
	
	assertThat(actual.length,is(4));
	assertThat(actual[3].getId(),is(4L));
	assertThat(actual[3].getFirstName(),is(customer.getFirstName()));
	assertThat(actual[3].getLastName(),is(customer.getLastName()));
	
    }
}
