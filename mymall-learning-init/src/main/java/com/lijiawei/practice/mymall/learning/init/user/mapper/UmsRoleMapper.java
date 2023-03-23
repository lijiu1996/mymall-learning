package com.lijiawei.practice.mymall.learning.init.user.mapper;

import com.lijiawei.practice.mymall.learning.init.user.domain.UmsRole;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UmsRole record);

    int insertOrUpdate(UmsRole record);

    int insertOrUpdateSelective(UmsRole record);

    int insertSelective(UmsRole record);

    UmsRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsRole record);

    int updateByPrimaryKey(UmsRole record);

    int updateBatch(List<UmsRole> list);

    int updateBatchSelective(List<UmsRole> list);

    int batchInsert(@Param("list") List<UmsRole> list);
}