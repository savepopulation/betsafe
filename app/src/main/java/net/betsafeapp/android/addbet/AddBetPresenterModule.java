package net.betsafeapp.android.addbet;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tyln on 26/01/2017.
 */

@Module
class AddBetPresenterModule {
    @NonNull
    private final AddBetContract.View mView;

    AddBetPresenterModule(@NonNull AddBetContract.View view) {
        this.mView = view;
    }

    @NonNull
    @Provides
    AddBetContract.View provideAddBetModule() {
        return this.mView;
    }
}
