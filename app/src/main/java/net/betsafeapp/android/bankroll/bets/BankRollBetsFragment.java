package net.betsafeapp.android.bankroll.bets;

import android.support.annotation.NonNull;

import net.betsafeapp.android.Constants;
import net.betsafeapp.android.R;
import net.betsafeapp.android.RxFragment;

/**
 * Created by tyln on 12/03/2017.
 */

public final class BankRollBetsFragment extends RxFragment<BankRollBetsContract.Presenter>
        implements BankRollBetsContract.View {

    @NonNull
    public static BankRollBetsFragment newInstance() {
        return new BankRollBetsFragment();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_bankroll_history;
    }

    @Override
    protected int getMenuRes() {
        return Constants.NO_RES;
    }
}
