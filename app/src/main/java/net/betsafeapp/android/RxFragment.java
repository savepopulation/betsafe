package net.betsafeapp.android;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import net.betsafeapp.android.util.AlertUtil;

/**
 * Created by tyln on 12/03/2017.
 */

public abstract class RxFragment<T extends BasePresenter> extends BaseFragment implements BaseView<T> {
    @NonNull
    protected T mPresenter;

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.start();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    public void onDestroy() {
        mPresenter.destroy();
        super.onDestroy();
    }

    @Override
    public void alert(@Nullable String message) {
        AlertUtil.alert(getApplicationContext(), message);
    }

    @Override
    public void setPresenter(@NonNull T presenter) {
        this.mPresenter = presenter;
    }
}
