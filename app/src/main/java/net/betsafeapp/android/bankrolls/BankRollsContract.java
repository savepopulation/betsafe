package net.betsafeapp.android.bankrolls;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import net.betsafeapp.android.BasePresenter;
import net.betsafeapp.android.BaseView;
import net.betsafeapp.android.data.BankRoll;

import java.util.List;

/**
 * Created by tyln on 17/01/2017.
 */

interface BankRollsContract {
    interface View extends BaseView<Presenter> {
        void initBankRollsAdapter(@NonNull List<BankRoll> bankrolls);

        void notifyBankRollDataChanged();

        void navigateToAddBankRoll();

        void navigateToAddBet();

        void emptyBankroll();

        void collapseFloatingActionsMenu();

        void navigateToBankRollDetail(@NonNull String bankRollId);

        void navigateToSettings();
    }

    interface Presenter extends BasePresenter {
        void addBankRoll();

        void addBet();

        void showBankRoll(@NonNull String bankRollId);

        void showSettings();

        void search(@Nullable String query);
    }
}
