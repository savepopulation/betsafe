package net.betsafeapp.android.bankroll.history;

import android.support.annotation.NonNull;


import dagger.Module;
import dagger.Provides;

/**
 * Created by tyln on 25/03/2017.
 */

@Module
public final class BankRollHistoryPresenterModule {
    @NonNull
    private final BankRollHistoryContract.View mView;

    public BankRollHistoryPresenterModule(@NonNull BankRollHistoryContract.View view) {
        this.mView = view;
    }

    @NonNull
    @Provides
    BankRollHistoryContract.View provideBankRollView() {
        return this.mView;
    }
}
