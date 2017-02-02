package net.betsafeapp.android.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

/**
 * Created by tyln on 28/01/2017.
 */

public final class ValidationUtil {
    private ValidationUtil() {
        // Private Emptry Constructor
    }

    public static boolean isNullOrEmpty(@Nullable String s) {
        return TextUtils.isEmpty(s) || s.trim().length() == 0;
    }

    public static boolean isInitialAmountValid(double initialAmount) {
        return initialAmount > 0;
    }
}
