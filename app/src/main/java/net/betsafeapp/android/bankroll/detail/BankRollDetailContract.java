package net.betsafeapp.android.bankroll.detail;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import net.betsafeapp.android.BasePresenter;
import net.betsafeapp.android.BaseView;

/**
 * Created by tyln on 02/03/2017.
 */

public interface BankRollDetailContract {
    interface View extends BaseView<Presenter> {

        void initToolbar(@NonNull String bankRollName);

        void bankRollDeleted(@NonNull String bankRollName);

        void showBankRollDeleteConfirmDialog();

        void showBankRollCloseConfirmDialog();

        void bankRollClosed(@NonNull String bankRollName);

        void showEditBankRoll(@NonNull String bankRollId);

        void showBankRollCurrentAmount(double currentAmount);

        void showBankRollInitialAmount(double initialAmount);

        void showBankRollStatus(int status);

        void showBankRollBetCount(int count);
    }

    interface Presenter extends BasePresenter {

        void deleteBankRollRequested();

        void deleteBankRoll();

        void closeBankRollRequested();

        void closeBankRoll();

        void editBankRoll();
    }
}
