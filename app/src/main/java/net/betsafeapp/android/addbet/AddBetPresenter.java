package net.betsafeapp.android.addbet;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import net.betsafeapp.android.BasePresenter;
import net.betsafeapp.android.data.source.BankRollRepository;

import javax.inject.Inject;

/**
 * Created by tyln on 26/01/2017.
 */

final class AddBetPresenter implements AddBetContract.Presenter {
    @NonNull
    private AddBetContract.View mView;

    @NonNull
    private final BankRollRepository mBankRollRepository;

    @Nullable
    private final String mBankrollId;

    @Inject
    AddBetPresenter(@NonNull AddBetContract.View view,
                    @NonNull BankRollRepository bankRollRepository,
                    @Nullable String bankrollId) {
        this.mView = view;
        this.mBankRollRepository = bankRollRepository;
        this.mBankrollId = bankrollId;

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
