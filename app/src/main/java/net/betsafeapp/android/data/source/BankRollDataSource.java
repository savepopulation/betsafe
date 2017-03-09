package net.betsafeapp.android.data.source;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;

import net.betsafeapp.android.data.BankRoll;
import net.betsafeapp.android.data.Bet;
import net.betsafeapp.android.data.Pick;

import rx.Observable;

/**
 * Created by tyln on 17/01/2017.
 */

public interface BankRollDataSource {
    Observable<BankRoll> getBankRolls();

    void createNewBankroll(@NonNull BankRoll bankRoll);

    Observable<BankRoll> searchBankroll(@Nullable String query);

    Observable<Pick> getPicks();
}
