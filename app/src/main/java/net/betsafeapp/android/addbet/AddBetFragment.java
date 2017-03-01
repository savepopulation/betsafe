package net.betsafeapp.android.addbet;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Spinner;

import net.betsafeapp.android.BaseFragment;
import net.betsafeapp.android.Constants;
import net.betsafeapp.android.R;
import net.betsafeapp.android.data.BankRoll;
import net.betsafeapp.android.util.AlertUtil;

import java.util.List;

/**
 * Created by tyln on 26/01/2017.
 */

public final class AddBetFragment extends BaseFragment implements AddBetContract.View {
    @NonNull
    private AddBetContract.Presenter mPresenter;

    // Views
    private Spinner mBankRollsSpinner;

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
        return R.menu.menu_add_bet;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBankRollsSpinner = (Spinner) view.findViewById(R.id.spinner_bankrolls);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        mPresenter.unsubscribe();
        super.onPause();
    }

    @Override
    public void setPresenter(@NonNull AddBetContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void alert(@Nullable String message) {
        AlertUtil.alert(getApplicationContext(), message);
    }

    @Override
    public void showBankrolls(@NonNull List<BankRoll> bankrolls) {
        final BankRollsSpinnerAdapter bankRollsSpinnerAdapter = new BankRollsSpinnerAdapter(getActivity(),
                android.R.layout.simple_spinner_item,
                bankrolls);
        bankRollsSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mBankRollsSpinner.setAdapter(bankRollsSpinnerAdapter);
    }
}
