package net.betsafeapp.android.addbankroll;

import android.support.annotation.NonNull;

import net.betsafeapp.android.BasePresenter;
import net.betsafeapp.android.BaseView;

/**
 * Created by tyln on 19/01/2017.
 */

interface AddBankRollContract {
    interface View extends BaseView<Presenter> {
        void errorOnCreatingBankRoll();

        void onBankRollCreated();
    }

    interface Presenter extends BasePresenter {
        void addBankroll(@NonNull String name, double initialAmount, int privacy);
    }
}
