package net.betsafeapp.android.addbet;

import android.support.annotation.NonNull;

import net.betsafeapp.android.BasePresenter;
import net.betsafeapp.android.data.source.BankRollRepository;

import javax.inject.Inject;

/**
 * Created by tyln on 26/01/2017.
 */

class AddBetPresenter implements AddBetContract.Presenter {
    @NonNull
    private AddBetContract.View mView;

    @NonNull
    private final BankRollRepository mBankRollRepository;

    @Inject
    AddBetPresenter(@NonNull AddBetContract.View view, @NonNull BankRollRepository bankRollRepository) {
        this.mView = view;
        this.mBankRollRepository = bankRollRepository;

        mView.setPresenter(this);
    }

    @Override
    public void start() {
        // Empty
    }

    @Override
    public void subscribe() {
        // Empty
    }

    @Override
    public void unsubscribe() {
        // Empty
    }

    @Override
    public void destroy() {
        this.mView = null;
    }
}
