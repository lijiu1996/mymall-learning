package com.lijiawei.practice.mymall.learning.init.user.mapper;

import com.lijiawei.practice.mymall.learning.init.user.domain.UmsAdmin;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsAdminMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UmsAdmin record);

    int insertOrUpdate(UmsAdmin record);

    int insertOrUpdateSelective(UmsAdmin record);

    int insertSelective(UmsAdmin record);

    UmsAdmin selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsAdmin record);

    int updateByPrimaryKey(UmsAdmin record);

    int updateBatch(List<UmsAdmin> list);

    int updateBatchSelective(List<UmsAdmin> list);

    int batchInsert(@Param("list") List<UmsAdmin> list);

    UmsAdmin selectFirstByUsername(@Param("username")String username);

}