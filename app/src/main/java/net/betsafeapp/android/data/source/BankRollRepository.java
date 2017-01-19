package net.betsafeapp.android.data.source;

import android.support.annotation.NonNull;

import net.betsafeapp.android.data.source.local.BankRollLocalDataSource;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by tyln on 17/01/2017.
 */

@Singleton
public class BankRollRepository implements BankRollDataSource {
    @NonNull
    private BankRollLocalDataSource mBankRollLocalDataSource;

    @Inject
    BankRollRepository(@NonNull @Local BankRollLocalDataSource bankRollLocalDataSource) {
        this.mBankRollLocalDataSource = bankRollLocalDataSource;
    }
}
