package net.betsafeapp.android.home;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tyln on 19/01/2017.
 */

@Module
final class MainPresenterModule {
    @NonNull
    private MainContract.View mView;

    MainPresenterModule(@NonNull MainContract.View view) {
        this.mView = view;
    }

    @Provides
    MainContract.View provideMainView() {
        return mView;
    }
}
