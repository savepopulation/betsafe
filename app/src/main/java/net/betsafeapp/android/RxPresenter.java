package net.betsafeapp.android;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by tyln on 01/03/2017.
 */

public abstract class RxPresenter {
    @NonNull
    private final CompositeSubscription mCompositeSubscription;

    protected RxPresenter() {
        this.mCompositeSubscription = new CompositeSubscription();
    }

    protected void clearSubscriptions() {
        if (mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.clear();
        }
    }

    protected void addSubscription(@Nullable Subscription subscription) {
        if (subscription != null) {
            mCompositeSubscription.add(subscription);
        }
    }

    protected void removeSubscription(@Nullable Subscription subscription) {
        if (subscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.remove(subscription);
        }
    }
}
