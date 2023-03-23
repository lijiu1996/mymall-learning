package com.lijiawei.practice.mymall.learning.init.user.service;

import com.lijiawei.practice.mymall.learning.init.user.domain.UmsPermission;
import com.lijiawei.practice.mymall.learning.init.user.mapper.UmsCustomMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.lijiawei.practice.mymall.learning.init.user.mapper.UmsAdminMapper;
import com.lijiawei.practice.mymall.learning.init.user.domain.UmsAdmin;
@Service
public class UmsAdminService{

    @Resource
    private UmsAdminMapper umsAdminMapper;

    @Resource
    private UmsCustomMapper umsCustomMapper;
    
    public int deleteByPrimaryKey(Long id) {
        return umsAdminMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(UmsAdmin record) {
        return umsAdminMapper.insert(record);
    }

    
    public int insertOrUpdate(UmsAdmin record) {
        return umsAdminMapper.insertOrUpdate(record);
    }

    
    public int insertOrUpdateSelective(UmsAdmin record) {
        return umsAdminMapper.insertOrUpdateSelective(record);
    }

    
    public int insertSelective(UmsAdmin record) {
        return umsAdminMapper.insertSelective(record);
    }

    
    public UmsAdmin selectByPrimaryKey(Long id) {
        return umsAdminMapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(UmsAdmin record) {
        return umsAdminMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(UmsAdmin record) {
        return umsAdminMapper.updateByPrimaryKey(record);
    }

    
    public int updateBatch(List<UmsAdmin> list) {
        return umsAdminMapper.updateBatch(list);
    }

    
    public int updateBatchSelective(List<UmsAdmin> list) {
        return umsAdminMapper.updateBatchSelective(list);
    }

    
    public int batchInsert(List<UmsAdmin> list) {
        return umsAdminMapper.batchInsert(list);
    }

    public UmsAdmin getAdminByUsername(String username) {
        return umsAdminMapper.selectFirstByUsername(username);
    }

    public List<UmsPermission> getPermissionList(Long id) {
        return umsCustomMapper.getUmsPermissionListByAdminId(id);
    }
}
