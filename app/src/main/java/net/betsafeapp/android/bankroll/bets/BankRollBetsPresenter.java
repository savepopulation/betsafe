package net.betsafeapp.android.bankroll.bets;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import net.betsafeapp.android.RxPresenter;
import net.betsafeapp.android.data.BankRoll;
import net.betsafeapp.android.data.source.BankRollRepository;
import net.betsafeapp.android.util.ValidationUtil;

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

    @NonNull
    private String mBankRollId;

    @Inject
    BankRollBetsPresenter(@NonNull BankRollBetsContract.View view,
                          @NonNull BankRollRepository bankRollRepository,
                          @NonNull String bankRollId) {
        super(view);
        this.mBankRollRepository = bankRollRepository;
        this.mBankRollId = bankRollId;

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

    @Override
    public void addBet() {
        if (ValidationUtil.isNullOrEmpty(mBankRollId)) {
            return;
        }

        mView.navigateToAddBet(mBankRollId);
    }
}
