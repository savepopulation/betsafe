package net.betsafeapp.android.bankroll;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import net.betsafeapp.android.BaseActivity;
import net.betsafeapp.android.R;
import net.betsafeapp.android.RxFragment;
import net.betsafeapp.android.addbet.AddBetActivity;
import net.betsafeapp.android.view.ConfirmDialogFragment;

/**
 * Created by tyln on 02/03/2017.
 */

public final class BankRollFragment extends RxFragment<BankRollContract.Presenter> implements
        BankRollContract.View,
        ConfirmDialogFragment.ConfirmListener {

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

        final Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar_main);
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        ((BaseActivity) getActivity()).setSupportActionBar(toolbar);

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

            case R.id.action_delete:
                mPresenter.deleteBankRollRequested();
                break;

            case R.id.action_close:
                mPresenter.closeBankRollRequested();
                break;
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

    @Override
    public void bankRollDeleted(@NonNull String bankRollName) {
        alert(getString(R.string.success_message_bankroll_deleted, bankRollName));
        getActivity().finish();
    }

    @Override
    public void showBankRollDeleteConfirmDialog() {
        final ConfirmDialogFragment deleteBankRollDialogFragment = ConfirmDialogFragment.newInstance(null,
                getString(R.string.dialog_message_delete_bankroll),
                getString(R.string.button_continue),
                getString(R.string.button_cancel),
                true);
        deleteBankRollDialogFragment.show(getChildFragmentManager(), ConfirmDialogFragment.TAG_DIALOG_BAKNROLL_DELETE);
    }

    @Override
    public void showBankRollCloseConfirmDialog() {
        final ConfirmDialogFragment deleteBankRollDialogFragment = ConfirmDialogFragment.newInstance(null,
                getString(R.string.dialog_message_close_bankroll),
                getString(R.string.button_continue),
                getString(R.string.button_cancel),
                true);
        deleteBankRollDialogFragment.show(getChildFragmentManager(), ConfirmDialogFragment.TAG_DIALOG_BANKROLL_CLOSE);
    }

    @Override
    public void bankRollClosed(@NonNull String bankRollName) {
        alert(getString(R.string.success_message_bakroll_closed, bankRollName));
    }

    @Override
    public void onConfirm(@NonNull String tag) {
        switch (tag) {
            case ConfirmDialogFragment.TAG_DIALOG_BAKNROLL_DELETE:
                mPresenter.deleteBankRoll();
                break;

            case ConfirmDialogFragment.TAG_DIALOG_BANKROLL_CLOSE:
                mPresenter.closeBankRoll();
                break;
        }
    }

    @Override
    public void onCancel(@NonNull String tag) {
        // Empty method
    }
}
