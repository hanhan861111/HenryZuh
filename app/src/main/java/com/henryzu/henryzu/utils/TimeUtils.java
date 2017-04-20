package com.henryzu.henryzu.utils;

import java.text.SimpleDateFormat;

/**
 * Created by 郭艳杰 on 17/1/8.
 */

public class TimeUtils {

    public static String formatTime(int time) {
        if (time < 0) {
            return "00:00";
        }
        String pattern = "HH:mm:ss";

        if (time < 1000 * 60 * 60) {
            pattern = "mm:ss";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String format = simpleDateFormat.format(time);
        return format;

    }
}
