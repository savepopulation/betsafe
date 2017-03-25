package net.betsafeapp.android.bankroll.detail;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tyln on 02/03/2017.
 */

@Module
public final class BankRollDetailPresenterModule {
    @NonNull
    private final BankRollDetailContract.View mView;

    @NonNull
    private final String mBankRollId;

    public BankRollDetailPresenterModule(@NonNull BankRollDetailContract.View view, @NonNull String bankRollId) {
        this.mView = view;
        this.mBankRollId = bankRollId;
    }

    @NonNull
    @Provides
    BankRollDetailContract.View provideBankRollView() {
        return this.mView;
    }

    @NonNull
    @Provides
    String provideBankRollId() {
        return this.mBankRollId;
    }
}
