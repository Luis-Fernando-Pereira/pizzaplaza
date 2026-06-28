package br.com.pizzaplaza.userservice.util;

public class ValidationUtils {

    public static boolean isPasswordValid(String password) {
        return password != null && !password.isBlank();
    }

    public static boolean isEmailValid(String email) {
        return email != null && !email.isBlank();
    }

    public static boolean isCpfValid(String cpf) {
        return cpf != null && !cpf.isBlank();
    }
}
