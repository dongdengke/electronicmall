package cn.edu.bjtu.elctronicmall.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5 {
	public static void main(String[] args) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("md5");
		String password = "123456";
		byte[] result = digest.digest(password.getBytes());
		StringBuffer sb = new StringBuffer();
		for (byte b : result) {
			int num = b & 0xff;
			String hex = Integer.toHexString(num);
			if (hex.length() == 1) {
				sb.append("0");
			} else {
				sb.append(hex);
			}
		}
		System.out.println(sb.toString());
	}
}
