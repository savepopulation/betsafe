package net.betsafeapp.android.editbankroll;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import net.betsafeapp.android.BaseActivity;
import net.betsafeapp.android.BetSafeApp;
import net.betsafeapp.android.Constants;
import net.betsafeapp.android.R;

import javax.inject.Inject;

/**
 * Created by tyln on 22/03/2017.
 */

public final class EditBankRollActivity extends BaseActivity {
    private static final String BUNDLE_BANKROLL_ID = "bankroll_id";

    @NonNull
    @Inject
    EditBankRollPresenter mPresenter;

    @NonNull
    @Inject
    String mBankRollId;

    @NonNull
    public static Intent newIntent(@NonNull Context context, @NonNull String bankRollId) {
        final Intent intent = new Intent(context, EditBankRollActivity.class);
        intent.putExtra(BUNDLE_BANKROLL_ID, bankRollId);
        return intent;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_edit_bankroll;
    }

    @Override
    protected int getTitleRes() {
        return R.string.screen_title_edit_bankroll;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String bankRollId = null;
        final Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            bankRollId = bundle.getString(BUNDLE_BANKROLL_ID, null);
        }

        EditBankRollFragment editBankRollFragment = (EditBankRollFragment) getSupportFragmentManager()
                .findFragmentById(R.id.framelayout_main);

        if (editBankRollFragment == null) {
            editBankRollFragment = EditBankRollFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.framelayout_main, editBankRollFragment)
                    .commit();
        }

        DaggerEditBankRollComponent.builder()
                .bankRollRepositoryComponent(((BetSafeApp) getApplication()).getBankRollRepositoryComponent())
                .editBankRollModule(new EditBankRollModule(editBankRollFragment, bankRollId))
                .build()
                .inject(this);
    }
}
