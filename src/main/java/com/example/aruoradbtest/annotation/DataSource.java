package com.example.aruoradbtest.annotation;

import com.example.aruoradbtest.enums.DataSourceType;

import java.lang.annotation.*;

/**
 * <ul>
 *     <li>If a class is annotated with @DataSource, all its methods matches the regex rules we specify in our application.properties will switch to their related datasource.</li>
 *     <li>If both a method and its class are annotated with @DataSource, only the method annotation will take effect.</li>
 * </ul>
 */

@Target({ ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
    DataSourceType value() default DataSourceType.WRITER;
}
