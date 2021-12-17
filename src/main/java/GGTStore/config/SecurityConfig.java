package GGTStore.config;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import GGTStore.entity.Account;
import GGTStore.service.AccountService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	AccountService accountService;
	@Autowired
	BCryptPasswordEncoder pc;
	@Autowired
	HttpSession session;

	// co che ma hoa mat khau
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(username -> {
			try {
				Optional<Account> user = accountService.findById(username);
				session.setAttribute("username", user.get().getUsername());
				String password = user.get().getPassword();
				if (user.isPresent() && pc.matches(pc.encode(password), user.get().getPassword()))
					;
				user.get().setPassword("");

				// String password = pc.encode(user.getPassword());
				String[] roles = user.get().getAuthorities().stream().map(er -> er.getRole().getId())
						.collect(Collectors.toList()).toArray(new String[0]);
				return User.withUsername(username).password(password).roles(roles).build();
			} catch (NoSuchElementException e) {
				throw new UsernameNotFoundException(username + "not found");
			}
		});
	}

	// phan quyen su dung
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/order/**", "/cart/**").authenticated().antMatchers("/admin/**")
				.hasAnyRole("STAF", "DIRE")
				.antMatchers("/rest/authorities", "/rest/accounts/**", "/rest/statistical/**", "/rest/authorities/**")
				.hasRole("DIRE").anyRequest().permitAll();

		http.formLogin().loginPage("/security/login/form").loginProcessingUrl("/security/login")
				.defaultSuccessUrl("/security/login/success", false).failureUrl("/security/login/error");

		http.rememberMe().tokenValiditySeconds(86400);

		http.exceptionHandling().accessDeniedPage("/security/unauthorized");

		http.logout().logoutUrl("/security/logoff").logoutSuccessUrl("/security/logoff/success");

		http.oauth2Login().loginPage("/security/login/form").defaultSuccessUrl("/oauth2/login/success", true)
				.failureUrl("/security/login/error").authorizationEndpoint().baseUri("/oauth2/authorization");

	}

	// cho phep truy xuat rest api tu ben ngoai(domain khac)
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}

}
