package net.betsafeapp.android.addbankroll;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import net.betsafeapp.android.BaseFragment;
import net.betsafeapp.android.Constants;
import net.betsafeapp.android.R;
import net.betsafeapp.android.util.AlertUtil;
import net.betsafeapp.android.util.Utils;

/**
 * Created by tyln on 19/01/2017.
 */

public final class AddBankRollFragment extends BaseFragment implements AddBankRollContract.View, View.OnClickListener {
    @NonNull
    private AddBankRollContract.Presenter mPresenter;

    private EditText mEditTextBankRollName;
    private EditText mEditTextBankRollInitialAmount;
    private Spinner mSpinnerBankRollPrivacy;
    private Button mButtonAddBankRoll;

    @NonNull
    public static AddBankRollFragment newInstance() {
        return new AddBankRollFragment();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_add_bankroll;
    }

    @Override
    protected int getMenuRes() {
        return Constants.NO_RES;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mEditTextBankRollName = (EditText) view.findViewById(R.id.edittext_bankroll_name);
        mEditTextBankRollName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mPresenter.checkBankRollName(editable.toString());
            }
        });

        mEditTextBankRollInitialAmount = (EditText) view.findViewById(R.id.edittext_bankroll_initial_amount);
        mEditTextBankRollInitialAmount.setText(Constants.DEFAULT_AMOUNT);
        mEditTextBankRollInitialAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mPresenter.checkBankRollInitialCapital(Utils.convertStringToDouble(editable.toString()));
            }
        });

        mSpinnerBankRollPrivacy = (Spinner) view.findViewById(R.id.spinner_bankroll_privacy);

        mButtonAddBankRoll = (Button) view.findViewById(R.id.button_add_bankroll);
        mButtonAddBankRoll.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.start();
    }

    @Override
    public void setPresenter(@NonNull AddBankRollContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void alert(@Nullable String message) {
        AlertUtil.alert(getApplicationContext(), message);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_add_bankroll:
                mPresenter.addBankroll(mEditTextBankRollName.getText().toString().trim(),
                        Double.valueOf(mEditTextBankRollInitialAmount.getText().toString().trim()),
                        mSpinnerBankRollPrivacy.getSelectedItemPosition());
                break;
        }
    }

    @Override
    public void enableOrDisableCreateBankRoll(boolean isEnabled) {
        mButtonAddBankRoll.setEnabled(isEnabled);
    }

    @Override
    public void errorOnCreatingBankRoll() {
        AlertUtil.alert(getApplicationContext(), getString(R.string.error_message_cannot_create_bankroll));
        mEditTextBankRollName.setText("");
        mEditTextBankRollInitialAmount.setText(Constants.DEFAULT_AMOUNT);
        mSpinnerBankRollPrivacy.setSelection(0);
    }

    @Override
    public void onBankRollCreated() {
        AlertUtil.alert(getApplicationContext(), getString(R.string.success_message_new_bankroll_created));
        getActivity().finish();
    }
}
