package net.betsafeapp.android.home;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import net.betsafeapp.android.RxPresenter;
import net.betsafeapp.android.data.BankRoll;
import net.betsafeapp.android.data.source.BankRollRepository;
import net.betsafeapp.android.util.ValidationUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by tyln on 17/01/2017.
 */

final class MainPresenter extends RxPresenter<MainContract.View> implements
        MainContract.Presenter {

    @NonNull
    private final BankRollRepository mBankRollRepository;

    @NonNull
    private final List<BankRoll> mBankRolls;

    @Nullable
    private String mQuery;

    @Inject
    MainPresenter(@NonNull MainContract.View view,
                  @NonNull BankRollRepository safeRepository) {
        super(view);
        mView = view;
        this.mBankRollRepository = safeRepository;
        this.mBankRolls = new ArrayList<>();

        mView.setPresenter(this);
    }

    @Override
    public void start() {
        mView.initBankRollsAdapter(mBankRolls);
    }

    @Override
    public void subscribe() {
        if (ValidationUtil.isNullOrEmpty(mQuery)) {
            getBankRolls();
        } else {
            searchBankRollsByName();
        }
    }

    @CallSuper
    @Override
    public void unsubscribe() {
        super.unsubscribe();
        mView.collapseFloatingActionsMenu();
    }

    @Override
    public void addBankRoll() {
        mView.navigateToAddBankRoll();
    }

    @Override
    public void addBet() {
        if (ValidationUtil.isNullOrEmpty(mBankRolls)) {
            mView.collapseFloatingActionsMenu();
            mView.emptyBankroll();
        } else {
            mView.navigateToAddBet();
        }
    }

    @Override
    public void showBankRoll(@NonNull String bankRollId) {
        if (ValidationUtil.isNullOrEmpty(bankRollId)) {
            return;
        }
        mView.navigateToBankRollDetail(bankRollId);
    }

    @Override
    public void showSettings() {
        mView.navigateToSettings();
    }

    @Override
    public void search(@Nullable String query) {
        this.mQuery = query;
        searchBankRollsByName();
    }

    private void getBankRolls() {
        clearSubscriptions();
        mBankRolls.clear();
        final Subscription bankrollSubscription = mBankRollRepository.getBankRolls()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getBankRollObserver());
        addSubscription(bankrollSubscription);
    }

    private void searchBankRollsByName() {
        clearSubscriptions();
        mBankRolls.clear();
        final Subscription searchSubscription = mBankRollRepository.searchBankroll(mQuery)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getBankRollObserver());
        addSubscription(searchSubscription);
    }

    @NonNull
    private Observer<BankRoll> getBankRollObserver() {
        return new Observer<BankRoll>() {
            @Override
            public void onCompleted() {
                mView.notifyBankRollDataChanged();
            }

            @Override
            public void onError(Throwable e) {
                mView.alert(e.getMessage());
            }

            @Override
            public void onNext(BankRoll bankRoll) {
                mBankRolls.add(bankRoll);
            }
        };
    }
}
