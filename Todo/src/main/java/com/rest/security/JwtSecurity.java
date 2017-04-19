package com.rest.security;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.rest.constants.TodoConstant;

@Component
public class JwtSecurity {

	@Resource
	private Environment env;

	public String createAuthenticateToken(String emailId) throws Exception {

		try {

			Date now = new Date();
			Long nowMillis = System.currentTimeMillis();
			now.setTime(nowMillis);

			JWTCreator.Builder builder = JWT.create();
			builder.withIssuedAt(now);
			builder.withSubject(emailId);
			builder.withExpiresAt(getJwtSecretExpireAt());
			return builder.sign(getJwtAlgorithm());

		} catch (JWTCreationException | UnsupportedEncodingException e) {
			throw new Exception("Token not create.");
		}
	}

	public String getJwtSecretkey() {
		return env.getRequiredProperty(TodoConstant.JWT_SECURITY_SECRET_KEY);
	}

	public Date getJwtSecretExpireAt() {
		Long nowMillis = System.currentTimeMillis();
		Long expMillis = nowMillis
				+ Long.valueOf(env
						.getRequiredProperty(TodoConstant.JWT_SECURITY_SECRET_EXPIRE));
		Date dt = new Date();
		dt.setTime(expMillis);

		return dt;
	}

	public String getJwtIssuer() {
		return env.getRequiredProperty(TodoConstant.JWT_SECURITY_SECRET_ISSUER);
	}

	public Algorithm getJwtAlgorithm() throws UnsupportedEncodingException {
		return Algorithm.HMAC512(getJwtSecretkey());
	}
}
