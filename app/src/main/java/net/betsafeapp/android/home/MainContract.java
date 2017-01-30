package net.betsafeapp.android.home;

import android.support.annotation.NonNull;

import net.betsafeapp.android.BasePresenter;
import net.betsafeapp.android.BaseView;
import net.betsafeapp.android.data.BankRoll;

import java.util.List;

/**
 * Created by tyln on 17/01/2017.
 */

interface MainContract {
    interface View extends BaseView<Presenter> {
        void initBankRollsAdater(@NonNull List<BankRoll> bankrolls);

        void notifyBankRollDataChanged();

        void navigateToAddBankRoll();

        void navigateToAddBet();
    }

    interface Presenter extends BasePresenter {
        void addBankRoll();

        void addBet();
    }
}
