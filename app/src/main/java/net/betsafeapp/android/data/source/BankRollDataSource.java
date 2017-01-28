package net.betsafeapp.android.data.source;

import android.support.annotation.NonNull;

import net.betsafeapp.android.data.BankRoll;

/**
 * Created by tyln on 17/01/2017.
 */

public interface BankRollDataSource {
    void createNewBankroll(@NonNull BankRoll bankRoll);
}
