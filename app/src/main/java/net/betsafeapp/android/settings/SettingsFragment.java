package net.betsafeapp.android.settings;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import net.betsafeapp.android.BaseFragment;
import net.betsafeapp.android.Constants;
import net.betsafeapp.android.R;
import net.betsafeapp.android.util.AlertUtil;

/**
 * Created by tyln on 09/03/2017.
 */

public final class SettingsFragment extends BaseFragment implements SettingsContract.View {
    @NonNull
    private SettingsContract.Presenter mPresenter;

    @NonNull
    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_settings;
    }

    @Override
    protected int getMenuRes() {
        return Constants.NO_RES;
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.start();
    }

    @Override
    public void setPresenter(@NonNull SettingsContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void alert(@Nullable String message) {
        AlertUtil.alert(getApplicationContext(), message);
    }
}
