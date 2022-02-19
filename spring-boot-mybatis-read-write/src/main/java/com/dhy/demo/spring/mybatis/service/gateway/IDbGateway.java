package com.dhy.demo.spring.mybatis.service.gateway;

import com.dhy.demo.spring.mybatis.po.SeataStoragePo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Project spring-boot-mybatis
 * @Description 主要用途描述
 * @Author lvaolin
 * @Date 2021/12/28 上午9:27
 */
public interface IDbGateway {
    public List<SeataStoragePo> productSelectAll();
    public int productCount();
    public int productInsert(SeataStoragePo po);
    public int productDelete(SeataStoragePo po);
    public int productUpdate(SeataStoragePo po);
    public List<SeataStoragePo> productSelectAllByXml();
}
