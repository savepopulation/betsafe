package net.betsafeapp.android.bankrolls;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tyln on 19/01/2017.
 */

@Module
final class BankRollsPresenterModule {
    @NonNull
    private BankRollsContract.View mView;

    BankRollsPresenterModule(@NonNull BankRollsContract.View view) {
        this.mView = view;
    }

    @NonNull
    @Provides
    BankRollsContract.View provideBankRollsView() {
        return mView;
    }
}
