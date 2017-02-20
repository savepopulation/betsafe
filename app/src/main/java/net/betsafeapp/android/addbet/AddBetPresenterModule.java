package net.betsafeapp.android.addbet;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tyln on 26/01/2017.
 */

@Module
final class AddBetPresenterModule {
    @NonNull
    private final AddBetContract.View mView;

    @Nullable
    private final String mBankrollId;

    AddBetPresenterModule(@NonNull AddBetContract.View view, @Nullable String bankrollId) {
        this.mView = view;
        this.mBankrollId = bankrollId;
    }

    @NonNull
    @Provides
    AddBetContract.View provideAddBetView() {
        return this.mView;
    }

    @Nullable
    @Provides
    String provideBankrollId() {
        return this.mBankrollId;
    }
}
