package net.betsafeapp.android.util;

import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;

/**
 * Created by tyln on 30/01/2017.
 */

public final class DateUtil {

    private DateUtil() {
        // Empty Private Constructor
    }

    private static final String DEFAULT_DATE_FORMAT = "dd.MM.yyyy";
    public static final String DATE_FORMAT_BET_ROW = "dd.MM.yyyy - HH:mm";

    @NonNull
    public static String convertTime(long timeInMilis) {
        try {
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
            return simpleDateFormat.format(timeInMilis);
        } catch (Exception e) {
            return "";
        }

    }

    @NonNull
    public static String convertTime(long timeInMilis, @NonNull String format) {
        try {
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            return simpleDateFormat.format(timeInMilis);
        } catch (Exception ex) {
            return "";
        }
    }
}
