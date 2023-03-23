package com.lijiawei.practice.mymall.learning.init.user.mapper;

import com.lijiawei.practice.mymall.learning.init.user.domain.UmsPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsCustomMapper {

    List<UmsPermission> getUmsPermissionListByAdminId(@Param("adminId") Long adminId);
}
