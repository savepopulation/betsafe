package net.betsafeapp.android.addbankroll;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import net.betsafeapp.android.RxPresenter;
import net.betsafeapp.android.data.BankRoll;
import net.betsafeapp.android.data.source.BankRollRepository;
import net.betsafeapp.android.data.factory.BankRollFactory;
import net.betsafeapp.android.util.ValidationUtil;

import javax.inject.Inject;

/**
 * Created by tyln on 19/01/2017.
 */

final class AddBankRollPresenter extends RxPresenter<AddBankRollContract.View>
        implements AddBankRollContract.Presenter {

    @NonNull
    private BankRollRepository mBankRollRepository;

    private boolean isBankRollNameValid;
    private boolean isInitialCapitalValid;

    @Inject
    AddBankRollPresenter(@NonNull AddBankRollContract.View view, @NonNull BankRollRepository bankRollRepository) {
        super(view);
        this.mBankRollRepository = bankRollRepository;
        this.isInitialCapitalValid = false;
        this.isInitialCapitalValid = false;

        mView.setPresenter(this);
    }

    @Override
    public void start() {
        mView.enableOrDisableCreateBankRoll(canCreateNewBankRoll());
    }

    @Override
    public void subscribe() {
        // Empty method
    }

    @Override
    public void addBankroll(@NonNull String name, double initialAmount, int privacy) {
        final BankRoll bankRoll = BankRollFactory.newInstance(name, initialAmount, privacy);
        if (bankRoll == null) {
            mView.errorOnCreatingBankRoll();
            return;
        }

        mBankRollRepository.createNewBankroll(bankRoll);
        mView.onBankRollCreated();
    }

    @Override
    public void checkBankRollName(@Nullable String bankRollName) {
        isBankRollNameValid = !ValidationUtil.isNullOrEmpty(bankRollName);
        mView.enableOrDisableCreateBankRoll(canCreateNewBankRoll());
    }

    @Override
    public void checkBankRollInitialCapital(double initialCapital) {
        isInitialCapitalValid = ValidationUtil.isInitialAmountValid(initialCapital);
        mView.enableOrDisableCreateBankRoll(canCreateNewBankRoll());
    }

    private boolean canCreateNewBankRoll() {
        return isBankRollNameValid && isInitialCapitalValid;
    }
}
