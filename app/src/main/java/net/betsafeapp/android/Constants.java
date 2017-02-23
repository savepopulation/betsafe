package net.betsafeapp.android;

/**
 * Created by tyln on 16/01/2017.
 */

public final class Constants {
    private Constants() {
        // Empty Private Constructor
    }

    // RES
    public static final int NO_RES = 0;

    // DEFAULT VALUES
    public static final String DEFAULT_AMOUNT = "0";

    // BANKROLL PRIVACY
    public static final int BANKROLL_PRIVACY_PRIVATE = 0;
    public static final int BANKROLL_PRIVACY_PUBLIC = 1;

    // BANKROLL STATUS
    public static final int BANKROLL_STATUS_OPEN = 0;
    public static final int BANKROLL_STATUS_CLOSED = 1;
    public static final int BANKROLL_STATUS_COMPLETED = 2;

    // BANKROLL TYPES
    public static final int BANKROLL_TYPE_DEFAULT = 0;

    // BET RESULTS
    public static final int BET_STATUS_PENDING = 0;
    public static final int BET_STATUS_WON = 1;
    public static final int BET_STATUS_LOSE = 2;
    public static final int BET_STATUS_DRAW = 3;
    public static final int BET_STATUS_CANCEL = 4;
    public static final int BET_STATUS_POSTPONED = 5;
}
