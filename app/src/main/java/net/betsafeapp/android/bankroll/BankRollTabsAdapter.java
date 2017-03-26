package net.betsafeapp.android.bankroll;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import net.betsafeapp.android.bankroll.detail.BankRollDetailFragment;
import net.betsafeapp.android.data.BankRoll;

/**
 * Created by tyln on 12/03/2017.
 */

final class BankRollTabsAdapter extends FragmentPagerAdapter {
    static final int TAB_DETAIL = 0;
    static final int TAB_BETS = 1;

    @NonNull
    private String[] mTitles;

    @NonNull
    private SparseArray<Fragment> mFragments;

    BankRollTabsAdapter(@NonNull FragmentManager fm,
                        @NonNull String[] titles,
                        @NonNull SparseArray fragments) {
        super(fm);
        this.mTitles = titles;
        this.mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case TAB_DETAIL:
                return mFragments.get(0);

            case TAB_BETS:
                return mFragments.get(1);

            default:
                return mFragments.get(0);
        }
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
