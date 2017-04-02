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
    public static int getColorResByBankRollStatus(int status) {
        switch (status) {
            case Constants.BANKROLL_STATUS_CLOSED:
                return R.color.colorClosed;

            default:
                return android.R.color.white;
        }
    }

    @ColorRes
    public static int getColorResByBetStatus(int status) {
        switch (status) {
            case Constants.BET_STATUS_PENDING:
                return R.color.colorPendingYellow;

            case Constants.BET_STATUS_WON:
                return R.color.colorWonGreen;

            case Constants.BET_STATUS_LOSE:
                return R.color.colorLooseRed;

            case Constants.BET_STATUS_DRAW:
            case Constants.BET_STATUS_CANCEL:
            case Constants.BET_STATUS_POSTPONED:
            default:
                return R.color.colorDivider;
        }
    }

    @StringRes
    public static int getStringResForBetStatus(int status) {
        switch (status) {
            case Constants.BET_STATUS_PENDING:
                return R.string.text_bet_status_pending;

            case Constants.BET_STATUS_WON:
                return R.string.text_bet_status_won;

            case Constants.BET_STATUS_LOSE:
                return R.string.text_bet_status_lost;

            case Constants.BET_STATUS_DRAW:
                return R.string.text_bet_status_draw;

            case Constants.BET_STATUS_CANCEL:
                return R.string.text_bet_status_cancel;

            case Constants.BET_STATUS_POSTPONED:
                return R.string.text_bet_status_postponed;

            default:
                return R.string.unknown;
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
