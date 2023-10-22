package com.portmz.portsystem.constant;

public class JWTUtil {
	public static final long EXPIRE_ACCESS_TOKEN = 1 * 60 * 1000;// 1min

	public static final long EXPIRE_REFRESH_TOKEN = 120 * 60 * 1000;// 2h

	public static final String BEARER_PREFIX = "Bearer ";

	public static final String ISSUER = "springBootApp";

	public static final String SECRET = "myPrivateSecret";

	public static final String AUTH_HEADER = "Authorization";
	
}
