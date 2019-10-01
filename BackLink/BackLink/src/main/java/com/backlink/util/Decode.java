package com.backlink.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class Decode {
	
	public static boolean checkpw(String plaintext, String hashed) {
		return BCrypt.checkpw(plaintext, hashed);
	}
}
