package com.dhy.demo.spring.springdatajpa.dao;

import com.dhy.demo.spring.springdatajpa.po.SeataStoragePo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SeataStoreageRepository  extends JpaRepository<SeataStoragePo,Integer> {

    /**
     * 按约定写接口名称即可，By后面是po的字段名称，驼峰，参数即为条件值
     * @param stock
     * @return
     */
    List<SeataStoragePo> findByStock(int stock);

    @Query("from Users where name = ?")
    List<SeataStoragePo> queryByNameUseHQL(String name);

    @Query(value = "select * from t_user where name=?",nativeQuery = true)
    List<SeataStoragePo> queryByNameUseSQL(String name);

    @Query("update Users set name=? where id=?")
    @Modifying
        //需要执行一个更新操作
    void updateUsersNameById(String name,Integer id);

    @Query("delete Users set name=? where id=?")
    @Modifying
        //需要执行一个更新操作
    void deleteUsersNameById(String name,Integer id);

}
