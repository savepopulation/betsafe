package net.betsafeapp.android.data.source;

import android.support.annotation.NonNull;

import net.betsafeapp.android.data.BankRoll;
import net.betsafeapp.android.data.Pick;

import rx.Observable;

/**
 * Created by tyln on 17/01/2017.
 */

public interface BankRollDataSource {
    Observable<BankRoll> getBankRolls();

    void createNewBankroll(@NonNull BankRoll bankRoll);

    Observable<Pick> getPicks();
}
