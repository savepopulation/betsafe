package net.betsafeapp.android.bankroll.bets;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import net.betsafeapp.android.RxPresenter;
import net.betsafeapp.android.data.BankRoll;
import net.betsafeapp.android.data.Bet;
import net.betsafeapp.android.data.source.BankRollRepository;
import net.betsafeapp.android.util.ValidationUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tyln on 25/03/2017.
 */

public class BankRollBetsPresenter extends RxPresenter<BankRollBetsContract.View>
        implements BankRollBetsContract.Presenter {

    @NonNull
    private BankRollRepository mBankRollRepository;

    @NonNull
    private final String mBankRollId;

    @NonNull
    private final List<Bet> mBets;

    @Inject
    BankRollBetsPresenter(@NonNull BankRollBetsContract.View view,
                          @NonNull BankRollRepository bankRollRepository,
                          @NonNull String bankRollId) {
        super(view);
        this.mBankRollRepository = bankRollRepository;
        this.mBankRollId = bankRollId;
        this.mBets = new ArrayList<>();

        mView.setPresenter(this);
    }

    @Override
    public void start() {
        mView.initBets(mBets);
    }

    @Override
    public void subscribe() {
        getBets();
    }

    @Override
    public void addBet() {
        if (ValidationUtil.isNullOrEmpty(mBankRollId)) {
            return;
        }

        mView.navigateToAddBet(mBankRollId);
    }

    @Override
    public void editBet(@NonNull String betId) {
        if (!ValidationUtil.isNullOrEmpty(betId)) {
            mView.showEditBet();
        }
    }

    @Override
    public void removeBet(@NonNull Bet bet) {
        if (bet == null) {
            return;
        }

        mBets.remove(bet);
        mBankRollRepository.removeBetFromBankRoll(mBankRollId, bet);
        mView.betRemoved(bet.getEvent());
        mView.notifyUi();
    }

    private void getBets() {
        mBets.clear();
        final Subscription betsSbuscription = mBankRollRepository.getBets(mBankRollId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Bet>() {
                    @Override
                    public void onCompleted() {
                        mView.notifyUi();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.alert(e.getMessage());
                    }

                    @Override
                    public void onNext(Bet bet) {
                        mBets.add(bet);
                    }
                });
        addSubscription(betsSbuscription);
    }
}
