/**
 * CryptoHash - Project 4, Social Network App
 * A fun little script to encrypt a string so we don't have raw passwords saved
 *
 * @author Group 8, C. H. Graham, ...
 * @version July 19, 2021
 */

public class CryptoHash {
	public static String getHash(String password) {
        //Will always be 32 digits, add digits
	    if (password.length() > 32) {
	        password = password.substring(0, 32);
	    } else {
	        for (int i = password.length(); i < 32; i++) {
	            password = "0" + password;
            }
	    }

	    int divisor =  0;
	    for (int i = 0; i < password.length(); i++) {
	        divisor += (int) password.charAt(i);
	    }

	    String output = "";
	    // char 32 to 126 range
	    for (int i = 0; i < password.length(); i++) {
	        output += (char) ((((int) password.charAt(i)) * 1000 * i / divisor) % 93 + 33);
	    }

	    return output;
	}
}
