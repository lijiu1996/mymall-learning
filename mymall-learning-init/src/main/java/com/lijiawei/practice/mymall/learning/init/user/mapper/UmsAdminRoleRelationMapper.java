package com.lijiawei.practice.mymall.learning.init.user.mapper;

import com.lijiawei.practice.mymall.learning.init.user.domain.UmsAdminRoleRelation;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsAdminRoleRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UmsAdminRoleRelation record);

    int insertOrUpdate(UmsAdminRoleRelation record);

    int insertOrUpdateSelective(UmsAdminRoleRelation record);

    int insertSelective(UmsAdminRoleRelation record);

    UmsAdminRoleRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsAdminRoleRelation record);

    int updateByPrimaryKey(UmsAdminRoleRelation record);

    int updateBatch(List<UmsAdminRoleRelation> list);

    int updateBatchSelective(List<UmsAdminRoleRelation> list);

    int batchInsert(@Param("list") List<UmsAdminRoleRelation> list);
}