package net.betsafeapp.android.data.source;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;

import net.betsafeapp.android.data.BankRoll;
import net.betsafeapp.android.data.Bet;
import net.betsafeapp.android.data.Pick;
import net.betsafeapp.android.data.source.local.BankRollLocalDataSource;
import net.betsafeapp.android.util.ValidationUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.RealmList;
import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

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
    @NonNull
    public Observable<BankRoll> getBankRolls() {
        if (mBankrollCache != null && mBankrollCache.size() > 0) {
            return Observable.from(mBankrollCache.values());
        }

        return mBankRollLocalDataSource.getBankRolls()
                .doOnNext(new Action1<BankRoll>() {
                    @Override
                    public void call(BankRoll bankRoll) {
                        initCacheIfNeededAndPutBankroll(bankRoll);
                    }
                });
    }

    @Override
    public void createNewBankroll(@NonNull BankRoll bankRoll) {
        initCacheIfNeededAndPutBankroll(bankRoll);
        mBankRollLocalDataSource.createNewBankroll(bankRoll);
    }

    @Override
    @NonNull
    public Observable<BankRoll> searchBankroll(@Nullable final String query) {
        return getBankRolls().filter(new Func1<BankRoll, Boolean>() {
            @Override
            public Boolean call(BankRoll bankRoll) {
                return ValidationUtil.isNullOrEmpty(query)
                        || ValidationUtil.isNullOrEmpty(bankRoll.getName())
                        || bankRoll.getName().contains(query);
            }
        });
    }

    @Override
    public Observable<BankRoll> getBankRoll(@NonNull String bankRollId) {
        if (mBankrollCache != null && mBankrollCache.containsKey(bankRollId)) {
            final BankRoll bankRoll = mBankrollCache.get(bankRollId);
            if (bankRoll == null) {
                return Observable.empty();
            }
            return Observable.just(mBankrollCache.get(bankRollId));
        }

        return mBankRollLocalDataSource.getBankRoll(bankRollId)
                .map(new Func1<BankRoll, BankRoll>() {
                    @Override
                    public BankRoll call(BankRoll bankRoll) {
                        if (bankRoll == null) {
                            throw new IllegalArgumentException("UnKnown Error!");
                        }

                        return bankRoll;
                    }
                });
    }

    @Override
    @NonNull
    public Observable<Pick> getPicks() {
        return mBankRollLocalDataSource.getPicks();
    }

    public void addBet(@Nullable String bankrollId, @Nullable Bet bet) {
        final BankRoll bankRoll = mBankrollCache.get(bankrollId);
        if (bankRoll == null || bet == null) {
            return;
        }
        if (bankRoll.getBets() == null) {
            bankRoll.setBets(new RealmList<Bet>());
        }
        bankRoll.getBets().add(bet);
        mBankRollLocalDataSource.saveBankroll(bankRoll);
    }

    private void initCacheIfNeededAndPutBankroll(@Nullable BankRoll bankRoll) {
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
