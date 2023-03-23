package com.lijiawei.practice.mymall.learning.init.user.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lijiawei.practice.mymall.learning.init.user.domain.UmsRole;
import java.util.List;
import com.lijiawei.practice.mymall.learning.init.user.mapper.UmsRoleMapper;
@Service
public class UmsRoleService{

    @Resource
    private UmsRoleMapper umsRoleMapper;

    
    public int deleteByPrimaryKey(Long id) {
        return umsRoleMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(UmsRole record) {
        return umsRoleMapper.insert(record);
    }

    
    public int insertOrUpdate(UmsRole record) {
        return umsRoleMapper.insertOrUpdate(record);
    }

    
    public int insertOrUpdateSelective(UmsRole record) {
        return umsRoleMapper.insertOrUpdateSelective(record);
    }

    
    public int insertSelective(UmsRole record) {
        return umsRoleMapper.insertSelective(record);
    }

    
    public UmsRole selectByPrimaryKey(Long id) {
        return umsRoleMapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(UmsRole record) {
        return umsRoleMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(UmsRole record) {
        return umsRoleMapper.updateByPrimaryKey(record);
    }

    
    public int updateBatch(List<UmsRole> list) {
        return umsRoleMapper.updateBatch(list);
    }

    
    public int updateBatchSelective(List<UmsRole> list) {
        return umsRoleMapper.updateBatchSelective(list);
    }

    
    public int batchInsert(List<UmsRole> list) {
        return umsRoleMapper.batchInsert(list);
    }

}
