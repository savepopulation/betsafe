package net.betsafeapp.android.editbankroll;

import net.betsafeapp.android.BasePresenter;
import net.betsafeapp.android.BaseView;
import net.betsafeapp.android.RxPresenter;

/**
 * Created by tyln on 22/03/2017.
 */

interface EditBankRollContract {
    interface View extends BaseView<Presenter> {
        // Marker interface
    }

    interface Presenter extends BasePresenter {
        // Marker interface
    }
}
