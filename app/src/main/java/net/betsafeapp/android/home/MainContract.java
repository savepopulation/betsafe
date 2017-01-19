package net.betsafeapp.android.home;

import net.betsafeapp.android.BasePresenter;
import net.betsafeapp.android.BaseView;

/**
 * Created by tyln on 17/01/2017.
 */

interface MainContract {
    interface View extends BaseView<Presenter> {
        // Empty interface
    }

    interface Presenter extends BasePresenter {
        void showNewSafeTypes();
    }
}
