package com.yu.domin.shop.service;

import com.yu.domin.shop.model.ShopGroupInfoModel;
import com.yu.domin.shop.repository.ShopGroupInfoRepo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by yu on 2021/3/25.
 */

public class ShopGroupInfoService {

    private ShopGroupInfoRepo repo;

    @Inject
    public ShopGroupInfoService(ShopGroupInfoRepo repo) {
        this.repo = repo;
    }

    public Single<Boolean> save(ShopGroupInfoModel model) {
        return Single.create(emitter -> {
            emitter.onSuccess(repo.save(model));
        });
    }

    public Completable saveGroupInfos(List<ShopGroupInfoModel> models) {
        return Completable.create(emitter -> {
            repo.saveGroupInfos(models);
            emitter.onComplete();
        });
    }
}
