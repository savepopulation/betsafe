package net.betsafeapp.android.settings;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tyln on 09/03/2017.
 */

@Module
final class SettingsPresenterModule {
    @NonNull
    private SettingsContract.View mView;

    SettingsPresenterModule(@NonNull SettingsContract.View view) {
        this.mView = view;
    }

    @NonNull
    @Provides
    SettingsContract.View provideSettingsView() {
        return this.mView;
    }
}
