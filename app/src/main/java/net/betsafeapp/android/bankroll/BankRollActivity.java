package net.betsafeapp.android.bankroll;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import net.betsafeapp.android.BaseActivity;
import net.betsafeapp.android.BetSafeApp;
import net.betsafeapp.android.Constants;
import net.betsafeapp.android.R;

import javax.inject.Inject;

/**
 * Created by tyln on 02/03/2017.
 */

public final class BankRollActivity extends BaseActivity {
    private static final String BUNDLE_BANKROLL_ID = "bankroll_id";

    @NonNull
    @Inject
    BankRollPresenter mBankRollPresenter;

    @NonNull
    @Inject
    String mBankRollId;

    @NonNull
    public static Intent newIntent(@NonNull Context context, @NonNull String bankrollId) {
        final Intent intent = new Intent(context, BankRollActivity.class);
        intent.putExtra(BUNDLE_BANKROLL_ID, bankrollId);
        return intent;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_bankroll;
    }

    @Override
    protected int getMenuRes() {
        return Constants.NO_RES;
    }

    @Override
    protected int getScreenName() {
        return Constants.NO_RES;
    }

    @Override
    protected int getTitleRes() {
        return Constants.NO_RES;
    }

    @Override
    protected int getNavigationType() {
        return NAVIGATION_BACK;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String bankrollId = "";
        final Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            bankrollId = bundle.getString(BUNDLE_BANKROLL_ID, "");
        }

        BankRollFragment bankRollFragment = (BankRollFragment) getSupportFragmentManager()
                .findFragmentById(R.id.framelayout_main);

        if (bankRollFragment == null) {
            bankRollFragment = BankRollFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.framelayout_main, bankRollFragment)
                    .commit();
        }

        DaggerBankRollComponent.builder()
                .bankRollPresenterModule(new BankRollPresenterModule(bankRollFragment, bankrollId))
                .bankRollRepositoryComponent(((BetSafeApp) getApplication()).getBankRollRepositoryComponent())
                .build()
                .inject(this);
    }
}
