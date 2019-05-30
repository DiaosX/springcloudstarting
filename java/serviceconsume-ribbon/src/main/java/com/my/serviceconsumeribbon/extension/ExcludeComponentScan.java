package com.my.serviceconsumeribbon.extension;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ExcludeComponentScan {
}
