package net.betsafeapp.android.home;

import net.betsafeapp.android.BasePresenter;
import net.betsafeapp.android.BaseView;

/**
 * Created by tyln on 17/01/2017.
 */

interface MainContract {
    interface View extends BaseView<Presenter> {
        void navigateToAddBankRoll();

        void navigateToAddBet();
    }

    interface Presenter extends BasePresenter {
        void addBankRoll();

        void addBet();
    }
}
