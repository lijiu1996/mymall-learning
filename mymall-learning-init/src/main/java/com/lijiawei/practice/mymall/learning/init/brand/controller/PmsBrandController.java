package com.lijiawei.practice.mymall.learning.init.brand.controller;

import com.lijiawei.practice.mymall.learning.init.brand.domain.PmsBrand;
import com.lijiawei.practice.mymall.learning.init.brand.service.PmsBrandService;
import com.lijiawei.practice.mymall.learning.init.common.api.CommonPage;
import com.lijiawei.practice.mymall.learning.init.common.api.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 品牌管理Controller
 * Created by macro on 2019/4/19.
 */
@Slf4j
@RestController
@RequestMapping("/brand")
public class PmsBrandController {

    @Autowired
    private PmsBrandService pmsBrandService;

    @PreAuthorize("hasAuthority('pms:brand:read')")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public CommonResult<List<PmsBrand>> getBrandList() {
        return CommonResult.success(pmsBrandService.selectAll());
    }

    @PreAuthorize("hasAuthority('pms:brand:create')")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult createBrand(@RequestBody PmsBrand pmsBrand) {
        int insert = pmsBrandService.insert(pmsBrand);
        if (insert == 1) {
            return CommonResult.success(pmsBrand);
        } else
            return CommonResult.failed();
    }

    @PreAuthorize("hasAuthority('pms:brand:update')")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult updateBrand(@PathVariable("id") Long id, @RequestBody PmsBrand pmsBrandDto, BindingResult result) {
        pmsBrandDto.setId(id);
        return CommonResult.success(pmsBrandService.updateByPrimaryKey(pmsBrandDto));
    }

    @PreAuthorize("hasAuthority('pms:brand:delete')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public CommonResult deleteBrand(@PathVariable("id") Long id) {
        return CommonResult.success(pmsBrandService.deleteByPrimaryKey(id));
    }

    @PreAuthorize("hasAuthority('pms:brand:read')")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<PmsBrand>> listBrand(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                        @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) {
        List<PmsBrand> pmsBrands = pmsBrandService.selectAllByPage(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(pmsBrands));
    }

    @PreAuthorize("hasAuthority('pms:brand:read')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommonResult<PmsBrand> brand(@PathVariable("id") Long id) {
        return CommonResult.success(pmsBrandService.selectByPrimaryKey(id));
    }
}
