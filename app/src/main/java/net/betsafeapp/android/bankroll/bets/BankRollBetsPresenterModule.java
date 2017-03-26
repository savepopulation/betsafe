package net.betsafeapp.android.bankroll.bets;

import android.support.annotation.NonNull;


import dagger.Module;
import dagger.Provides;

/**
 * Created by tyln on 25/03/2017.
 */

@Module
public final class BankRollBetsPresenterModule {
    @NonNull
    private final BankRollBetsContract.View mView;

    public BankRollBetsPresenterModule(@NonNull BankRollBetsContract.View view) {
        this.mView = view;
    }

    @NonNull
    @Provides
    BankRollBetsContract.View provideBankRollView() {
        return this.mView;
    }
}
