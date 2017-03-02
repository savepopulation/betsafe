package net.betsafeapp.android.bankroll;

import net.betsafeapp.android.BasePresenter;
import net.betsafeapp.android.BaseView;

/**
 * Created by tyln on 02/03/2017.
 */

interface BankRollContract {
    interface View extends BaseView<Presenter> {
        // Empty method
    }

    interface Presenter extends BasePresenter {
        // Empty method
    }
}
