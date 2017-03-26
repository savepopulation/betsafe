package net.betsafeapp.android.bankroll.bets;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.betsafeapp.android.R;
import net.betsafeapp.android.data.BankRoll;
import net.betsafeapp.android.data.Bet;
import net.betsafeapp.android.util.DateUtil;
import net.betsafeapp.android.util.UiUtil;
import net.betsafeapp.android.util.ValidationUtil;

import java.util.List;

/**
 * Created by tyln on 26/03/2017.
 */

final class BetsAdapter extends RecyclerView.Adapter<BetsAdapter.ViewHolder> {
    @NonNull
    private final List<Bet> mItems;

    @Nullable
    private final ItemClickListener mItemClickListener;

    BetsAdapter(@NonNull List<Bet> items, @Nullable BetsAdapter.ItemClickListener itemClickListener) {
        this.mItems = items;
        this.mItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public BetsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_bets, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BetsAdapter.ViewHolder holder, int position) {
        final Bet bet = mItems.get(position);

        if (bet == null) return;

        holder.mTextViewBetName.setText(bet.getEvent());
        holder.mTextViewBetStake.setText(String.valueOf(bet.getStake()));
        holder.mTextViewBetOdd.setText(String.valueOf(bet.getOdds()));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    final class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mTextViewBetName;
        private final TextView mTextViewBetStake;
        private final TextView mTextViewBetOdd;

        ViewHolder(View itemView) {
            super(itemView);
            mTextViewBetName = (TextView) itemView.findViewById(R.id.textview_bet_name);
            mTextViewBetStake = (TextView) itemView.findViewById(R.id.textview_stake);
            mTextViewBetOdd = (TextView) itemView.findViewById(R.id.textview_odd);
        }
    }

    interface ItemClickListener {
        void onItemClicked(@Nullable String bankRollId);
    }
}
