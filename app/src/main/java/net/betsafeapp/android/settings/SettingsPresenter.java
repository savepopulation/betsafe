package net.betsafeapp.android.settings;

import android.support.annotation.NonNull;

import javax.inject.Inject;


/**
 * Created by tyln on 09/03/2017.
 */

final class SettingsPresenter implements SettingsContract.Presenter {
    @NonNull
    private SettingsContract.View mView;

    @Inject
    SettingsPresenter(@NonNull SettingsContract.View view) {
        this.mView = view;

        mView.setPresenter(this);
    }

    @Override
    public void start() {
        // Empty method
    }

    @Override
    public void subscribe() {
        // Empty method
    }

    @Override
    public void unsubscribe() {
        // Empty method
    }

    @Override
    public void destroy() {
        this.mView = null;
    }
}
