package net.betsafeapp.android.data.source.local;

import android.content.Context;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

import net.betsafeapp.android.Constants;
import net.betsafeapp.android.R;
import net.betsafeapp.android.data.BankRoll;
import net.betsafeapp.android.data.Bet;
import net.betsafeapp.android.data.Pick;
import net.betsafeapp.android.data.source.BankRollDataSource;
import net.betsafeapp.android.util.ValidationUtil;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by tyln on 17/01/2017.
 */

@Singleton
public class BankRollLocalDataSource implements BankRollDataSource {
    @NonNull
    private final Context mContext;

    @NonNull
    private final RealmConfiguration mRealmConfiguration;

    public BankRollLocalDataSource(@NonNull Context context) {
        this.mContext = context;
        this.mRealmConfiguration = new RealmConfiguration.Builder(mContext).build();
    }

    @Override
    public Observable<BankRoll> getBankRolls() {
        return Observable.create(new Observable.OnSubscribe<BankRoll>() {
            @Override
            public void call(Subscriber<? super BankRoll> subscriber) {
                final List<BankRoll> bankRolls = getAllBankRolls();
                if (!ValidationUtil.isNullOrEmpty(bankRolls)) {
                    for (final BankRoll bankRoll : bankRolls) {
                        subscriber.onNext(bankRoll);
                    }
                }
                subscriber.onCompleted();
            }
        });
    }

    @Override
    public void createNewBankroll(@NonNull BankRoll bankRoll) {
        saveBankroll(bankRoll);
    }

    @Override
    public Observable<BankRoll> searchBankroll(@Nullable String query) {
        // TODO implement db search
        return Observable.empty();
    }

    @Override
    public Observable<BankRoll> getBankRoll(@NonNull String bankRollId) {
        return Realm.getInstance(mRealmConfiguration)
                .where(BankRoll.class)
                .equalTo("id", bankRollId).findFirst()
                .asObservable();
    }

    @Override
    public void deleteBankRoll(@NonNull final String bankRollId) {
        Realm.getInstance(mRealmConfiguration)
                .executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        final RealmResults<BankRoll> bankRolls = realm.where(BankRoll.class)
                                .equalTo("id", bankRollId)
                                .findAll();
                        bankRolls.deleteAllFromRealm();
                    }
                });
    }

    @Override
    public void closeBankRoll(@NonNull final String bankRollId) {
        Realm.getInstance(mRealmConfiguration)
                .executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        final BankRoll bankRoll = realm.where(BankRoll.class)
                                .equalTo("id", bankRollId)
                                .findFirst();
                        bankRoll.setStatus(Constants.BANKROLL_STATUS_CLOSED);
                        realm.copyToRealmOrUpdate(bankRoll);
                    }
                });
    }

    @Override
    public Observable<Pick> getPicks() {
        return Observable.create(new Observable.OnSubscribe<Pick>() {
            @Override
            public void call(Subscriber<? super Pick> subscriber) {
                final String[] picksArray = mContext.getResources().getStringArray(R.array.picks);
                for (int i = 0; i < picksArray.length; i++) {
                    subscriber.onNext(new Pick(i, picksArray[i]));
                }
                subscriber.onCompleted();
            }
        });
    }

    @Override
    public Observable<Bet> getBets(@NonNull String bankRollId) {
        // TODO implement this
        return Observable.empty();
    }

    @Override
    public void removeBetFromBankRoll(@NonNull final String bankRollId, @NonNull final Bet bet) {
        Realm.getInstance(mRealmConfiguration)
                .executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        final RealmResults<Bet> bets = realm.where(Bet.class)
                                .equalTo("id", bet.getId())
                                .findAll();
                        bets.deleteAllFromRealm();
                    }
                });
    }

    @WorkerThread
    public void saveBankroll(@NonNull final BankRoll bankRoll) {
        Realm.getInstance(mRealmConfiguration)
                .executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        final BankRoll realmBankRoll = new BankRoll();
                        fillBankRoll(realmBankRoll, bankRoll);
                        realm.copyToRealmOrUpdate(realmBankRoll);
                    }
                });
    }

    @WorkerThread
    private List<BankRoll> getAllBankRolls() {
        final Realm realm = Realm.getInstance(mRealmConfiguration);
        realm.beginTransaction();

        final RealmResults<BankRoll> realmResults = realm.where(BankRoll.class)
                .findAllSorted("createDate", Sort.DESCENDING);

        final List<BankRoll> bankRolls = realm.copyFromRealm(realmResults);
        realm.commitTransaction();
        realm.close();

        return bankRolls;
    }

    private void fillBankRoll(@NonNull BankRoll realmBankRoll, @NonNull BankRoll bankRoll) {
        realmBankRoll.setId(bankRoll.getId());
        realmBankRoll.setName(bankRoll.getName());
        realmBankRoll.setType(bankRoll.getType());
        realmBankRoll.setInitialCapital(bankRoll.getInitialCapital());
        realmBankRoll.setCurrentCapital(bankRoll.getCurrentCapital());
        realmBankRoll.setStatus(bankRoll.getStatus());
        realmBankRoll.setPrivacy(bankRoll.getPrivacy());
        realmBankRoll.setCreateDate(bankRoll.getCreateDate());
        realmBankRoll.setUpdateDate(System.currentTimeMillis());
        realmBankRoll.setBets(bankRoll.getBets());
    }
}
