package net.betsafeapp.android.bankroll.history;

import android.support.annotation.NonNull;

import net.betsafeapp.android.RxPresenter;
import net.betsafeapp.android.data.BankRoll;
import net.betsafeapp.android.data.source.BankRollRepository;

import javax.inject.Inject;

/**
 * Created by tyln on 25/03/2017.
 */

public class BankRollHistoryPresenter extends RxPresenter<BankRollHistoryContract.View>
        implements BankRollHistoryContract.Presenter {

    @NonNull
    private BankRollRepository mBankRollRepository;

    @NonNull
    private BankRoll mBankRoll;

    @Inject
    BankRollHistoryPresenter(@NonNull BankRollHistoryContract.View view,
                             @NonNull BankRollRepository bankRollRepository) {
        super(view);
        this.mBankRollRepository = bankRollRepository;

        mView.setPresenter(this);
    }

    @Override
    public void start() {
        // empty
    }

    @Override
    public void subscribe() {
        // Empty
    }
}
