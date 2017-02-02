package net.betsafeapp.android.util;

import android.net.ParseException;
import android.support.annotation.Nullable;

/**
 * Created by tyln on 02/02/2017.
 */

public final class Utils {
    private Utils() {
        // Empty Private Constructor
    }

    public static double convertStringToDouble(@Nullable String s) {
        if (ValidationUtil.isNullOrEmpty(s)) {
            return 0;
        }

        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }
}
