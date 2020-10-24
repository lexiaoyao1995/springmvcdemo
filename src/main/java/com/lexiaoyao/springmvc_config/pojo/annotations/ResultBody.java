package com.lexiaoyao.springmvc_config.pojo.annotations;

import java.lang.annotation.*;

/**
 * ResultBody将前端发送的Result类，自动取出Data
 */
@Target({ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResultBody {
}
