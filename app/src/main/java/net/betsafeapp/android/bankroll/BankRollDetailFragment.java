package net.betsafeapp.android.bankroll;

import android.support.annotation.NonNull;

import net.betsafeapp.android.BaseFragment;
import net.betsafeapp.android.Constants;
import net.betsafeapp.android.R;

/**
 * Created by tyln on 12/03/2017.
 */

public final class BankRollDetailFragment extends BaseFragment {
    @NonNull
    public static BankRollDetailFragment newInstance() {
        return new BankRollDetailFragment();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_bankroll_detail;
    }

    @Override
    protected int getMenuRes() {
        return Constants.NO_RES;
    }
}
