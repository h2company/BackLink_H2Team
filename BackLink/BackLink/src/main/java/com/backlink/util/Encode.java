package com.backlink.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class Encode {
	
	@Value("${bcryt.salt}")
	private static int salt;
	
	public static String pw(String pw) {
		return BCrypt.hashpw(pw, BCrypt.gensalt(salt));
	}
}
