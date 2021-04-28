package com.yu.domin.shop.repository;

import com.yu.domin.shop.model.ShopGroupInfoModel;

import java.util.List;

/**
 * Created by yu on 2021/3/25.
 */

public interface ShopGroupInfoRepo {

    boolean save(ShopGroupInfoModel model);

    void saveGroupInfos(List<ShopGroupInfoModel> models);
}
