package com.dev.security;

import javax.crypto.SecretKey;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@ConfigurationProperties(prefix = "application.jwt")
public class JwtConfig {
	private String secretKey;
	private String tokenPrefix;
	private int tokenExpirationAfterDays;
	@Bean
	public SecretKey getKey() {
		return Keys.hmacShaKeyFor(secretKey.getBytes());
	}
}
