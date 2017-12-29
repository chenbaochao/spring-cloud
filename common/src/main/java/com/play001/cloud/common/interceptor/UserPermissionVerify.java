package com.play001.cloud.common.interceptor;

import java.lang.annotation.*;

/**
 * 用户权限验证
 */
@Documented
@Inherited
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
/**
 * 普通用户的权限验证,验证失败返回json格式的错误信息
 */
public @interface UserPermissionVerify {
}
