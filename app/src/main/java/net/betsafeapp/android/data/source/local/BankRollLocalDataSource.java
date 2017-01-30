package net.betsafeapp.android.data.source.local;

import android.content.Context;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

import net.betsafeapp.android.data.BankRoll;
import net.betsafeapp.android.data.source.BankRollDataSource;

import java.util.List;

import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmConfiguration;
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
        return Observable.from(getAllBankRolls());
    }

    @Override
    public void createNewBankroll(@NonNull BankRoll bankRoll) {
        saveBankroll(bankRoll);
    }

    @WorkerThread
    private void saveBankroll(@NonNull final BankRoll bankRoll) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Realm realm = Realm.getInstance(mRealmConfiguration);
                realm.beginTransaction();

                final BankRoll realmBankRoll = realm.createObject(BankRoll.class);
                realmBankRoll.setId(bankRoll.getId());
                realmBankRoll.setName(bankRoll.getName());
                realmBankRoll.setInitialCapital(bankRoll.getInitialCapital());
                realmBankRoll.setCurrentCapital(bankRoll.getCurrentCapital());
                realmBankRoll.setStatus(bankRoll.getStatus());
                realmBankRoll.setPrivacy(bankRoll.getPrivacy());
                realmBankRoll.setCreateDate(bankRoll.getCreateDate());
                realmBankRoll.setUpdateDate(System.currentTimeMillis());

                realm.copyToRealmOrUpdate(realmBankRoll);
                realm.commitTransaction();
                realm.close();
            }
        }).start();
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
}
