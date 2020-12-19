package com.dhy.common;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
@Import(MyImportSelector.class)
public @interface EnableMyConfig {

}
