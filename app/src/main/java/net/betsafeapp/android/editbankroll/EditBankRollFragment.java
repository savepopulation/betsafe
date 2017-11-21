package net.betsafeapp.android.editbankroll;

import android.support.annotation.NonNull;

import net.betsafeapp.android.BaseFragment;
import net.betsafeapp.android.Constants;
import net.betsafeapp.android.R;
import net.betsafeapp.android.RxFragment;

/**
 * Created by tyln on 22/03/2017.
 */

public final class EditBankRollFragment extends RxFragment<EditBankRollContract.Presenter>
        implements EditBankRollContract.View {

    @NonNull
    public static EditBankRollFragment newInstance() {
        return new EditBankRollFragment();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_edit_bankroll;
    }
}
