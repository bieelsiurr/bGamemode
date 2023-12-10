package me.biieeel.utils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Command {

    String name();

    String permission() default "";

    boolean onlyForPlayers() default true;

}