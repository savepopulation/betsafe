package net.betsafeapp.android.bankroll;

import android.support.annotation.NonNull;

import net.betsafeapp.android.RxPresenter;
import net.betsafeapp.android.addbet.AddBetActivity;
import net.betsafeapp.android.data.BankRoll;
import net.betsafeapp.android.data.source.BankRollRepository;
import net.betsafeapp.android.util.ValidationUtil;

import javax.inject.Inject;

/**
 * Created by tyln on 02/03/2017.
 */

final class BankRollPresenter extends RxPresenter<BankRollContract.View> implements BankRollContract.Presenter {

    @NonNull
    private BankRollRepository mBankRollRepository;

    @NonNull
    private String mBankRollId;

    @NonNull
    private BankRoll mBankRoll;

    @Inject
    BankRollPresenter(@NonNull BankRollContract.View view,
                      @NonNull BankRollRepository bankRollRepository,
                      @NonNull String bankRollId) {
        super(view);
        this.mView = view;
        this.mBankRollRepository = bankRollRepository;
        this.mBankRollId = bankRollId;

        mView.setPresenter(this);
    }

    @Override
    public void start() {
        // Empty method
    }

    @Override
    public void subscribe() {
        // Empty method
    }

    @Override
    public void addBet() {
        if (ValidationUtil.isNullOrEmpty(mBankRollId)) {
            return;
        }

        mView.navigateToAddBet(mBankRollId);
    }
}
