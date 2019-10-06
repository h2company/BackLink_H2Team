package com.backlink.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
	
	public static boolean checkEmail(String email) {
		String emailPattern = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
			Pattern regex = Pattern.compile(emailPattern);
			Matcher matcher = regex.matcher(email);
			return matcher.find();
	}
}
