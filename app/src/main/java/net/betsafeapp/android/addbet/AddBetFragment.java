package net.betsafeapp.android.addbet;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import net.betsafeapp.android.BaseFragment;
import net.betsafeapp.android.Constants;
import net.betsafeapp.android.R;
import net.betsafeapp.android.util.AlertUtil;

/**
 * Created by tyln on 26/01/2017.
 */

public final class AddBetFragment extends BaseFragment implements AddBetContract.View {
    @NonNull
    private AddBetContract.Presenter mPresenter;

    @NonNull
    public static AddBetFragment newInstance() {
        return new AddBetFragment();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_add_bet;
    }

    @Override
    protected int getMenuRes() {
        return Constants.NO_RES;
    }

    @Override
    public void setPresenter(@NonNull AddBetContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void alert(@Nullable String message) {
        AlertUtil.alert(getApplicationContext(), message);
    }
}
