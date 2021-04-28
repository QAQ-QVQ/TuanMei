package com.yu.meituan.app;

import android.os.Bundle;

import com.yu.meituan.MyApplication;
import com.yu.meituan.R;
import com.yu.meituan.app.discover.DiscoverFragment;
import com.yu.meituan.app.home.HomeFragment;
import com.yu.meituan.app.mine.MineFragment;
import com.yu.meituan.app.nearby.NearbyFragment;
import com.yu.meituan.app.order.OrderFragment;
import com.yu.meituan.base.BaseActivity;
import com.yu.meituan.base.BaseFragment;
import com.yu.meituan.di.components.DaggerActivityComponent;
import com.yu.meituan.di.modules.ActivityModule;
import com.yu.meituan.widget.bottomtab.CustomBottomTabWidget;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tabWidget)
    CustomBottomTabWidget tabWidget;
    private List<BaseFragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        DaggerActivityComponent.builder()
                .applicationComponent(MyApplication.getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build().inject(this);

        //初始化
        init();
    }

    private void init() {
        //构造Fragment的集合
        fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new NearbyFragment());
        fragmentList.add(new DiscoverFragment());
        fragmentList.add(new OrderFragment());
        fragmentList.add(new MineFragment());
        //初始化CustomBottomTabWidget
        tabWidget.init(getSupportFragmentManager(), fragmentList);
    }
}
