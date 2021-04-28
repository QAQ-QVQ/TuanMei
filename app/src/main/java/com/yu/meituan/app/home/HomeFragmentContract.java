package com.yu.meituan.app.home;

import com.yu.domin.shop.model.ShopModel;
import com.yu.meituan.app.home.model.IconTitleModel;
import com.yu.meituan.base.BasePresenter;
import com.yu.meituan.widget.IconTitleView;
import com.scwang.smartrefresh.layout.api.RefreshFooter;

import java.util.List;

/**
 * Created by yu on 2021/3/10.
 */

public interface HomeFragmentContract {

    interface View {

        void addViewToBigModule(IconTitleView iconTitleView);

        void setShopListData(List<ShopModel> shopModels);

        void finishLoadmore(boolean success);

        void finishLoadmoreWithNoMoreData();

        void resetNoMoreData();

        void finishRefresh(boolean success);

        void setRefreshFooter(RefreshFooter footer);

        void addData2RecyclerView(List<ShopModel> shopModels);




    }

    interface Presenter extends BasePresenter<View>{

        List<Integer> getBannerImages();

        List<IconTitleModel> getIconTitleModels();

        void onLoadMore();

        void onRefresh();
    }
}
