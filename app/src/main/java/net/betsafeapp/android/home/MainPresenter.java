package net.betsafeapp.android.home;

import android.support.annotation.NonNull;

import net.betsafeapp.android.data.BankRoll;
import net.betsafeapp.android.data.source.BankRollRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by tyln on 17/01/2017.
 */

final class MainPresenter implements MainContract.Presenter {
    @NonNull
    private MainContract.View mView;

    @NonNull
    private final BankRollRepository mBankRollRepository;

    @NonNull
    private final CompositeSubscription mCompositeSubscription;

    @NonNull
    private final List<BankRoll> mBankRolls;

    @Inject
    MainPresenter(@NonNull MainContract.View view, @NonNull BankRollRepository safeRepository) {
        this.mView = view;
        this.mBankRollRepository = safeRepository;
        this.mCompositeSubscription = new CompositeSubscription();
        this.mBankRolls = new ArrayList<>();

        mView.setPresenter(this);
    }

    @Override
    public void start() {
        mView.initBankRollsAdater(mBankRolls);
    }

    @Override
    public void subscribe() {
        getBankRolls();
    }

    @Override
    public void unsubscribe() {
        if (mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.clear();
        }
    }

    @Override
    public void destroy() {
        this.mView = null;
    }

    @Override
    public void addBankRoll() {
        mView.navigateToAddBankRoll();
    }

    @Override
    public void addBet() {
        mView.navigateToAddBet();
    }

    private void getBankRolls() {
        mCompositeSubscription.clear();
        mBankRolls.clear();
        final Subscription subscription = mBankRollRepository.getBankRolls()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BankRoll>() {
                    @Override
                    public void onCompleted() {
                        mView.notifyBankRollDataChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.alert(e.getMessage());
                    }

                    @Override
                    public void onNext(BankRoll item) {
                        mBankRolls.add(item);
                    }
                });
        mCompositeSubscription.add(subscription);
    }
}
