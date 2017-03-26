package net.betsafeapp.android.bankroll.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;

import net.betsafeapp.android.BaseActivity;
import net.betsafeapp.android.R;
import net.betsafeapp.android.RxFragment;
import net.betsafeapp.android.addbet.AddBetActivity;
import net.betsafeapp.android.editbankroll.EditBankRollActivity;
import net.betsafeapp.android.view.ConfirmDialogFragment;

/**
 * Created by tyln on 02/03/2017.
 */

public final class BankRollDetailFragment extends RxFragment<BankRollDetailContract.Presenter> implements
        BankRollDetailContract.View,
        ConfirmDialogFragment.ConfirmListener {

    @NonNull
    public static BankRollDetailFragment newInstance() {
        return new BankRollDetailFragment();
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                mPresenter.deleteBankRollRequested();
                break;

            case R.id.action_close:
                mPresenter.closeBankRollRequested();
                break;

            case R.id.action_edit:
                mPresenter.editBankRoll();
                break;
        }
        return super.onOptionsItemSelected(item);
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
    public void showEditBankRoll(@NonNull String bankRollId) {
        startActivity(EditBankRollActivity.newIntent(getActivity(), bankRollId));
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
