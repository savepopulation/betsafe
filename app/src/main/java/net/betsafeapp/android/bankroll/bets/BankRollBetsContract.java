package net.betsafeapp.android.bankroll.bets;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import net.betsafeapp.android.BasePresenter;
import net.betsafeapp.android.BaseView;
import net.betsafeapp.android.data.Bet;

import java.util.List;

/**
 * Created by tyln on 25/03/2017.
 */

public interface BankRollBetsContract {
    interface View extends BaseView<Presenter> {
        void navigateToAddBet(@NonNull String defaultBankRollId);

        void initBets(@NonNull List<Bet> bets);

        void notifyUi();

        void betRemoved(@NonNull String betName);

        void showEditBet();
    }

    interface Presenter extends BasePresenter {
        void addBet();

        void editBet(@NonNull String betId);

        void removeBet(@NonNull Bet bet);
    }
}
