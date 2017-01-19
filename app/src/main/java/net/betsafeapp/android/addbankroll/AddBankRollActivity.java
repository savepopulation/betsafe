package net.betsafeapp.android.addbankroll;

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
 * Created by tyln on 19/01/2017.
 */

public final class AddBankRollActivity extends BaseActivity {
    @NonNull
    @Inject
    AddBankRollPresenter mAddBankRollPresenter;

    @NonNull
    public static Intent newIntent(@NonNull Context context) {
        return new Intent(context, AddBankRollActivity.class);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_add_bankroll;
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
        return R.string.screen_title_add_bankroll;
    }

    @Override
    protected int getNavigationType() {
        return NAVIGATION_BACK;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AddBankRollFragment addBankRollFragment = (AddBankRollFragment) getSupportFragmentManager()
                .findFragmentById(R.id.framelayout_main);

        if (addBankRollFragment == null) {
            addBankRollFragment = AddBankRollFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.framelayout_main, addBankRollFragment)
                    .commit();
        }

        DaggerAddBankRollComponent.builder()
                .bankRollRepositoryComponent(((BetSafeApp) getApplication()).getBankRollRepositoryComponent())
                .addBankRollModule(new AddBankRollModule(addBankRollFragment))
                .build()
                .inject(this);
    }
}
