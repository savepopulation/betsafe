package net.betsafeapp.android.bankroll.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.TextViewCompat;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

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
    private TextView mTextViewCurrentAmount;

    @NonNull
    private TextView mTextViewInitialAmount;

    @NonNull
    private TextView mTextViewStatus;

    @NonNull
    private TextView mTextViewBetCount;

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
        return R.menu.menu_bankroll;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTextViewCurrentAmount = (TextView) view.findViewById(R.id.textview_bankroll_current_amount);
        mTextViewInitialAmount = (TextView) view.findViewById(R.id.textview_bankroll_init_amount);
        mTextViewStatus = (TextView) view.findViewById(R.id.textview_bankroll_status);
        mTextViewBetCount = (TextView) view.findViewById(R.id.textview_total_bets_count);
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
        alert(getString(R.string.success_message_bankroll_closed, bankRollName));
    }

    @Override
    public void showEditBankRoll(@NonNull String bankRollId) {
        startActivity(EditBankRollActivity.newIntent(getActivity(), bankRollId));
    }

    @Override
    public void showBankRollCurrentAmount(double currentAmount) {
        mTextViewCurrentAmount.setText(String.valueOf(currentAmount));
    }

    @Override
    public void showBankRollInitialAmount(double initialAmount) {
        mTextViewInitialAmount.setText(String.valueOf(initialAmount));
    }

    @Override
    public void showBankRollStatus(int status) {
        mTextViewStatus.setText("status" + status);
    }

    @Override
    public void showBankRollBetCount(int count) {
        mTextViewBetCount.setText(String.valueOf(count));
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
