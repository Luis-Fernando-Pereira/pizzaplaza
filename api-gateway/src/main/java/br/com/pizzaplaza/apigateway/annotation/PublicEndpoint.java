package br.com.pizzaplaza.apigateway.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marca endpoints que não exigem autenticação.
 * Rotas sem esta anotação são interceptadas pelo AuthenticationFilter,
 * que delega a validação ao auth-service.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface PublicEndpoint {
}
