package com.yu.meituan.di.components;

import com.yu.meituan.app.MainActivity;
import com.yu.meituan.app.home.HomeFragment;
import com.yu.meituan.di.modules.ActivityModule;
import com.yu.meituan.di.scopes.Scopes;

import dagger.Component;

/**
 * Created by yu on 2021/3/10.
 */

@Scopes.Activity
@Component(modules = {ActivityModule.class}, dependencies = {ApplicationComponent.class})
public interface ActivityComponent {

    void inject(MainActivity activity);

    void inject(HomeFragment fragment);

}
