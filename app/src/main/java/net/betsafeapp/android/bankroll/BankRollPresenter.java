package net.betsafeapp.android.bankroll;

import android.support.annotation.NonNull;

import net.betsafeapp.android.RxPresenter;
import net.betsafeapp.android.data.BankRoll;
import net.betsafeapp.android.data.source.BankRollRepository;

import javax.inject.Inject;

/**
 * Created by tyln on 02/03/2017.
 */

final class BankRollPresenter extends RxPresenter implements BankRollContract.Presenter {
    @NonNull
    private BankRollContract.View mView;

    @NonNull
    private BankRollRepository mBankRollRepository;

    @NonNull
    private String mBankRollId;

    @NonNull
    private BankRoll mBankRoll;

    @Inject
    BankRollPresenter(@NonNull BankRollContract.View view, @NonNull BankRollRepository bankRollRepository, @NonNull String bankRollId) {
        super();
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
        // Empty method
    }

    @Override
    public void unsubscribe() {
        // Empty method
    }

    @Override
    public void destroy() {
        this.mView = null;
    }
}
