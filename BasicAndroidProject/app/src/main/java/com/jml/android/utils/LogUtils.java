package com.jml.android.utils;

import android.util.Log;

import com.jml.android.BuildConfig;


/**
 * Created by jose on 28/02/15.
 *
 * @version 0.1.0
 * @since 1
 */
public class LogUtils {

    private static final String LOG_PREFIX = "demo_";
    private static final int LOG_PREFIX_LENGTH = LOG_PREFIX.length();
    private static final int MAX_LOG_TAG_LENGTH = 23;

    public static String makeLogTag(String str) {
        if (str.length() > MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH) {
            return LOG_PREFIX + str.substring(0, MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH - 1);
        }

        return LOG_PREFIX + str;
    }

    /**
     * Don't use this when obfuscating class names!
     */
    public static String makeLogTag(Class cls) {
        return makeLogTag(cls.getSimpleName());
    }

    public static void LOGD(final String tag, String message) {
        //noinspection PointlessBooleanExpression,ConstantConditions
        if (BuildConfig.DEBUG || Log.isLoggable(tag, Log.DEBUG)) {
            Log.d(makeLogTag( tag ), message);
        }
    }

    public static void LOGD(final String tag, String message, Throwable cause) {
        //noinspection PointlessBooleanExpression,ConstantConditions
        if (BuildConfig.DEBUG || Log.isLoggable(tag, Log.DEBUG)) {
            Log.d(makeLogTag(tag), message, cause);
        }
    }

    public static void LOGV(final String tag, String message) {
        //noinspection PointlessBooleanExpression,ConstantConditions
        if (BuildConfig.DEBUG && Log.isLoggable(tag, Log.VERBOSE)) {
            Log.v(makeLogTag(tag), message);
        }
    }

    public static void LOGV(final String tag, String message, Throwable cause) {
        //noinspection PointlessBooleanExpression,ConstantConditions
        if (BuildConfig.DEBUG && Log.isLoggable(tag, Log.VERBOSE)) {
            Log.v( makeLogTag( tag ), message, cause);
        }
    }

    public static void LOGI(final String tag, String message) {
        Log.i( makeLogTag( tag ) , message);
    }

    public static void LOGI(final String tag, String message, Throwable cause) {
        Log.i( makeLogTag(tag), message, cause);
    }

    public static void LOGW(final String tag, String message) {
        Log.w ( makeLogTag( tag ), message);
    }

    public static void LOGW(final String tag, String message, Throwable cause) {
        Log.w( makeLogTag( tag ), message, cause);
    }

    public static void LOGE(final String tag, String message) {
        Log.e( makeLogTag( tag), message);
    }

    public static void LOGE(final String tag, String message, Throwable cause) {
        Log.e( makeLogTag(tag), message, cause);
    }

    private LogUtils() {
    }
}
