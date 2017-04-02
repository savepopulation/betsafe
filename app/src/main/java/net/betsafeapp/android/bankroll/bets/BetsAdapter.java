package net.betsafeapp.android.bankroll.bets;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    public void onBindViewHolder(final BetsAdapter.ViewHolder holder, int position) {
        final Bet bet = mItems.get(position);

        if (bet == null) return;

        final Context context = holder.itemView.getContext();

        holder.mTextViewBetName.setText(bet.getEvent());
        holder.mTextViewBetPick.setText(context.getString(R.string.label_bet_pick, bet.getPick().getName()));
        holder.mTextViewStake.setText(context.getString(R.string.label_bet_stake, bet.getStake()));

        final String resultString = context.getString(UiUtil.getStringResForBetStatus(bet.getResult()));
        holder.mTextViewStatus.setText(context.getString(R.string.label_bet_status, resultString));

        holder.mTextViewDate.setText(context.getString(R.string.label_bet_date,
                DateUtil.convertTime(bet.getCreateDate(), DateUtil.DATE_FORMAT_BET_ROW)));

        holder.mTextViewOdd.setText(context.getString(R.string.label_bet_odd, bet.getOdds()));
        holder.mTextViewBooker.setText(context.getString(R.string.label_bet_booker, bet.getBookMaker()));

        // TODO convert stports to object
        holder.mTextViewSport.setText(context.getString(R.string.label_bet_sport, String.valueOf(bet.getSport())));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.mLinearLayoutBetDetails.getVisibility() == View.VISIBLE) {
                    holder.mLinearLayoutBetDetails.setVisibility(View.GONE);
                } else {
                    holder.mLinearLayoutBetDetails.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    final class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mTextViewBetName;
        private final TextView mTextViewStake;
        private final TextView mTextViewBetPick;
        private final TextView mTextViewStatus;
        private final TextView mTextViewDate;
        private final TextView mTextViewOdd;
        private final TextView mTextViewBooker;
        private final TextView mTextViewSport;
        private final LinearLayout mLinearLayoutBetDetails;

        ViewHolder(View itemView) {
            super(itemView);
            mTextViewBetName = (TextView) itemView.findViewById(R.id.textview_bet_name);
            mTextViewStake = (TextView) itemView.findViewById(R.id.textview_bet_stake);
            mTextViewBetPick = (TextView) itemView.findViewById(R.id.textview_bet_pick);
            mTextViewStatus = (TextView) itemView.findViewById(R.id.textview_bet_status);
            mTextViewDate = (TextView) itemView.findViewById(R.id.textview_bet_date);
            mTextViewOdd = (TextView) itemView.findViewById(R.id.textview_bet_odd);
            mTextViewBooker = (TextView) itemView.findViewById(R.id.textview_bet_booker);
            mTextViewSport = (TextView) itemView.findViewById(R.id.textview_bet_sport);
            mLinearLayoutBetDetails = (LinearLayout) itemView.findViewById(R.id.lienarlayout_bet_details);
        }
    }

    interface ItemClickListener {
        void editBet(@NonNull Bet bet);

        void removeBet(@NonNull Bet bet);
    }
}
