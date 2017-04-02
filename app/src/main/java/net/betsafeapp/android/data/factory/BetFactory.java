package net.betsafeapp.android.data.factory;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import net.betsafeapp.android.Constants;
import net.betsafeapp.android.data.Bet;
import net.betsafeapp.android.data.Pick;
import net.betsafeapp.android.util.ValidationUtil;

import java.util.UUID;

/**
 * Created by tyln on 01/03/2017.
 */

public final class BetFactory {

    private BetFactory() {
        // Private empty constructor
    }

    @Nullable
    public static Bet newInstance(@NonNull String eventName,
                                  @NonNull String bookmaker,
                                  @NonNull String bankrollId,
                                  double odd,
                                  double stake,
                                  int sport,
                                  Pick pick) {
        if (ValidationUtil.isNullOrEmpty(eventName) || ValidationUtil.isNullOrEmpty(bookmaker)) {
            return null;
        }

        if (odd <= 0 || stake <= 0 || pick == null) {
            return null;
        }

        return new Bet(UUID.randomUUID().toString(),
                eventName,
                bankrollId,
                bookmaker,
                sport,
                odd,
                stake,
                pick,
                Constants.BET_STATUS_PENDING,
                System.currentTimeMillis(),
                System.currentTimeMillis());
    }
}


