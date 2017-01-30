package net.betsafeapp.android.home;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.betsafeapp.android.R;
import net.betsafeapp.android.data.BankRoll;
import net.betsafeapp.android.util.DateUtil;

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
        holder.mTextViewQuery.setText(bankRoll.getName());
        holder.mTextViewDate.setText(DateUtil.convertTime(bankRoll.getCreateDate()));
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

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mTextViewQuery;
        private final TextView mTextViewDate;

        ViewHolder(View itemView) {
            super(itemView);
            mTextViewQuery = (TextView) itemView.findViewById(R.id.textview_bankroll_name);
            mTextViewDate = (TextView) itemView.findViewById(R.id.textview_create_date);
        }
    }

    interface ItemClickListener {
        void onItemClicked(@Nullable String query);
    }
}
