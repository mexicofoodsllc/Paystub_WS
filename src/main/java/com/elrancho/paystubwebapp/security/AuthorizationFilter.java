package com.elrancho.paystubwebapp.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

/*
 * The filter responsible for authorizing the user by verifying the token
 */
	public class AuthorizationFilter extends BasicAuthenticationFilter {

		public AuthorizationFilter(AuthenticationManager authenticationManager) {
			super(authenticationManager);
		}

		@Override
		protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
				throws IOException, ServletException {

			String header = req.getHeader(SecurityConstants.HEADER_STRING);
			
			
				
			if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
					chain.doFilter(req, res);
					return;
				}

			UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			chain.doFilter(req, res);
		}

		/*
		 * This method reads the JWT from the Authorization header, 
		 * and then uses Jwtsto validate the token. 
		 * If everything is in place, we set the user in the SecurityContext 
		 * and allow the request to move on.
		 */
		public UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {

			//String path = request.getRequestURI();//.substring(request.getContextPath().length());
			
			String	token = request.getHeader(SecurityConstants.HEADER_STRING);
			
			if (token != null) {

				token = token.replace(SecurityConstants.TOKEN_PREFIX, "");
				
				//parsing the token
				String user = Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(token).getBody()
						.getSubject();

				if (user != null)
					return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());

			}

			return null;
		}
	}
