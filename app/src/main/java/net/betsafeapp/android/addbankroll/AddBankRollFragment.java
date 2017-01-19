package net.betsafeapp.android.addbankroll;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import net.betsafeapp.android.BaseFragment;
import net.betsafeapp.android.Constants;
import net.betsafeapp.android.R;
import net.betsafeapp.android.utils.AlertUtil;

/**
 * Created by tyln on 19/01/2017.
 */

public final class AddBankRollFragment extends BaseFragment implements AddBankRollContract.View {
    @NonNull
    private AddBankRollContract.Presenter mPresenter;

    @NonNull
    public static AddBankRollFragment newInstance() {
        return new AddBankRollFragment();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_add_bankroll;
    }

    @Override
    protected int getMenuRes() {
        return Constants.NO_RES;
    }

    @Override
    public void setPresenter(@NonNull AddBankRollContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void alert(@Nullable String message) {
        AlertUtil.alert(getApplicationContext(), message);
    }
}
