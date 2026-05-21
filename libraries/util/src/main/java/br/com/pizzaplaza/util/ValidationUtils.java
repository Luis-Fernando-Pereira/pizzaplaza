package br.com.pizzaplaza.util;

public class ValidationUtils {

    public static Boolean isPasswordValid(String password) {
        return password != null && !password.isEmpty() && !password.isBlank();
    }

    public static Boolean isEmailValid(String email) {
        return email != null && !email.isEmpty() && !email.isBlank();
    }

    public static Boolean isCpfValid(String cpf) {
        return cpf != null && !cpf.isEmpty() && !cpf.isBlank();
    }
}
