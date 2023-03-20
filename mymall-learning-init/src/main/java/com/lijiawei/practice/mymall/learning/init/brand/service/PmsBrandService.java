package com.lijiawei.practice.mymall.learning.init.brand.service;

import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.lijiawei.practice.mymall.learning.init.brand.mapper.PmsBrandMapper;
import com.lijiawei.practice.mymall.learning.init.brand.domain.PmsBrand;

@Service
public class PmsBrandService {

    @Resource
    private PmsBrandMapper pmsBrandMapper;

    public int deleteByPrimaryKey(Long id) {
        return pmsBrandMapper.deleteByPrimaryKey(id);
    }

    public int insert(PmsBrand record) {
        return pmsBrandMapper.insert(record);
    }

    public int insertOrUpdate(PmsBrand record) {
        return pmsBrandMapper.insertOrUpdate(record);
    }

    public int insertOrUpdateSelective(PmsBrand record) {
        return pmsBrandMapper.insertOrUpdateSelective(record);
    }

    public int insertSelective(PmsBrand record) {
        return pmsBrandMapper.insertSelective(record);
    }

    public PmsBrand selectByPrimaryKey(Long id) {
        return pmsBrandMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(PmsBrand record) {
        return pmsBrandMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(PmsBrand record) {
        return pmsBrandMapper.updateByPrimaryKey(record);
    }

    public int updateBatch(List<PmsBrand> list) {
        return pmsBrandMapper.updateBatch(list);
    }

    public int updateBatchSelective(List<PmsBrand> list) {
        return pmsBrandMapper.updateBatchSelective(list);
    }

    public int batchInsert(List<PmsBrand> list) {
        return pmsBrandMapper.batchInsert(list);
    }

    public List<PmsBrand> selectAll() {
        return pmsBrandMapper.findAll();
    }

    public List<PmsBrand> selectAllByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return pmsBrandMapper.findAll();
    }
}
