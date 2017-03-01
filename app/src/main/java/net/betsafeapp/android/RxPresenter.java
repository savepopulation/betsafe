package net.betsafeapp.android;

import android.support.annotation.NonNull;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by tyln on 01/03/2017.
 */

public class RxPresenter {
    @NonNull
    protected final CompositeSubscription mCompositeSubscription;

    protected RxPresenter() {
        this.mCompositeSubscription = new CompositeSubscription();
    }

    protected void clearSubscriptions() {
        if (mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.clear();
        }
    }
}
