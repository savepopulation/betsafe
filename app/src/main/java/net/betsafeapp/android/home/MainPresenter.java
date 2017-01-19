package net.betsafeapp.android.home;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import net.betsafeapp.android.data.source.BankRollRepository;

import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by tyln on 17/01/2017.
 */

final class MainPresenter implements MainContract.Presenter {
    @Nullable
    private MainContract.View mView;

    @NonNull
    private final BankRollRepository mSafeRepository;

    @NonNull
    private final CompositeSubscription mCompositeSubscription;

    @Inject
    MainPresenter(@NonNull MainContract.View view, @NonNull BankRollRepository safeRepository) {
        this.mView = view;
        this.mSafeRepository = safeRepository;
        this.mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void start() {
        // Empty method
    }

    @Override
    public void subscribe() {
        // get repositories
    }

    @Override
    public void unsubscribe() {
        // unsubscribe
    }

    @Override
    public void destroy() {
        this.mView = null;
    }

    @Override
    public void showNewSafeTypes() {

    }
}
