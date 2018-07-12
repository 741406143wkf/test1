package cn.itcast.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {
	
	public static void main(String args[]) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		//$2a$10$kzfjfl4tJDVa5cwnQJm4FeyfbYzaQgaXBX5.Ln0wthf/TKZTSik1O
		//$2a$10$FLkJu3zfW7dc8t0aBBaSXOkBKGRGFrb.iPBslBwJ/VOeXw7rZNrau
		//System.out.println(encoder.encode("123456"));
		System.out.println(encoder.matches("123456", "$2a$10$kzfjfl4tJDVa5cwnQJm4FeyfbYzaQgaXBX5.Ln0wthf/TKZTSik1O"));
		System.out.println(encoder.matches("123456", "$2a$10$FLkJu3zfW7dc8t0aBBaSXOkBKGRGFrb.iPBslBwJ/VOeXw7rZNrau"));
	}

}
