package auth.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import auth.Application;
import auth.model.LoginUser;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class LoginControllerTest {
    private TestRestTemplate rt;
    
    @Value("${local.server.port}")
    private int port;
    
    @Before
    public void setUp(){
	rt = new TestRestTemplate();
    }
    
    @Test
    public void ユーザー名からユーザー情報が取得できる(){
	LoginUser actual = rt.postForObject("http://localhost:" + port + "/login","user01", LoginUser.class);
	
	// TODO:暗号化されているってどうテストしようか？
	assertThat(actual.getUsername(),is("user01"));
//	assertThat(actual.getPassword(),is("pass01"));
	assertThat(actual.getAuthority(),is("USER_ROLE"));
    }
    
    @Test
    public void 存在しないユーザー情報のユーザ名には空文字が取得できる(){
	LoginUser actual = rt.postForObject("http://localhost:" + port + "/login","user99", LoginUser.class);
	
	assertThat(actual.getUsername(),is(""));
	assertThat(actual.getPassword(),is(""));
	assertThat(actual.getAuthority(),is(""));
    }
}
