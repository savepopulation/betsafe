package net.betsafeapp.android.editbankroll;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import net.betsafeapp.android.RxPresenter;
import net.betsafeapp.android.data.source.BankRollRepository;

import javax.inject.Inject;

/**
 * Created by tyln on 22/03/2017.
 */

final class EditBankRollPresenter extends RxPresenter<EditBankRollContract.View>
        implements EditBankRollContract.Presenter {

    @NonNull
    private final BankRollRepository mBankRollRepository;

    @NonNull
    private final String mBankRollId;

    @Inject
    EditBankRollPresenter(@NonNull EditBankRollContract.View view,
                          @NonNull BankRollRepository bankRollRepository,
                          @NonNull String bankRollId) {
        super(view);
        this.mBankRollRepository = bankRollRepository;
        this.mBankRollId = bankRollId;

        mView.setPresenter(this);
    }

    @Override
    public void start() {
        // Empty method
    }

    @Override
    public void subscribe() {
        // Empty method
    }
}
