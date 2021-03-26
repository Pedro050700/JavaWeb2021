package br.com.senac.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	private static String[] PUBLIC_MATCHERS = {"/h2-console/**"};
	 
	@Autowired
	CurrentUserDetailService userDatailService;
	//metodo que define as configurações do spring security
	protected void configure(HttpSecurity http) throws Exception {
		
		// Liberando acesso para todas as url que está dentro de PUBLIC_MATCHES
		http.authorizeRequests().antMatchers(PUBLIC_MATCHERS).permitAll()
		.antMatchers(PUBLIC_MATCHERS).hasRole("ADMIN")
		.anyRequest().authenticated() // qualquer outra requisição, vai ser preciso autenticar.
		.and().formLogin().defaultSuccessUrl("/menu", true).permitAll() //deixo todos acessarem meu form de login
		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")); // todas as pessoas podem fazer logout
	}
	
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/*","/js/*");
	}
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//		auth.inMemoryAuthentication().withUser("admin").password(encoder.encode("123")).roles("ADMIN");
//	}
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDatailService).passwordEncoder(new BCryptPasswordEncoder());
		
	}
}