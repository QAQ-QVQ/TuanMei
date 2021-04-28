package com.yu.meituan.di.components;

import android.app.Application;
import android.content.Context;

import com.yu.data.shop.mapper.GroupPackageMapper;
import com.yu.data.shop.mapper.ShopEvaluateMapper;
import com.yu.data.shop.mapper.ShopGroupInfoMapper;
import com.yu.data.shop.mapper.ShopMapper;
import com.yu.domin.shop.repository.GroupPackageRepository;
import com.yu.domin.shop.repository.ShopGroupInfoRepo;
import com.yu.domin.shop.repository.ShopRepository;
import com.yu.domin.shop.service.GroupPackageService;
import com.yu.domin.shop.service.ShopGroupInfoService;
import com.yu.domin.shop.service.ShopService;
import com.yu.meituan.MyApplication;
import com.yu.meituan.app.home.HomeFragmentContract;
import com.yu.meituan.app.home.HomeFragmentPresenter;
import com.yu.meituan.di.modules.ApplicationModule;
import com.yu.meituan.mock.MockUtils;
import com.yu.common.rxjava.CloseableRxServiceExecutor;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by yu on 2021/3/10.
 */

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(MyApplication application);

    Application application();

    Context context();

    HomeFragmentPresenter presenter();

    HomeFragmentContract.Presenter homeFragmentContractPresenter();

    ShopMapper shopMapper();

    ShopRepository shopRepository();

    ShopService shopService();

    ShopEvaluateMapper shopEvaluateMapper();

    GroupPackageMapper groupPackageMapper();

    ShopGroupInfoMapper shopGroupInfoMapper();

    GroupPackageRepository groupPackageRepository();

    GroupPackageService groupPackageService();

    MockUtils mockUtils();

    ShopGroupInfoRepo shopGroupInfoRepo();

    ShopGroupInfoService shopGroupInfoService();

    CloseableRxServiceExecutor closeableRxServiceExecutor();
}
