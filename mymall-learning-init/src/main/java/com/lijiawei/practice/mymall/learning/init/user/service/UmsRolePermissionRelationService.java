package com.lijiawei.practice.mymall.learning.init.user.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.lijiawei.practice.mymall.learning.init.user.domain.UmsRolePermissionRelation;
import com.lijiawei.practice.mymall.learning.init.user.mapper.UmsRolePermissionRelationMapper;
@Service
public class UmsRolePermissionRelationService{

    @Resource
    private UmsRolePermissionRelationMapper umsRolePermissionRelationMapper;

    
    public int deleteByPrimaryKey(Long id) {
        return umsRolePermissionRelationMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(UmsRolePermissionRelation record) {
        return umsRolePermissionRelationMapper.insert(record);
    }

    
    public int insertOrUpdate(UmsRolePermissionRelation record) {
        return umsRolePermissionRelationMapper.insertOrUpdate(record);
    }

    
    public int insertOrUpdateSelective(UmsRolePermissionRelation record) {
        return umsRolePermissionRelationMapper.insertOrUpdateSelective(record);
    }

    
    public int insertSelective(UmsRolePermissionRelation record) {
        return umsRolePermissionRelationMapper.insertSelective(record);
    }

    
    public UmsRolePermissionRelation selectByPrimaryKey(Long id) {
        return umsRolePermissionRelationMapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(UmsRolePermissionRelation record) {
        return umsRolePermissionRelationMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(UmsRolePermissionRelation record) {
        return umsRolePermissionRelationMapper.updateByPrimaryKey(record);
    }

    
    public int updateBatch(List<UmsRolePermissionRelation> list) {
        return umsRolePermissionRelationMapper.updateBatch(list);
    }

    
    public int updateBatchSelective(List<UmsRolePermissionRelation> list) {
        return umsRolePermissionRelationMapper.updateBatchSelective(list);
    }

    
    public int batchInsert(List<UmsRolePermissionRelation> list) {
        return umsRolePermissionRelationMapper.batchInsert(list);
    }

}
