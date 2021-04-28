package com.yu.meituan.di.modules;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yu on 2021/3/10.
 */

@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    public Activity provideActivity(){
        return this.activity;
    }
}
