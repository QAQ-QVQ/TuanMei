package com.yu.data.shop.repository;

import com.yu.data.db.MeituanDB;
import com.yu.data.shop.entity.ShopGroupInfoEntity;
import com.yu.data.shop.mapper.ShopGroupInfoMapper;
import com.yu.domin.shop.model.ShopGroupInfoModel;
import com.yu.domin.shop.repository.ShopGroupInfoRepo;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.database.transaction.FastStoreModelTransaction;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by yu on 2021/3/25.
 */

public class ShopGroupInfoRepoImpl implements ShopGroupInfoRepo {

    private ShopGroupInfoMapper mapper;

    @Inject
    public ShopGroupInfoRepoImpl(ShopGroupInfoMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public boolean save(ShopGroupInfoModel model) {
        ShopGroupInfoEntity entity = mapper.toEntity(model);
        return entity.save();
    }

    @Override
    public void saveGroupInfos(List<ShopGroupInfoModel> models) {
        FastStoreModelTransaction<ShopGroupInfoEntity> transaction = FastStoreModelTransaction
                .saveBuilder(FlowManager.getModelAdapter(ShopGroupInfoEntity.class))
                .addAll(mapper.toEntities(models))
                .build();
        FlowManager.getDatabase(MeituanDB.class)
                .beginTransactionAsync(transaction)
                .build().execute();
    }
}
