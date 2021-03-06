package com.yu.meituan.app.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.yu.domin.shop.model.ShopModel;
import com.yu.meituan.MyApplication;
import com.yu.meituan.R;
import com.yu.meituan.app.home.adapter.LittleModuleAdapter;
import com.yu.meituan.app.home.adapter.ShopListAdapter;
import com.yu.meituan.app.home.model.IconTitleModel;
import com.yu.meituan.base.BaseFragment;
import com.yu.meituan.di.components.DaggerActivityComponent;
import com.yu.meituan.utils.GlideImageLoader;
import com.yu.meituan.utils.ToastUtils;
import com.yu.meituan.widget.refresh.CustomRefreshFooter;
import com.yu.meituan.widget.refresh.CustomRefreshHeader;
import com.yu.meituan.widget.HomeAdsView;
import com.yu.meituan.widget.IconTitleView;
import com.yu.meituan.widget.decoration.DividerItemDecoration;
import com.yu.meituan.widget.decoration.HomeGridDecoration;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yu on 2021/3/8.
 */

public class HomeFragment extends BaseFragment implements HomeFragmentContract.View {

    @BindView(R.id.home_banner)
    Banner banner;
    //????????????LinearLayout??????
    @BindView(R.id.ll_big_module_fragment_home)
    LinearLayout llBigModule;
    //?????????GridView??????
    @BindView(R.id.recyclerview_little_module)
    RecyclerView littleModuleRecyclerView;
    //4??????????????????????????????View
    @BindView(R.id.home_ads_view)
    HomeAdsView homeAdsView;
    //??????????????????
    @BindView(R.id.recycler_view_shops)
    RecyclerView rvShopList;
    //??????????????????
    @BindView(R.id.smartRefreshLayout_home)
    SmartRefreshLayout smartRefreshLayout;

    @Inject
    HomeFragmentContract.Presenter presenter;

    private ShopListAdapter mShopListAdapter;
    private List<ShopModel> mShopModels = Collections.emptyList();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        //?????? ButterKnife
        ButterKnife.bind(this, view);

        DaggerActivityComponent.builder()
                .applicationComponent(MyApplication.getApplicationComponent())
                .build()
                .inject(this);

        presenter.setContractView(this);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        initBanner();
        initLittleModuleRecyclerView();
        initAds();
        initShopList();
        initSmartRefreshLayout();
    }


    //???????????????????????????
    private void initSmartRefreshLayout() {
        smartRefreshLayout.setRefreshHeader(new CustomRefreshHeader(getActivity()));
        smartRefreshLayout.setRefreshFooter(new CustomRefreshFooter(getActivity(), "????????????"));
        smartRefreshLayout.setEnableScrollContentWhenLoaded(true);//??????????????????????????????????????????????????????
        smartRefreshLayout.setEnableFooterFollowWhenLoadFinished(true);
        smartRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                presenter.onLoadMore();
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                presenter.onRefresh();
            }
        });


    }

    @Override
    public void finishLoadmore(boolean success) {
        smartRefreshLayout.finishLoadmore(success);
    }

    @Override
    public void finishLoadmoreWithNoMoreData() {
        smartRefreshLayout.finishLoadmoreWithNoMoreData();
    }

    @Override
    public void finishRefresh(boolean success) {
        smartRefreshLayout.finishRefresh(success);
    }

    @Override
    public void resetNoMoreData() {
        smartRefreshLayout.resetNoMoreData();
    }

    /**
     * ????????????????????????????????????RecyclerView
     * @param shopModels
     */
    @Override
    public void addData2RecyclerView(List<ShopModel> shopModels) {
        mShopListAdapter.addData(shopModels);
    }

    @Override
    public void setRefreshFooter(RefreshFooter footer) {
        smartRefreshLayout.setRefreshFooter(footer);
    }

    private void initShopList() {
        LinearLayoutManager lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        rvShopList.setLayoutManager(lm);
        rvShopList.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        rvShopList.setItemAnimator(new DefaultItemAnimator());
        mShopListAdapter = new ShopListAdapter(getActivity(), R.layout.item_home_shop_list, mShopModels);
//        mShopListAdapter.setUpFetchEnable(true);
        rvShopList.setAdapter(mShopListAdapter);
//        mShopListAdapter.setEmptyView();
    }

    @Override
    public void setShopListData(List<ShopModel> shopModels) {
        mShopListAdapter.setNewData(shopModels);
    }

    private void initAds() {
        homeAdsView.setOnAdsClickListener(new HomeAdsView.OnAdsClickListener() {
            @Override
            public void onAds1Click() {
                ToastUtils.show("Ads1");
            }

            @Override
            public void onAds2Click() {
                ToastUtils.show("Ads2");
            }

            @Override
            public void onAds3Click() {
                ToastUtils.show("Ads3");
            }

            @Override
            public void onAds4Click() {
                ToastUtils.show("Ads4");
            }
        });
    }

    /**
     * ?????????????????????RecyclerView
     */
    private void initLittleModuleRecyclerView() {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 5);
        //??????LayoutManager
        littleModuleRecyclerView.setLayoutManager(gridLayoutManager);
        //???????????????
        littleModuleRecyclerView.addItemDecoration(new HomeGridDecoration(12));
        //????????????
        littleModuleRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //??????Adapter
        List<IconTitleModel> iconTitleModels = presenter.getIconTitleModels();
        LittleModuleAdapter littleModuleAdapter = new LittleModuleAdapter(
                R.layout.view_icon_title_small, iconTitleModels);

        littleModuleRecyclerView.setAdapter(littleModuleAdapter);
        //??????item????????????
        littleModuleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtils.show(iconTitleModels.get(position).getTitle());
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        //??????banner?????????
        banner.startAutoPlay();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        //??????banner?????????
        banner.stopAutoPlay();
    }

    public void initBanner() {
        //??????banner???????????????
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                .setImageLoader(new GlideImageLoader())
                .setImages(presenter.getBannerImages())
                .setBannerAnimation(Transformer.Default)
                .isAutoPlay(true)
                .setDelayTime(3000)
                .setIndicatorGravity(BannerConfig.CENTER)
                .start();
    }

    /**
     * ?????????????????????View
     */
    @Override
    public void addViewToBigModule(IconTitleView iconTitleView) {
        llBigModule.addView(iconTitleView);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
