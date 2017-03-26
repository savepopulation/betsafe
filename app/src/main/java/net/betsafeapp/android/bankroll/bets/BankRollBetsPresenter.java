package net.betsafeapp.android.bankroll.bets;

import android.support.annotation.NonNull;

import net.betsafeapp.android.RxPresenter;
import net.betsafeapp.android.data.BankRoll;
import net.betsafeapp.android.data.source.BankRollRepository;

import javax.inject.Inject;

/**
 * Created by tyln on 25/03/2017.
 */

public class BankRollBetsPresenter extends RxPresenter<BankRollBetsContract.View>
        implements BankRollBetsContract.Presenter {

    @NonNull
    private BankRollRepository mBankRollRepository;

    @NonNull
    private BankRoll mBankRoll;

    @Inject
    BankRollBetsPresenter(@NonNull BankRollBetsContract.View view,
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
