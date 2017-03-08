package net.betsafeapp.android.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.getbase.floatingactionbutton.FloatingActionsMenu;

import net.betsafeapp.android.BaseFragment;
import net.betsafeapp.android.Constants;
import net.betsafeapp.android.R;
import net.betsafeapp.android.addbankroll.AddBankRollActivity;
import net.betsafeapp.android.addbet.AddBetActivity;
import net.betsafeapp.android.bankroll.BankRollActivity;
import net.betsafeapp.android.data.BankRoll;
import net.betsafeapp.android.util.AlertUtil;
import net.betsafeapp.android.view.DividerDecorator;

import java.util.List;

/**
 * Created by tyln on 19/01/2017.
 */

public final class MainFragment extends BaseFragment
        implements MainContract.View, View.OnClickListener, BankRollAdapter.ItemClickListener {
    @NonNull
    private MainContract.Presenter mPresenter;

    @NonNull
    private RecyclerView mRecyclerViewBankRolls;

    @NonNull
    private BankRollAdapter mBankRollAdapter;

    @NonNull
    private CoordinatorLayout mCoordinatorLayoutMain;

    @NonNull
    private FloatingActionsMenu mFloatingActionsMenu;

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

        mCoordinatorLayoutMain = (CoordinatorLayout) view.findViewById(R.id.coordinator_layout_main);
        mFloatingActionsMenu = (FloatingActionsMenu) view.findViewById(R.id.multiple_actions_left);

        mRecyclerViewBankRolls = (RecyclerView) view.findViewById(R.id.recyclerview_bankrolls);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerViewBankRolls.addItemDecoration(new DividerDecorator(getActivity(), R.drawable.divider));
        mRecyclerViewBankRolls.setLayoutManager(linearLayoutManager);
        mRecyclerViewBankRolls.setHasFixedSize(true);

        view.findViewById(R.id.action_add_bet).setOnClickListener(this);
        view.findViewById(R.id.action_new_bankroll).setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.start();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    public void onDestroy() {
        mPresenter.destroy();
        super.onDestroy();
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
            case R.id.action_add_bet:
                mPresenter.addBet();
                break;

            case R.id.action_new_bankroll:
                mPresenter.addBankRoll();
                break;
        }
    }

    @Override
    public void initBankRollsAdapter(@NonNull List<BankRoll> bankrolls) {
        mBankRollAdapter = new BankRollAdapter(bankrolls, this);
        mRecyclerViewBankRolls.setAdapter(mBankRollAdapter);
    }

    @Override
    public void notifyBankRollDataChanged() {
        mBankRollAdapter.notifyDataSetChanged();
    }

    @Override
    public void navigateToAddBankRoll() {
        startActivity(AddBankRollActivity.newIntent(getActivity()));
    }

    @Override
    public void navigateToAddBet() {
        startActivity(AddBetActivity.newIntent(getActivity(), null));
    }

    @Override
    public void emptyBankroll() {
        Snackbar.make(mCoordinatorLayoutMain, getString(R.string.error_message_empty_bankrolls), Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.snackbar_button_add), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mPresenter.addBankRoll();
                    }
                })
                .show();
    }

    @Override
    public void collapseFloatingActionsMenu() {
        mFloatingActionsMenu.collapse();
    }

    @Override
    public void navigateToBankRollDetail(@NonNull String bankRollId) {
        startActivity(BankRollActivity.newIntent(getActivity(), bankRollId));
    }

    @Override
    public void onItemClicked(@Nullable String bankrollId) {
        mPresenter.showBankRoll(bankrollId);
    }
}
