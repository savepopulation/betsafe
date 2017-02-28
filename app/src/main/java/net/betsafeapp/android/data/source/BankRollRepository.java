package net.betsafeapp.android.data.source;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;

import net.betsafeapp.android.data.BankRoll;
import net.betsafeapp.android.data.Pick;
import net.betsafeapp.android.data.source.local.BankRollLocalDataSource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by tyln on 17/01/2017.
 */

@Singleton
public final class BankRollRepository implements BankRollDataSource {
    @NonNull
    private BankRollLocalDataSource mBankRollLocalDataSource;

    @Nullable
    private ArrayMap<String, BankRoll> mBankrollCache;

    public BankRollRepository() {
        // Public empty constructor
    }

    @Inject
    BankRollRepository(@NonNull @Local BankRollLocalDataSource bankRollLocalDataSource) {
        this.mBankRollLocalDataSource = bankRollLocalDataSource;
    }

    @Override
    public Observable<BankRoll> getBankRolls() {
        if (mBankrollCache != null && mBankrollCache.size() > 0) {
            return Observable.from(mBankrollCache.values());
        } else {
            return mBankRollLocalDataSource.getBankRolls()
                    .doOnNext(new Action1<BankRoll>() {
                        @Override
                        public void call(BankRoll bankRoll) {
                            initCacheIfNeededAndPutBankroll(bankRoll);
                        }
                    });
        }
    }

    @Override
    public void createNewBankroll(@NonNull BankRoll bankRoll) {
        initCacheIfNeededAndPutBankroll(bankRoll);
        mBankRollLocalDataSource.createNewBankroll(bankRoll);
    }

    @Override
    public Observable<Pick> getPicks() {
        return mBankRollLocalDataSource.getPicks();
    }

    private void initCacheIfNeededAndPutBankroll(@NonNull BankRoll bankRoll) {
        if (bankRoll == null) {
            return;
        }

        if (mBankrollCache == null) {
            mBankrollCache = new ArrayMap<>();
        }

        if (!mBankrollCache.containsKey(bankRoll.getId())) {
            mBankrollCache.put(bankRoll.getId(), bankRoll);
        }
    }
}
