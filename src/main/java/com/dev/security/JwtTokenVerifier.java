package com.dev.security;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

public class JwtTokenVerifier extends OncePerRequestFilter{
	private final JwtConfig jwtConfig;
	
	public JwtTokenVerifier(JwtConfig jwtConfig) {
		super();
		this.jwtConfig = jwtConfig;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader=request.getHeader("Authorization");
		if(authHeader==null || authHeader.isBlank() || !authHeader.startsWith(jwtConfig.getTokenPrefix())) {
			filterChain.doFilter(request, response);
		}else {
			String token = authHeader.replace(jwtConfig.getTokenPrefix(), "");
			
			try {
				Jws<Claims> jws = Jwts.parser().setSigningKey(jwtConfig.getKey())
				.parseClaimsJws(token);
				
				Claims body=jws.getBody();
				String username=body.getSubject();
				List<Map<String, String>> authorities=(List<Map<String, String>>) body.get("authorities");
				
				Set<SimpleGrantedAuthority> simpleGrantedAuth=authorities.stream().map(x->
				new SimpleGrantedAuthority(x.get("authority"))).collect(Collectors.toSet());
				
				Authentication authentication=new UsernamePasswordAuthenticationToken(username, null, simpleGrantedAuth);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}catch(JwtException e) {
				throw new IllegalStateException(String.format("Token %s cannot be truest", token));
			}
			filterChain.doFilter(request, response);
		}
		
	}

}
