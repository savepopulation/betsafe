package net.betsafeapp.android.settings;

import net.betsafeapp.android.BasePresenter;
import net.betsafeapp.android.BaseView;

/**
 * Created by tyln on 09/03/2017.
 */

interface SettingsContract {
    interface View extends BaseView<Presenter> {
        // Marker interface
    }

    interface Presenter extends BasePresenter {
        // Marker interface
    }
}
