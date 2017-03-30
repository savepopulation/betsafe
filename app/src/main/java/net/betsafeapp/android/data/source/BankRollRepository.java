package net.betsafeapp.android.data.source;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;

import net.betsafeapp.android.Constants;
import net.betsafeapp.android.data.BankRoll;
import net.betsafeapp.android.data.Bet;
import net.betsafeapp.android.data.Pick;
import net.betsafeapp.android.data.factory.BankRollFactory;
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
        final BankRoll bankRoll = getBankRollFromCache(bankRollId);
        if (bankRoll != null) {
            return Observable.just(bankRoll);
        }

        return mBankRollLocalDataSource.getBankRoll(bankRollId)
                .map(new Func1<BankRoll, BankRoll>() {
                    @Override
                    public BankRoll call(BankRoll bankRoll) {
                        if (bankRoll == null) {
                            throw new IllegalArgumentException("Unknown Error!");
                        }

                        return bankRoll;
                    }
                });
    }

    @Override
    public void deleteBankRoll(@NonNull String bankRollId) {
        removeBankRollFromCache(bankRollId);
        mBankRollLocalDataSource.deleteBankRoll(bankRollId);
    }

    @Override
    @NonNull
    public Observable<Pick> getPicks() {
        return mBankRollLocalDataSource.getPicks();
    }

    @Override
    public Observable<Bet> getBets(@NonNull String bankRollId) {
        final BankRoll bankRoll = getBankRollFromCache(bankRollId);
        if (bankRoll != null) {
            return Observable.from(bankRoll.getBets());
        }

        return mBankRollLocalDataSource.getBankRoll(bankRollId)
                .flatMap(new Func1<BankRoll, Observable<Bet>>() {
                    @Override
                    public Observable<Bet> call(BankRoll bankRoll) {
                        return Observable.from(bankRoll.getBets());
                    }
                });
    }

    @Override
    public void removeBetFromBankRoll(@NonNull String bankRollId, @NonNull Bet bet) {
        final BankRoll bankRoll = getBankRollFromCache(bankRollId);
        if (bankRoll != null) {
            final List<Bet> bets = bankRoll.getBets();
            if (!ValidationUtil.isNullOrEmpty(bets) || bets.contains(bet)) {
                bets.remove(bet);
            }
        }

        mBankRollLocalDataSource.removeBetFromBankRoll(bankRollId, bet);
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

    private boolean isCacheHasBankkRoll(@NonNull String bankRollId) {
        return mBankrollCache != null && mBankrollCache.containsKey(bankRollId);
    }

    private BankRoll getBankRollFromCache(@NonNull String bankRollId) {
        if (!isCacheHasBankkRoll(bankRollId)) {
            return null;
        }

        return mBankrollCache.get(bankRollId);
    }

    private void removeBankRollFromCache(@NonNull String bankRollId) {
        if (ValidationUtil.isNullOrEmpty(bankRollId)) {
            return;
        }

        if (!isCacheHasBankkRoll(bankRollId)) {
            return;
        }

        mBankrollCache.remove(bankRollId);
    }

    @Override
    public void closeBankRoll(@NonNull String bankRollId) {
        final BankRoll bankRoll = getBankRollFromCache(bankRollId);
        if (bankRoll == null) {
            return;
        }

        bankRoll.setStatus(Constants.BANKROLL_STATUS_CLOSED);
        mBankRollLocalDataSource.closeBankRoll(bankRollId);
    }
}
