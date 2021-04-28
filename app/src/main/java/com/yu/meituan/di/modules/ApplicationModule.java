package com.yu.meituan.di.modules;

import android.app.Application;
import android.content.Context;

import com.yu.data.shop.repository.GroupPackageRepoImpl;
import com.yu.data.shop.repository.ShopGroupInfoRepoImpl;
import com.yu.data.shop.repository.ShopRepositoryImpl;
import com.yu.domin.shop.repository.GroupPackageRepository;
import com.yu.domin.shop.repository.ShopGroupInfoRepo;
import com.yu.domin.shop.repository.ShopRepository;
import com.yu.meituan.app.home.HomeFragmentContract;
import com.yu.meituan.app.home.HomeFragmentPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yu on 2021/3/10.
 */

@Module
public class ApplicationModule {

    private Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return application;
    }

    @Provides
    public Context provideContext() {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    HomeFragmentContract.Presenter providePresenter(HomeFragmentPresenter presenter) {
        return presenter;
    }

    @Provides
    @Singleton
    ShopRepository provideShopRepository(ShopRepositoryImpl impl) {
        return impl;
    }

    @Provides
    @Singleton
    GroupPackageRepository provideGroupPackageRepository(GroupPackageRepoImpl impl) {
        return impl;
    }

    @Provides
    @Singleton
    ShopGroupInfoRepo provideShopGroupInfoRepo(ShopGroupInfoRepoImpl impl) {
        return impl;
    }


}
