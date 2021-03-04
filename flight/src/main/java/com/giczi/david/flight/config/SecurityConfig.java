package com.giczi.david.flight.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
			authorizeRequests()
				.antMatchers("/console/**").permitAll()
				.antMatchers("/flight-js/**", "/flight-css/**").permitAll()
				.antMatchers("/flight/login").permitAll()
				.antMatchers("/flight/registration").permitAll()
				.antMatchers(HttpMethod.POST,"/flight/reg").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/flight/login")
				.permitAll()
				.and()
			.logout()
				.logoutSuccessUrl("/login?logout")
				.permitAll();
		
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}

	
	@Autowired
	public void configureAuth(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser("orboka@gmail.com")
				.password("Tündérgalambocska79")
				.roles("USER")
			.and().
				withUser("david.giczi@gmail.com")
				.password("dave")
				.roles("ADMIN");
	}
	
	
}
