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
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by tyln on 26/01/2017.
 */

final class AddBetPresenter extends RxPresenter implements AddBetContract.Presenter {
    @NonNull
    private AddBetContract.View mView;

    @NonNull
    private final BankRollRepository mBankRollRepository;

    @Nullable
    private final String mBankrollId;

    @Inject
    AddBetPresenter(@NonNull AddBetContract.View view,
                    @NonNull BankRollRepository bankRollRepository,
                    @Nullable String bankrollId) {
        super();
        this.mView = view;
        this.mBankRollRepository = bankRollRepository;
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

    @Override
    public void unsubscribe() {
        clearSubscriptions();
    }

    @Override
    public void destroy() {
        this.mView = null;
    }

    private void getBankrolls() {
        final List<BankRoll> bankRolls = new ArrayList<>();
        final Subscription bankrollSubscription = mBankRollRepository.getBankRolls()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BankRoll>() {
                    @Override
                    public void onCompleted() {
                        mView.showBankrolls(bankRolls);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.alert(e.getMessage());
                    }

                    @Override
                    public void onNext(BankRoll item) {
                        bankRolls.add(item);
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
