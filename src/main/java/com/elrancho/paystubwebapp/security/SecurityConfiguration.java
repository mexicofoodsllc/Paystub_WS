package com.elrancho.paystubwebapp.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.elrancho.paystubwebapp.service.UserService;


@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	private final BCryptPasswordEncoder bCryptSecurityPasswordEncoder;
	private final UserDetailsService userDetailsService;
	
	public SecurityConfiguration(UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {

		this.userDetailsService = userDetailsService;
		this.bCryptSecurityPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
    protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable().cors().and().authorizeRequests()
        .antMatchers(HttpMethod.POST, "/login").permitAll()
		//.antMatchers(HttpMethod.POST, SecurityConstants.PASSWORD_RESET_URL).permitAll()
		.anyRequest()
		.authenticated().and().addFilter(getAuthenticationFilter())
		.addFilter(new AuthorizationFilter(authenticationManager())).sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        		
    }
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptSecurityPasswordEncoder);
	}

	   @Bean
	    CorsConfigurationSource corsConfigurationSource() {
		    CorsConfiguration configuration = new CorsConfiguration();
		    configuration.setAllowedOrigins(Arrays.asList("*"));
		    configuration.setAllowedMethods(Arrays.asList("POST","GET","PUT","UPDATE","DELETE","OPTIONS"));
		    configuration.setExposedHeaders(Arrays.asList("Authorization"));
		    configuration.setAllowedHeaders(Arrays.asList("Authorization","origin","content-type","accept","x-requested-with","userName"));
		    configuration.getAllowCredentials();
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", configuration);
	        return source;
	    }
	   
	public AuthenticationFilter getAuthenticationFilter() throws Exception {

		final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
		filter.setFilterProcessesUrl("/login");

		return filter;
	}
	


}