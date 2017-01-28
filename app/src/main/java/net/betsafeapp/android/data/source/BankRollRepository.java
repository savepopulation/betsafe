package net.betsafeapp.android.data.source;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;

import net.betsafeapp.android.data.BankRoll;
import net.betsafeapp.android.data.source.local.BankRollLocalDataSource;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by tyln on 17/01/2017.
 */

@Singleton
public final class BankRollRepository implements BankRollDataSource {
    @NonNull
    private BankRollLocalDataSource mBankRollLocalDataSource;

    @Nullable
    private ArrayMap<String, BankRoll> mBankrollCache;

    @Inject
    BankRollRepository(@NonNull @Local BankRollLocalDataSource bankRollLocalDataSource) {
        this.mBankRollLocalDataSource = bankRollLocalDataSource;
    }

    @Override
    public void createNewBankroll(@NonNull BankRoll bankRoll) {
        initCacheIfNeededAndPutBankroll(bankRoll);
        mBankRollLocalDataSource.createNewBankroll(bankRoll);
    }

    private void initCacheIfNeededAndPutBankroll(@NonNull BankRoll bankRoll) {
        if (mBankrollCache == null) {
            mBankrollCache = new ArrayMap<>();
        }

        mBankrollCache.put(bankRoll.getId(), bankRoll);
    }
}
