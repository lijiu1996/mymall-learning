package com.lijiawei.practice.mymall.learning.init.user.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lijiawei.practice.mymall.learning.init.user.mapper.UmsPermissionMapper;
import com.lijiawei.practice.mymall.learning.init.user.domain.UmsPermission;
import java.util.List;
@Service
public class UmsPermissionService{

    @Resource
    private UmsPermissionMapper umsPermissionMapper;

    
    public int deleteByPrimaryKey(Long id) {
        return umsPermissionMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(UmsPermission record) {
        return umsPermissionMapper.insert(record);
    }

    
    public int insertOrUpdate(UmsPermission record) {
        return umsPermissionMapper.insertOrUpdate(record);
    }

    
    public int insertOrUpdateSelective(UmsPermission record) {
        return umsPermissionMapper.insertOrUpdateSelective(record);
    }

    
    public int insertSelective(UmsPermission record) {
        return umsPermissionMapper.insertSelective(record);
    }

    
    public UmsPermission selectByPrimaryKey(Long id) {
        return umsPermissionMapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(UmsPermission record) {
        return umsPermissionMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(UmsPermission record) {
        return umsPermissionMapper.updateByPrimaryKey(record);
    }

    
    public int updateBatch(List<UmsPermission> list) {
        return umsPermissionMapper.updateBatch(list);
    }

    
    public int updateBatchSelective(List<UmsPermission> list) {
        return umsPermissionMapper.updateBatchSelective(list);
    }

    
    public int batchInsert(List<UmsPermission> list) {
        return umsPermissionMapper.batchInsert(list);
    }

}
