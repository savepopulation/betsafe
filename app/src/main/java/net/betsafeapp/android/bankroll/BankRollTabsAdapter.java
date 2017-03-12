package net.betsafeapp.android.bankroll;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

/**
 * Created by tyln on 12/03/2017.
 */

final class BankRollTabsAdapter extends FragmentPagerAdapter {
    private String[] mTitles;

    public BankRollTabsAdapter(@NonNull FragmentManager fm, @NonNull String[] titles) {
        super(fm);
        this.mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return BankRollDetailFragment.newInstance();

            case 1:
                return BankRollHistoryFragment.newInstance();

            default:
                return BankRollHistoryFragment.newInstance();
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
