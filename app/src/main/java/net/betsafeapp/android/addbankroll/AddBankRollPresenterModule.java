package net.betsafeapp.android.addbankroll;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tyln on 19/01/2017.
 */

@Module
final class AddBankRollPresenterModule {
    @NonNull
    private AddBankRollContract.View mView;

    AddBankRollPresenterModule(@NonNull AddBankRollContract.View view) {
        this.mView = view;
    }

    @NonNull
    @Provides
    AddBankRollContract.View provideAddBankRollView() {
        return this.mView;
    }
}
