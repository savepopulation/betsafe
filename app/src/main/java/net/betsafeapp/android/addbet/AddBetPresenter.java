package net.betsafeapp.android.addbet;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import net.betsafeapp.android.BasePresenter;
import net.betsafeapp.android.RxPresenter;
import net.betsafeapp.android.data.BankRoll;
import net.betsafeapp.android.data.Bet;
import net.betsafeapp.android.data.factory.BetFactory;
import net.betsafeapp.android.data.source.BankRollRepository;
import net.betsafeapp.android.util.ValidationUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by tyln on 26/01/2017.
 */

final class AddBetPresenter extends RxPresenter<AddBetContract.View> implements AddBetContract.Presenter {

    @NonNull
    private final BankRollRepository mBankRollRepository;

    @NonNull
    private final List<BankRoll> mBankRolls;

    @Nullable
    private final String mBankrollId;

    @Inject
    AddBetPresenter(@NonNull AddBetContract.View view,
                    @NonNull BankRollRepository bankRollRepository,
                    @Nullable String bankrollId) {
        super(view);
        this.mView = view;
        this.mBankRollRepository = bankRollRepository;
        this.mBankRolls = new ArrayList<>();
        this.mBankrollId = bankrollId;

        mView.setPresenter(this);
    }

    @Override
    public void start() {
        // Empty
    }

    @Override
    public void subscribe() {
        getBankrolls();
    }

    private void getBankrolls() {
        final Subscription bankrollSubscription = mBankRollRepository.getBankRolls()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<BankRoll, Boolean>() {
                    @Override
                    public Boolean call(BankRoll bankRoll) {
                        return ValidationUtil.isNullOrEmpty(mBankrollId) || mBankrollId.equals(bankRoll.getId());
                    }
                })
                .subscribe(new Observer<BankRoll>() {
                    @Override
                    public void onCompleted() {
                        mView.showBankrolls(mBankRolls);
                        if (!ValidationUtil.isNullOrEmpty(mBankrollId) && mBankRolls.size() == 1) {
                            mView.enableOrDisableBankRollSelection(false);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.alert(e.getMessage());
                    }

                    @Override
                    public void onNext(BankRoll item) {
                        mBankRolls.add(item);
                    }
                });

        addSubscription(bankrollSubscription);
    }

    @Override
    public void addBet(@NonNull String bankrollId,
                       @NonNull String eventName,
                       @NonNull String bookmaker,
                       double odd,
                       double stake,
                       int sport,
                       int pick) {
        final Bet bet = BetFactory.newInstance(eventName, bookmaker, bankrollId, odd, stake, sport, pick);
        if (bet == null) {
            mView.addBetError();
            return;
        }

        mBankRollRepository.addBet(bankrollId, bet);
        mView.betAddedSuccessfully();
    }
}
