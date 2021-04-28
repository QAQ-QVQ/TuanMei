package com.yu.meituan.di.scopes;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by yu on 2021/3/10.
 */

public interface Scopes {

    @Documented
    @Scope
    @Retention(RetentionPolicy.RUNTIME)
    @interface Activity{
    }

    @Documented
    @Scope
    @Retention(RetentionPolicy.RUNTIME)
    @interface Service{
    }

    @Documented
    @Scope
    @Retention(RetentionPolicy.RUNTIME)
    @interface Receiver{
    }
}
