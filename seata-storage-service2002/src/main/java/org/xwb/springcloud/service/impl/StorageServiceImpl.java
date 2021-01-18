package org.xwb.springcloud.service.impl;

import org.springframework.stereotype.Service;
import org.xwb.springcloud.dao.StorageDao;
import org.xwb.springcloud.service.StorageService;

import javax.annotation.Resource;

@Service
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageDao storageDao;

    @Override
    public void decrease(Long productId, Integer count) {
        storageDao.decrease(productId, count);
    }

}
