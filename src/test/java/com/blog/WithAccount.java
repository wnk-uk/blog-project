package com.blog;

import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithAccountSecurityContextFactory.class)
public @interface WithAccount {

    String name() default "Uk NamGung";
    String email() default "dailycoding0211@gmail.com";
    String picture() default "https://lh3.googleusercontent.com";

    String role() default "ADMIN";

}
