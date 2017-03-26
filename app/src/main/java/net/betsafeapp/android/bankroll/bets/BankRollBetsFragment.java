package net.betsafeapp.android.bankroll.bets;

import android.support.annotation.NonNull;
import android.view.MenuItem;

import net.betsafeapp.android.Constants;
import net.betsafeapp.android.R;
import net.betsafeapp.android.RxFragment;
import net.betsafeapp.android.addbet.AddBetActivity;

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
        return R.menu.menu_bets;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_bet:
                mPresenter.addBet();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void navigateToAddBet(@NonNull String defaultBankRollId) {
        startActivity(AddBetActivity.newIntent(getActivity(), defaultBankRollId));
    }
}
