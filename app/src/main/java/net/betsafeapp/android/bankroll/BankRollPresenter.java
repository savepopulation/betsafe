package net.betsafeapp.android.bankroll;

import android.support.annotation.NonNull;

import net.betsafeapp.android.RxPresenter;
import net.betsafeapp.android.addbet.AddBetActivity;
import net.betsafeapp.android.data.BankRoll;
import net.betsafeapp.android.data.source.BankRollRepository;
import net.betsafeapp.android.util.ValidationUtil;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tyln on 02/03/2017.
 */

final class BankRollPresenter extends RxPresenter<BankRollContract.View> implements BankRollContract.Presenter {

    @NonNull
    private BankRollRepository mBankRollRepository;

    @NonNull
    private String mBankRollId;

    @NonNull
    private BankRoll mBankRoll;

    @Inject
    BankRollPresenter(@NonNull BankRollContract.View view,
                      @NonNull BankRollRepository bankRollRepository,
                      @NonNull String bankRollId) {
        super(view);
        this.mView = view;
        this.mBankRollRepository = bankRollRepository;
        this.mBankRollId = bankRollId;

        mView.setPresenter(this);
    }

    @Override
    public void start() {
        // Empty method
    }

    @Override
    public void subscribe() {
        getBankRoll();
    }

    @Override
    public void addBet() {
        if (ValidationUtil.isNullOrEmpty(mBankRollId)) {
            return;
        }

        mView.navigateToAddBet(mBankRollId);
    }

    @Override
    public void deleteBankRollRequested() {
        if (!ValidationUtil.isNullOrEmpty(mBankRollId)) {
            mView.showBankRollDeleteConfirmDialog();
        }
    }

    @Override
    public void deleteBankRoll() {
        final String bankRollName = mBankRoll.getName();
        mBankRollRepository.deleteBankRoll(mBankRollId);
        mView.bankRollDeleted(bankRollName);
    }

    private void getBankRoll() {
        final Subscription bankRollSubscription = mBankRollRepository.getBankRoll(mBankRollId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BankRoll>() {
                    @Override
                    public void onCompleted() {
                        showBankRollName();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.alert(e.getMessage());
                    }

                    @Override
                    public void onNext(BankRoll bankRoll) {
                        mBankRoll = bankRoll;
                    }
                });
        addSubscription(bankRollSubscription);
    }

    private void showBankRollName() {
        if (!ValidationUtil.isNullOrEmpty(mBankRoll.getName())) {
            mView.initToolbar(mBankRoll.getName());
        }
    }
}
