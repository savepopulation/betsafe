package net.betsafeapp.android.addbet;

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
 * Created by tyln on 26/01/2017.
 */

public final class AddBetActivity extends BaseActivity {
    @NonNull
    @Inject
    AddBetPresenter mAddBetPresenter;

    @NonNull
    public static Intent newIntent(@NonNull Context context) {
        return new Intent(context, AddBetActivity.class);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_add_bet;
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
        return R.string.screen_title_add_bet;
    }

    @Override
    protected int getNavigationType() {
        return NAVIGATION_BACK;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AddBetFragment addBetFragment = (AddBetFragment) getSupportFragmentManager()
                .findFragmentById(R.id.framelayout_main);

        if (addBetFragment == null) {
            addBetFragment = AddBetFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.framelayout_main, addBetFragment)
                    .commit();
        }

        DaggerAddBetComponent.builder()
                .bankRollRepositoryComponent(((BetSafeApp) getApplication()).getBankRollRepositoryComponent())
                .addBetPresenterModule(new AddBetPresenterModule(addBetFragment))
                .build()
                .inject(this);
    }
}
