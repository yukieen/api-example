package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import web.properties.LoadBalancerProperties;
import web.service.LoginService;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private LoadBalancerProperties properties;
	
    @Override
    public void configure(WebSecurity web) throws Exception {
	web.ignoring().antMatchers("/static/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
	http.authorizeRequests().antMatchers("/", "/login").permitAll()
		.anyRequest().authenticated();
	http.formLogin().loginPage("/login").usernameParameter("username")
		.passwordParameter("password")
		.defaultSuccessUrl(properties.getUrl() + "/customers", true);
	http.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout**"))
		.logoutSuccessUrl(properties.getUrl() + "/login");
	super.configure(http);
    }

    @Configuration
    static class AuthenticationConfiguration extends
	    GlobalAuthenticationConfigurerAdapter {
	@Autowired
	private LoginService service;

	@Bean
	PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
	    auth.userDetailsService(service).passwordEncoder(passwordEncoder());
	}
    }
}
