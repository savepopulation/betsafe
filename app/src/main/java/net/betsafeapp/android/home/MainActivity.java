package net.betsafeapp.android.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import net.betsafeapp.android.BaseActivity;
import net.betsafeapp.android.Constants;
import net.betsafeapp.android.R;

public class MainActivity extends BaseActivity {
    @NonNull
    public static Intent newIntent(@NonNull Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected int getMenuRes() {
        return R.menu.menu_main;
    }

    @Override
    protected int getScreenName() {
        return Constants.NO_RES;
    }

    @Override
    protected int getNavigationType() {
        return NAVIGATION_ROOT;
    }
}
