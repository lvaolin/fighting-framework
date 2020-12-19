package com.dhy.provider.service;

import com.dhy.common.itf.ISeataStorageService;
import com.dhy.common.itf.SeataStoragePo;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

import java.util.List;

@DubboService
@Component
public class SeataStorageServiceImpl implements ISeataStorageService {

    @Override
    public List<SeataStoragePo> selectAll() {
        return null;
    }

    @Override
    public int selectCount() {
        return 0;
    }

    @Override
    public int insert(SeataStoragePo po) {
        return 0;
    }

    @Override
    public int delete(SeataStoragePo po) {
        return 0;
    }

    @Override
    public int update(SeataStoragePo po) {
        return 0;
    }
}
