package com.lijiawei.practice.mymall.learning.init.common.constant;

/**
 * @author Li JiaWei
 * @ClassName: RedisConstant
 * @Description:
 * @Date: 2023/3/21 14:28
 * @Version: 1.0
 */
public class RedisConstant {

    /**
     * pb6 最佳实践 redis前缀命名规范 服务:业务模块:具体业务名:id
     *  后期如果进行了微服务拆分 将业务模块以及具体业务名合并
     */

    public static final String REDIS_PREFIX_AUTH_CODE = "mall:login:authCode:%s";

    public static final Long   REDIS_EXPIRE_AUTH_CODE = 120L;

}
