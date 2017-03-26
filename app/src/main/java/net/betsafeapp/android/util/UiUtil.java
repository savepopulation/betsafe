package net.betsafeapp.android.util;

import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import net.betsafeapp.android.Constants;
import net.betsafeapp.android.R;

/**
 * Created by tyln on 15/03/2017.
 */

public final class UiUtil {

    private UiUtil() {
        // Private emtpy constructor
    }

    @ColorRes
    public static int getColorResByBetStatus(int status) {
        switch (status) {
            case Constants.BANKROLL_STATUS_CLOSED:
                return R.color.colorClosed;

            default:
                return android.R.color.white;
        }
    }

    @NonNull
    public static String generateViewPagerFragmentTag(int viewPagerId, int position) {
        final StringBuilder tagBuilder = new StringBuilder();
        tagBuilder.append("android:switcher:");
        tagBuilder.append(viewPagerId);
        tagBuilder.append(":");
        tagBuilder.append(position);
        return tagBuilder.toString();
    }
}
