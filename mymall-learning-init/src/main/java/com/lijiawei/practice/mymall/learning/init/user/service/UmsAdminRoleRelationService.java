package com.lijiawei.practice.mymall.learning.init.user.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.lijiawei.practice.mymall.learning.init.user.mapper.UmsAdminRoleRelationMapper;
import com.lijiawei.practice.mymall.learning.init.user.domain.UmsAdminRoleRelation;
@Service
public class UmsAdminRoleRelationService{

    @Resource
    private UmsAdminRoleRelationMapper umsAdminRoleRelationMapper;

    
    public int deleteByPrimaryKey(Long id) {
        return umsAdminRoleRelationMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(UmsAdminRoleRelation record) {
        return umsAdminRoleRelationMapper.insert(record);
    }

    
    public int insertOrUpdate(UmsAdminRoleRelation record) {
        return umsAdminRoleRelationMapper.insertOrUpdate(record);
    }

    
    public int insertOrUpdateSelective(UmsAdminRoleRelation record) {
        return umsAdminRoleRelationMapper.insertOrUpdateSelective(record);
    }

    
    public int insertSelective(UmsAdminRoleRelation record) {
        return umsAdminRoleRelationMapper.insertSelective(record);
    }

    
    public UmsAdminRoleRelation selectByPrimaryKey(Long id) {
        return umsAdminRoleRelationMapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(UmsAdminRoleRelation record) {
        return umsAdminRoleRelationMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(UmsAdminRoleRelation record) {
        return umsAdminRoleRelationMapper.updateByPrimaryKey(record);
    }

    
    public int updateBatch(List<UmsAdminRoleRelation> list) {
        return umsAdminRoleRelationMapper.updateBatch(list);
    }

    
    public int updateBatchSelective(List<UmsAdminRoleRelation> list) {
        return umsAdminRoleRelationMapper.updateBatchSelective(list);
    }

    
    public int batchInsert(List<UmsAdminRoleRelation> list) {
        return umsAdminRoleRelationMapper.batchInsert(list);
    }

}
