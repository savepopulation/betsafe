package net.betsafeapp.android.bankroll.bets;

import android.support.annotation.NonNull;

import net.betsafeapp.android.BasePresenter;
import net.betsafeapp.android.BaseView;

/**
 * Created by tyln on 25/03/2017.
 */

public interface BankRollBetsContract {
    interface View extends BaseView<Presenter> {
        void navigateToAddBet(@NonNull String defaultBankRollId);
    }

    interface Presenter extends BasePresenter {
        void addBet();
    }
}
