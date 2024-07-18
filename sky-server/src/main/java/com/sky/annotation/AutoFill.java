package com.sky.annotation;

import com.sky.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoFill {

    //数据库操作类型：UPDATE INSERT
    //这段代码看不懂这里为什么直接就可以加value()这个方法
    //我猜：可能是因为enum类型的原因，
    OperationType value();

}
