package net.betsafeapp.android.bankroll;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import net.betsafeapp.android.BaseActivity;
import net.betsafeapp.android.BaseFragment;
import net.betsafeapp.android.R;
import net.betsafeapp.android.RxFragment;
import net.betsafeapp.android.addbet.AddBetActivity;
import net.betsafeapp.android.util.AlertUtil;

/**
 * Created by tyln on 02/03/2017.
 */

public final class BankRollFragment extends RxFragment<BankRollContract.Presenter>
        implements BankRollContract.View {

    //Views
    private Toolbar mToolbar;

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

        mToolbar = (Toolbar) view.findViewById(R.id.toolbar_main);
        mToolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        ((BaseActivity) getActivity()).setSupportActionBar(mToolbar);

        final ViewPager viewPagerBankRollTabs = (ViewPager) view.findViewById(R.id.viewpager_bankroll);
        final TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        final BankRollTabsAdapter bankRollTabsAdapter = new BankRollTabsAdapter(getChildFragmentManager(),
                getResources().getStringArray(R.array.tabs_bankroll));
        viewPagerBankRollTabs.setAdapter(bankRollTabsAdapter);
        tabLayout.setupWithViewPager(viewPagerBankRollTabs);
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

    @Override
    public void initToolbar(@NonNull String bankRollName) {
        ((BaseActivity) getActivity()).setActionbarTitle(bankRollName);
    }
}
