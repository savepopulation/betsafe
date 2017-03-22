package net.betsafeapp.android.bankrolls;

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
import net.betsafeapp.android.util.DateUtil;
import net.betsafeapp.android.util.UiUtil;
import net.betsafeapp.android.util.ValidationUtil;

import java.util.List;

/**
 * Created by tyln on 30/01/2017.
 */

final class BankRollAdapter extends RecyclerView.Adapter<BankRollAdapter.ViewHolder> {

    @NonNull
    private final List<BankRoll> mItems;

    @Nullable
    private final ItemClickListener mItemClickListener;

    BankRollAdapter(@NonNull List<BankRoll> items, @Nullable ItemClickListener itemClickListener) {
        this.mItems = items;
        this.mItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_bankroll, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final BankRoll bankRoll = mItems.get(position);

        holder.mTextViewBankrollName.setText(bankRoll.getName());
        holder.mTextViewBalance.setText(String.valueOf(bankRoll.getCurrentCapital()));

        int betCount = 0;
        if (!ValidationUtil.isNullOrEmpty(bankRoll.getBets())) {
            betCount = bankRoll.getBets().size();
        }
        holder.mTextViewBetCount.setText(String.valueOf(betCount));

        final String date = DateUtil.convertTime(bankRoll.getUpdateDate());
        holder.mTextViewCreateDate.setText(date);

        int backgroundDrawableId = R.drawable.background_bankroll_profit_equal;
        int imageResId = R.mipmap.ic_trending_neutral_white_24dp;
        if (bankRoll.getInitialCapital() < bankRoll.getCurrentCapital()) {
            backgroundDrawableId = R.drawable.background_bankroll_profit_plus;
            imageResId = R.mipmap.ic_trending_up_white_24dp;
        } else if (bankRoll.getInitialCapital() > bankRoll.getCurrentCapital()) {
            backgroundDrawableId = R.drawable.background_bankroll_profit_negative;
            imageResId = R.mipmap.ic_trending_down_white_24dp;
        }
        holder.mImageViewBankroll.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), backgroundDrawableId));
        holder.mImageViewBankroll.setImageResource(imageResId);
        holder.mViewGroupRow.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(),
                UiUtil.getColorResByBetStatus(bankRoll.getStatus())));

        if (mItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClicked(bankRoll.getId());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    final class ViewHolder extends RecyclerView.ViewHolder {
        private final ViewGroup mViewGroupRow;
        private final TextView mTextViewBankrollName;
        private final TextView mTextViewCreateDate;
        private final TextView mTextViewBalance;
        private final TextView mTextViewBetCount;
        private final ImageView mImageViewBankroll;

        ViewHolder(View itemView) {
            super(itemView);
            mViewGroupRow = (ViewGroup) itemView.findViewById(R.id.row);
            mTextViewBankrollName = (TextView) itemView.findViewById(R.id.textview_bankroll_name);
            mTextViewCreateDate = (TextView) itemView.findViewById(R.id.textview_create_date);
            mTextViewBalance = (TextView) itemView.findViewById(R.id.textview_balance);
            mTextViewBetCount = (TextView) itemView.findViewById(R.id.textview_bet_count);
            mImageViewBankroll = (ImageView) itemView.findViewById(R.id.imageview_bakroll);
        }
    }

    interface ItemClickListener {
        void onItemClicked(@Nullable String bankRollId);
    }
}
