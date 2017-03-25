package net.betsafeapp.android.bankrolls;

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

public final class BankRollsActivity extends BaseActivity {
    @Inject
    BankRollsPresenter mBankRollsPresenter;

    @NonNull
    public static Intent newIntent(@NonNull Context context) {
        return new Intent(context, BankRollsActivity.class);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_bankrolls;
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
        return R.string.app_name;
    }

    @Override
    protected int getNavigationType() {
        return NAVIGATION_ROOT;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BankRollsFragment bankRollsFragment = (BankRollsFragment) getSupportFragmentManager()
                .findFragmentById(R.id.framelayout_main);

        if (bankRollsFragment == null) {
            bankRollsFragment = BankRollsFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.framelayout_main, bankRollsFragment)
                    .commit();
        }

        DaggerBankRollsComponent.builder()
                .bankRollRepositoryComponent(((BetSafeApp) getApplication()).getBankRollRepositoryComponent())
                .bankRollsPresenterModule(new BankRollsPresenterModule(bankRollsFragment))
                .build()
                .inject(this);
    }
}
