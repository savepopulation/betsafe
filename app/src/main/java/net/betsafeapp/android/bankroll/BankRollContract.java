package net.betsafeapp.android.bankroll;

import android.support.annotation.NonNull;

import net.betsafeapp.android.BasePresenter;
import net.betsafeapp.android.BaseView;

/**
 * Created by tyln on 02/03/2017.
 */

interface BankRollContract {
    interface View extends BaseView<Presenter> {
        void navigateToAddBet(@NonNull String defaultBankRollId);

        void initToolbar(@NonNull String bankRollName);

        void bankRollDeleted(@NonNull String bankRollName);

        void showBankRollDeleteConfirmDialog();
    }

    interface Presenter extends BasePresenter {
        void addBet();

        void deleteBankRollRequested();

        void deleteBankRoll();
    }
}
