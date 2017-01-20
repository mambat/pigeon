package com.raiyee.pigeon.utils;

import org.apache.commons.lang.StringUtils;

import java.util.Properties;

import static org.apache.commons.lang.StringUtils.trim;

/**
 * Properties APIs.
 *
 * @author wanglei
 * @date 17/1/19 下午3:35
 */
public class Props {

    public static String getString(Properties properties, String key) {
        return getString(properties, key, null);
    }

    public static String getString(Properties properties, String key, String defaultValue) {
        String value = trim(properties.getProperty(trim(key)));
        return StringUtils.isEmpty(value) ? defaultValue : value;
    }

    public static int getInt(Properties properties, String key) {
        return getInt(properties, key, 0);
    }

    public static int getInt(Properties properties, String key, int defaultValue) {
        String value = trim(properties.getProperty(trim(key)));
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static boolean getBoolean(Properties properties, String key) {
        String value = trim(properties.getProperty(trim(key)));
        return Boolean.parseBoolean(value);
    }

    public static boolean getBoolean(Properties properties, String key, boolean defaultValue) {
        String value = trim(properties.getProperty(trim(key)));
        if (StringUtils.isEmpty(value)) {
            return defaultValue;
        }
        return Boolean.parseBoolean(value);
    }
}
