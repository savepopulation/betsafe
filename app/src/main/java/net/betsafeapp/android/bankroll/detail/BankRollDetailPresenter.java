package net.betsafeapp.android.bankroll.detail;

import android.support.annotation.NonNull;
import android.util.Log;

import net.betsafeapp.android.Constants;
import net.betsafeapp.android.RxPresenter;
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

public final class BankRollDetailPresenter extends RxPresenter<BankRollDetailContract.View>
        implements BankRollDetailContract.Presenter {

    @NonNull
    private BankRollRepository mBankRollRepository;

    @NonNull
    private String mBankRollId;

    @NonNull
    private BankRoll mBankRoll;

    @Inject
    BankRollDetailPresenter(@NonNull BankRollDetailContract.View view,
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

    @Override
    public void closeBankRollRequested() {
        if (mBankRoll.getStatus() == Constants.BANKROLL_STATUS_CLOSED ||
                mBankRoll.getStatus() == Constants.BANKROLL_STATUS_COMPLETED) {
            return;
        }
        mView.showBankRollCloseConfirmDialog();
    }

    @Override
    public void closeBankRoll() {
        mBankRollRepository.closeBankRoll(mBankRollId);
        mView.bankRollClosed(mBankRoll.getName());
    }

    @Override
    public void editBankRoll() {
        mView.showEditBankRoll(mBankRollId);
    }

    private void getBankRoll() {
        final Subscription bankRollSubscription = mBankRollRepository.getBankRoll(mBankRollId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BankRoll>() {
                    @Override
                    public void onCompleted() {
                        showBankRollName();
                        mView.showBankRollCurrentAmount(mBankRoll.getCurrentCapital());
                        mView.showBankRollInitialAmount(mBankRoll.getInitialCapital());
                        mView.showBankRollStatus(mBankRoll.getStatus());
                        mView.showBankRollBetCount(mBankRoll.getBets().size());
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
