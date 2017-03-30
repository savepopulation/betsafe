package net.betsafeapp.android.bankroll.bets;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import net.betsafeapp.android.R;
import net.betsafeapp.android.RxFragment;
import net.betsafeapp.android.addbet.AddBetActivity;
import net.betsafeapp.android.data.Bet;
import net.betsafeapp.android.view.DividerDecorator;

import java.util.List;

/**
 * Created by tyln on 12/03/2017.
 */

public final class BankRollBetsFragment extends RxFragment<BankRollBetsContract.Presenter>
        implements BankRollBetsContract.View,
        BetsAdapter.ItemClickListener {

    @NonNull
    private RecyclerView mRecyclerViewBets;

    @NonNull
    private BetsAdapter mBetsAdapter;

    @NonNull
    public static BankRollBetsFragment newInstance() {
        return new BankRollBetsFragment();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_bankroll_bets;
    }

    @Override
    protected int getMenuRes() {
        return R.menu.menu_bets;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerViewBets = (RecyclerView) view.findViewById(R.id.recyclerview_bets);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerViewBets.addItemDecoration(new DividerDecorator(getActivity(), R.drawable.divider));
        mRecyclerViewBets.setLayoutManager(linearLayoutManager);
        mRecyclerViewBets.setHasFixedSize(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_bet:
                mPresenter.addBet();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void navigateToAddBet(@NonNull String defaultBankRollId) {
        startActivity(AddBetActivity.newIntent(getActivity(), defaultBankRollId));
    }

    @Override
    public void initBets(@NonNull List<Bet> bets) {
        mBetsAdapter = new BetsAdapter(bets, this);
        mRecyclerViewBets.setAdapter(mBetsAdapter);
    }

    @Override
    public void notifyUi() {
        mBetsAdapter.notifyDataSetChanged();
    }

    @Override
    public void betRemoved(@NonNull String betName) {
        alert(getString(R.string.success_message_bet_removed, betName));
    }

    @Override
    public void editBet(@NonNull Bet bet) {
        mPresenter.editBet(bet);
    }

    @Override
    public void removeBet(@NonNull Bet bet) {
        mPresenter.removeBet(bet);
    }
}
