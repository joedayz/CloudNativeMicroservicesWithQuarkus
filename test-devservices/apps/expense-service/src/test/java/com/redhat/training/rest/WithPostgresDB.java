package com.redhat.training.rest;

import io.quarkus.test.common.QuarkusTestResource;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author josediaz
 **/
@QuarkusTestResource(value=PostgresDBTestResource.class, restrictToAnnotatedClass = true)
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = { ElementType.TYPE })
public @interface WithPostgresDB {
  String username() default "";
  String password() default "";
  String name() default "";
}
