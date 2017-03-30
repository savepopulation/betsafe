package net.betsafeapp.android.bankroll;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;

import net.betsafeapp.android.BaseActivity;
import net.betsafeapp.android.BetSafeApp;
import net.betsafeapp.android.Constants;
import net.betsafeapp.android.R;
import net.betsafeapp.android.bankroll.bets.BankRollBetsPresenterModule;
import net.betsafeapp.android.bankroll.detail.BankRollDetailFragment;

import net.betsafeapp.android.bankroll.detail.BankRollDetailPresenter;
import net.betsafeapp.android.bankroll.detail.BankRollDetailPresenterModule;
import net.betsafeapp.android.bankroll.bets.BankRollBetsFragment;
import net.betsafeapp.android.bankroll.bets.BankRollBetsPresenter;
import net.betsafeapp.android.util.UiUtil;

import javax.inject.Inject;

/**
 * Created by tyln on 02/03/2017.
 */

public final class BankRollActivity extends BaseActivity {
    private static final String BUNDLE_BANKROLL_ID = "bankroll_id";

    @NonNull
    @Inject
    BankRollDetailPresenter mBankRollDetailPresenter;

    @NonNull
    @Inject
    BankRollBetsPresenter mBankRollBetsPresenter;

    @NonNull
    @Inject
    String mBankRollId;

    @NonNull
    public static Intent newIntent(@NonNull Context context, @NonNull String bankrollId) {
        final Intent intent = new Intent(context, BankRollActivity.class);
        intent.putExtra(BUNDLE_BANKROLL_ID, bankrollId);
        return intent;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_bankroll;
    }

    @Override
    protected int getMenuRes() {
        return Constants.NO_RES;
    }

    @Override
    protected int getScreenName() {
        return Constants.NO_RES;
    }

    @Override
    protected int getTitleRes() {
        return Constants.NO_RES;
    }

    @Override
    protected int getNavigationType() {
        return NAVIGATION_BACK;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String bankrollId = "";
        final Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            bankrollId = bundle.getString(BUNDLE_BANKROLL_ID, "");
        }

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        setSupportActionBar(toolbar);


        BankRollDetailFragment detailFragment = (BankRollDetailFragment) getSupportFragmentManager()
                .findFragmentByTag(UiUtil.generateViewPagerFragmentTag(R.id.viewpager_bankroll, BankRollTabsAdapter.TAB_DETAIL));
        if (detailFragment == null) {
            detailFragment = BankRollDetailFragment.newInstance();
        }

        BankRollBetsFragment betsFragment = (BankRollBetsFragment) getSupportFragmentManager()
                .findFragmentByTag(UiUtil.generateViewPagerFragmentTag(R.id.viewpager_bankroll, BankRollTabsAdapter.TAB_BETS));
        if (betsFragment == null) {
            betsFragment = BankRollBetsFragment.newInstance();
        }

        final SparseArray<Fragment> fragments = new SparseArray<>();
        fragments.put(0, detailFragment);
        fragments.put(1, betsFragment);

        final ViewPager viewPagerBankRollTabs = (ViewPager) findViewById(R.id.viewpager_bankroll);
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        final BankRollTabsAdapter bankRollTabsAdapter = new BankRollTabsAdapter(getSupportFragmentManager(),
                getResources().getStringArray(R.array.tabs_bankroll),
                fragments);
        viewPagerBankRollTabs.setAdapter(bankRollTabsAdapter);
        tabLayout.setupWithViewPager(viewPagerBankRollTabs);

        viewPagerBankRollTabs.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Empty method
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case BankRollTabsAdapter.TAB_DETAIL:
                        mBankRollDetailPresenter.subscribe();
                        break;

                    case BankRollTabsAdapter.TAB_BETS:
                        mBankRollDetailPresenter.subscribe();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // Empty method
            }
        });

        DaggerBankRollComponent.builder()
                .bankRollDetailPresenterModule(new BankRollDetailPresenterModule(detailFragment, bankrollId))
                .bankRollBetsPresenterModule(new BankRollBetsPresenterModule(betsFragment))
                .bankRollRepositoryComponent(((BetSafeApp) getApplication()).getBankRollRepositoryComponent())
                .build()
                .inject(this);
    }
}
