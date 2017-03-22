package net.betsafeapp.android.editbankroll;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tyln on 22/03/2017.
 */

@Module
final class EditBankRollModule {
    @NonNull
    private final EditBankRollContract.View mView;

    @NonNull
    private final String mBankRollId;

    EditBankRollModule(@NonNull EditBankRollContract.View view, @NonNull String bankRollId) {
        this.mView = view;
        this.mBankRollId = bankRollId;
    }

    @NonNull
    @Provides
    EditBankRollContract.View provideEditBankRollView() {
        return this.mView;
    }

    @NonNull
    @Provides
    String provideBankRollId() {
        return this.mBankRollId;
    }
}
