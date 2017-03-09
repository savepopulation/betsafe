package net.betsafeapp.android.settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import net.betsafeapp.android.BaseActivity;
import net.betsafeapp.android.Constants;
import net.betsafeapp.android.R;

import javax.inject.Inject;

/**
 * Created by tyln on 09/03/2017.
 */

public final class SettingsActivity extends BaseActivity {
    @NonNull
    @Inject
    SettingsPresenter mSettingsPresenter;

    @NonNull
    public static Intent newIntent(@NonNull Context context) {
        return new Intent(context, SettingsActivity.class);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_settings;
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
        return R.string.screen_title_settings;
    }

    @Override
    protected int getNavigationType() {
        return NAVIGATION_BACK;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SettingsFragment settingsFragment = (SettingsFragment) getSupportFragmentManager()
                .findFragmentById(R.id.framelayout_main);

        if (settingsFragment == null) {
            settingsFragment = SettingsFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.framelayout_main, settingsFragment)
                    .commit();
        }

        DaggerSettingsComponent.builder()
                .settingsPresenterModule(new SettingsPresenterModule(settingsFragment))
                .build()
                .inject(this);
    }
}
