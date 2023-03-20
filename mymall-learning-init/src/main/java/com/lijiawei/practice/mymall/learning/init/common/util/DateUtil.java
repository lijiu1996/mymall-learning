package com.lijiawei.practice.mymall.learning.init.common.util;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * @author Li JiaWei
 * @ClassName: DateUtil
 * @Description:
 *      pb4 最佳实践 如何使用java8日期时间类
 *          1. 如何进行日期转换
 *          2. 如何计算时间差  -- 判断哪个时间在前
 * @Date: 2023/3/20 13:44
 * @Version: 1.0
 */
public class DateUtil {

    public static final String defaultDateFormat = "yyyy-MM-dd HH:mm:ss";

    public static final ZoneId defaultZoneId = ZoneId.systemDefault();

    public static final ZoneOffset defaultZoneOffset = OffsetDateTime.now().getOffset();

    public static final DateTimeFormatter defaultDateFormatter = DateTimeFormatter.ofPattern(defaultDateFormat);

    public static String dateToString(LocalDateTime date) {
        return date.format(defaultDateFormatter);
    }

    public static long dateToMilliSecond(LocalDateTime date) {
        return date.atZone(defaultZoneId).
                toInstant().toEpochMilli();
    }

    public static long dateToSecond(LocalDateTime date) {
        return date.atZone(defaultZoneId).
                toInstant().getEpochSecond();
    }

    public static LocalDateTime stringToDate(String dateStr) {
        return LocalDateTime.parse(dateStr,defaultDateFormatter);
    }

    public static LocalDateTime secondToDate(long second) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(second),defaultZoneId);
    }

    public static LocalDateTime milliSecondToDate(long milli) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(milli),defaultZoneId);
    }


    public static long getDistanceSeconds(LocalDateTime l1, LocalDateTime l2) {
        Duration between = Duration.between(l1, l2);
        return between.toSeconds();
    }
}
