package net.betsafeapp.android.bankroll;

import android.support.annotation.NonNull;

import net.betsafeapp.android.data.BankRoll;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tyln on 02/03/2017.
 */

@Module
final class BankRollPresenterModule {
    @NonNull
    private final BankRollContract.View mView;

    @NonNull
    private final String mBankRollId;

    BankRollPresenterModule(@NonNull BankRollContract.View view, @NonNull String bankRollId) {
        this.mView = view;
        this.mBankRollId = bankRollId;
    }

    @NonNull
    @Provides
    BankRollContract.View provideBankRollView() {
        return this.mView;
    }

    @NonNull
    @Provides
    String provideBankRollId() {
        return this.mBankRollId;
    }
}
