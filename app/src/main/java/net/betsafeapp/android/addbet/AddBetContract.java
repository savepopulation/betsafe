package net.betsafeapp.android.addbet;

import android.support.annotation.NonNull;

import net.betsafeapp.android.BasePresenter;
import net.betsafeapp.android.BaseView;
import net.betsafeapp.android.data.BankRoll;

import java.util.List;

/**
 * Created by tyln on 26/01/2017.
 */

interface AddBetContract {
    interface View extends BaseView<Presenter> {
        void showBankrolls(@NonNull List<BankRoll> bankrolls);
    }

    interface Presenter extends BasePresenter {
        // Empty
    }
}
