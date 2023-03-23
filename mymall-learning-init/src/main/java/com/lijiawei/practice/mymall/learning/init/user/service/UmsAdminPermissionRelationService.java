package com.lijiawei.practice.mymall.learning.init.user.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.lijiawei.practice.mymall.learning.init.user.mapper.UmsAdminPermissionRelationMapper;
import com.lijiawei.practice.mymall.learning.init.user.domain.UmsAdminPermissionRelation;
@Service
public class UmsAdminPermissionRelationService{

    @Resource
    private UmsAdminPermissionRelationMapper umsAdminPermissionRelationMapper;

    
    public int deleteByPrimaryKey(Long id) {
        return umsAdminPermissionRelationMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(UmsAdminPermissionRelation record) {
        return umsAdminPermissionRelationMapper.insert(record);
    }

    
    public int insertOrUpdate(UmsAdminPermissionRelation record) {
        return umsAdminPermissionRelationMapper.insertOrUpdate(record);
    }

    
    public int insertOrUpdateSelective(UmsAdminPermissionRelation record) {
        return umsAdminPermissionRelationMapper.insertOrUpdateSelective(record);
    }

    
    public int insertSelective(UmsAdminPermissionRelation record) {
        return umsAdminPermissionRelationMapper.insertSelective(record);
    }

    
    public UmsAdminPermissionRelation selectByPrimaryKey(Long id) {
        return umsAdminPermissionRelationMapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(UmsAdminPermissionRelation record) {
        return umsAdminPermissionRelationMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(UmsAdminPermissionRelation record) {
        return umsAdminPermissionRelationMapper.updateByPrimaryKey(record);
    }

    
    public int updateBatch(List<UmsAdminPermissionRelation> list) {
        return umsAdminPermissionRelationMapper.updateBatch(list);
    }

    
    public int updateBatchSelective(List<UmsAdminPermissionRelation> list) {
        return umsAdminPermissionRelationMapper.updateBatchSelective(list);
    }

    
    public int batchInsert(List<UmsAdminPermissionRelation> list) {
        return umsAdminPermissionRelationMapper.batchInsert(list);
    }

}
