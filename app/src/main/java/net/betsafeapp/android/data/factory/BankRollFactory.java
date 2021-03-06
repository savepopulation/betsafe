package net.betsafeapp.android.data.factory;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import net.betsafeapp.android.Constants;
import net.betsafeapp.android.data.BankRoll;
import net.betsafeapp.android.util.ValidationUtil;

import java.util.UUID;

/**
 * Created by tyln on 28/01/2017.
 */

public final class BankRollFactory {

    private BankRollFactory() {
        // Empty Private Constructor
    }

    // Creates an initial bankroll
    @Nullable
    public static BankRoll newInstance(@NonNull String name, double initialCapital, int privacy) {
        if (ValidationUtil.isNullOrEmpty(name) || !ValidationUtil.isInitialAmountValid(initialCapital)) {
            return null;
        }

        final BankRoll bankRoll = new BankRoll();
        bankRoll.setId(UUID.randomUUID().toString());
        bankRoll.setName(name);
        bankRoll.setType(Constants.BANKROLL_TYPE_DEFAULT);
        bankRoll.setPrivacy(privacy);
        bankRoll.setInitialCapital(initialCapital);
        bankRoll.setCurrentCapital(initialCapital);
        bankRoll.setStatus(Constants.BANKROLL_STATUS_OPEN);
        bankRoll.setCreateDate(System.currentTimeMillis());
        bankRoll.setUpdateDate(System.currentTimeMillis());

        return bankRoll;
    }
}
