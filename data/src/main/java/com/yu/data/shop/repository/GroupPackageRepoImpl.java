package com.yu.data.shop.repository;

import com.yu.data.db.MeituanDB;
import com.yu.data.shop.entity.GroupPackageEntity;
import com.yu.data.shop.mapper.GroupPackageMapper;
import com.yu.domin.shop.model.GroupPackageModel;
import com.yu.domin.shop.repository.GroupPackageRepository;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.database.transaction.FastStoreModelTransaction;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by yu on 2021/3/25.
 */

public class GroupPackageRepoImpl implements GroupPackageRepository {

    private GroupPackageMapper mapper;

    @Inject
    public GroupPackageRepoImpl(GroupPackageMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public boolean save(GroupPackageModel model) {
        GroupPackageEntity entity = mapper.toEntity(model);
        return entity.save();
    }

    @Override
    public void saveGroupPackages(List<GroupPackageModel> models) {
        //快速异步事务存储
        FastStoreModelTransaction<GroupPackageEntity> transaction = FastStoreModelTransaction
                .saveBuilder(FlowManager.getModelAdapter(GroupPackageEntity.class))
                .addAll(mapper.toEntities(models))
                .build();
        FlowManager.getDatabase(MeituanDB.class)
                .beginTransactionAsync(transaction)
                .build()
                .execute();
    }
}
