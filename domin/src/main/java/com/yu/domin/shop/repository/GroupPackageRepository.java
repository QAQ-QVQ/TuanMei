package com.yu.domin.shop.repository;

import com.yu.domin.shop.model.GroupPackageModel;

import java.util.List;

/**
 * Created by yu on 2021/3/25.
 */

public interface GroupPackageRepository {

    boolean save(GroupPackageModel model);

    void saveGroupPackages(List<GroupPackageModel> models);

}
