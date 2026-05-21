package br.com.pizzaplaza.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    public static String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static Boolean isPasswordValid(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }

}
