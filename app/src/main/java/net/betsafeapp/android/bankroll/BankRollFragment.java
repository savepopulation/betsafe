package net.betsafeapp.android.bankroll;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;

import net.betsafeapp.android.BaseFragment;
import net.betsafeapp.android.Constants;
import net.betsafeapp.android.R;
import net.betsafeapp.android.addbankroll.AddBankRollActivity;
import net.betsafeapp.android.addbet.AddBetActivity;
import net.betsafeapp.android.util.AlertUtil;

/**
 * Created by tyln on 02/03/2017.
 */

public final class BankRollFragment extends BaseFragment implements BankRollContract.View {
    @NonNull
    private BankRollContract.Presenter mPresenter;

    @NonNull
    public static BankRollFragment newInstance() {
        return new BankRollFragment();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_bankroll;
    }

    @Override
    protected int getMenuRes() {
        return R.menu.menu_bankroll;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.start();
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
    public void setPresenter(@NonNull BankRollContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void alert(@Nullable String message) {
        AlertUtil.alert(getApplicationContext(), message);
    }

    @Override
    public void navigateToAddBet(@NonNull String defaultBankRollId) {
        startActivity(AddBetActivity.newIntent(getActivity(), defaultBankRollId));
    }
}
