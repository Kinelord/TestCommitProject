package com.shakirov.config;

import com.shakirov.model.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author igor@shakirov-dev.ru on 06.01.2023
 * @version 1.0
 */

@Configuration
//@EnableMethodSecurity
public class SecurityConfiguration/* extends WebSecurityConfigurerAdapter */ {
	
/*	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				.authorizeRequests().anyRequest().authenticated()
				.and()
//				.httpBasic(Customizer.withDefaults());
				.logout(logout -> logout
						.logoutUrl("/logout")
						.logoutSuccessUrl("/login")
						.deleteCookies("JSESSIONID"))
				.formLogin(login -> login
						.loginPage("/login")
						.defaultSuccessUrl("/users")
						.permitAll());
	}*/
	
	@Bean
//	@Order(SecurityProperties.BASIC_AUTH_ORDER)
	public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		return http
				// Отключим csrf, пока он не сконфигурирован
				.csrf().disable()
				
				// Только аутентифицированные пользователи, Роли отключены
//				.authorizeRequests().anyRequest().authenticated()
				
				// Новая конфигурация
				.authorizeHttpRequests(urlConfig -> urlConfig
								.antMatchers("/users/2/delete").denyAll()
								.antMatchers(
										"/login",
										"/users/registration",
										"/v3/api-docs/**",
										"/swagger-ui**/**").permitAll()
								.antMatchers("/users/{\\d+}/delete").hasAuthority(Role.ADMIN.getAuthority())

//						.antMatchers("/admin/**").hasAuthority("ADMIN")
//						.antMatchers("/admin/**").hasAnyRole("ROLE_ADMIN")  // В этом случае, нужно менять названия констант
								.antMatchers("/admin/**").hasAuthority(Role.ADMIN.getAuthority())
								.anyRequest().authenticated()
				
				
				)
//				.and()
				.formLogin(login -> login
								.loginPage("/login")
								.defaultSuccessUrl("/users")
//						.permitAll()  // Доступно всем, отключили тут, так как настроили в authorizeHttpRequests
				)
//				.httpBasic(Customizer.withDefaults())
//				.and()
				.logout(logout -> logout
						.logoutUrl("/logout")  // default
						.logoutSuccessUrl("/login")  // default
						.deleteCookies("JSESSIONID"))  // default
				.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
