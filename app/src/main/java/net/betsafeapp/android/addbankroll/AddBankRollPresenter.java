package net.betsafeapp.android.addbankroll;

import android.support.annotation.NonNull;

import net.betsafeapp.android.data.source.BankRollRepository;

import javax.inject.Inject;

/**
 * Created by tyln on 19/01/2017.
 */

final class AddBankRollPresenter implements AddBankRollContract.Presenter {
    @NonNull
    private AddBankRollContract.View mView;

    @NonNull
    private BankRollRepository mBankRollRepository;

    @Inject
    AddBankRollPresenter(@NonNull AddBankRollContract.View view, @NonNull BankRollRepository bankRollRepository) {
        this.mView = view;
        this.mBankRollRepository = bankRollRepository;

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