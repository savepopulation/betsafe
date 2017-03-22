package net.betsafeapp.android.bankrolls;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.getbase.floatingactionbutton.FloatingActionsMenu;

import net.betsafeapp.android.R;
import net.betsafeapp.android.RxFragment;
import net.betsafeapp.android.addbankroll.AddBankRollActivity;
import net.betsafeapp.android.addbet.AddBetActivity;
import net.betsafeapp.android.bankroll.BankRollActivity;
import net.betsafeapp.android.data.BankRoll;
import net.betsafeapp.android.settings.SettingsActivity;
import net.betsafeapp.android.view.DividerDecorator;

import java.util.List;

/**
 * Created by tyln on 19/01/2017.
 */

public final class BankRollsFragment extends RxFragment<BankRollsContract.Presenter> implements
        BankRollsContract.View,
        View.OnClickListener,
        BankRollAdapter.ItemClickListener {

    @NonNull
    private RecyclerView mRecyclerViewBankRolls;

    @NonNull
    private BankRollAdapter mBankRollAdapter;

    @NonNull
    private CoordinatorLayout mCoordinatorLayoutMain;

    @NonNull
    private FloatingActionsMenu mFloatingActionsMenu;

    @NonNull
    public static BankRollsFragment newInstance() {
        return new BankRollsFragment();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_bankrolls;
    }

    @Override
    protected int getMenuRes() {
        // TODO add xxxhdpi for settings and search icon
        return R.menu.menu_bankrolls;
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
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint(getString(R.string.action_search));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mPresenter.search(newText);
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                mPresenter.showSettings();
                return true;
        }
        return super.onOptionsItemSelected(item);
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
    public void navigateToSettings() {
        startActivity(SettingsActivity.newIntent(getActivity()));
    }

    @Override
    public void onItemClicked(@Nullable String bankrollId) {
        mPresenter.showBankRoll(bankrollId);
    }
}
