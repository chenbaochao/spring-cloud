package com.play001.cloud.cms.Interceptor;

import java.lang.annotation.*;

@Documented
@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
/**
 * 权限验证的标识符,与数据库中的cms_menu.menu_code对应
 * value为""表示只需要登陆验证
 */
public @interface PermissionCode {


    String value() default "";
}
