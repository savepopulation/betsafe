package net.betsafeapp.android.home;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import net.betsafeapp.android.BasePresenter;

/**
 * Created by tyln on 17/01/2017.
 */

public class MainPresenter implements BasePresenter {
    @Nullable
    private MainContract.View mView;

    public MainPresenter(@NonNull MainContract.View view) {
        this.mView = view;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void destroy() {
        this.mView = null;
    }
}
