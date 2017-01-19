package net.betsafeapp.android.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import net.betsafeapp.android.BaseFragment;
import net.betsafeapp.android.Constants;
import net.betsafeapp.android.R;
import net.betsafeapp.android.addbankroll.AddBankRollActivity;
import net.betsafeapp.android.utils.AlertUtil;

/**
 * Created by tyln on 19/01/2017.
 */

public final class MainFragment extends BaseFragment implements MainContract.View, View.OnClickListener {
    @NonNull
    private MainContract.Presenter mPresenter;

    @NonNull
    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_main;
    }

    @Override
    protected int getMenuRes() {
        return Constants.NO_RES;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final FloatingActionButton floatingAcionButton = (FloatingActionButton) view.findViewById(R.id.fab);
        floatingAcionButton.setOnClickListener(this);
    }

    @Override
    public void setPresenter(@NonNull MainContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void alert(@Nullable String message) {
        AlertUtil.alert(getApplicationContext(), message);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                mPresenter.addBankRoll();
                break;
        }
    }

    @Override
    public void navigateToAddBankRoll() {
        startActivity(AddBankRollActivity.newIntent(getActivity()));
    }
}
