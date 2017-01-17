package net.betsafeapp.android;

import android.app.Application;
import android.support.annotation.NonNull;

import net.betsafeapp.android.data.source.DaggerSafeRepositoryComponent;
import net.betsafeapp.android.data.source.SafeRepositoryComponent;
import net.betsafeapp.android.data.source.SafeRepositoryModule;

/**
 * Created by tyln on 16/01/2017.
 */

public class BetSafeApp extends Application {
    @NonNull
    private SafeRepositoryComponent mSafeRepositoryComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mSafeRepositoryComponent = DaggerSafeRepositoryComponent.builder()
                .applicationModule(new ApplicationModule((this)))
                .safeRepositoryModule(new SafeRepositoryModule())
                .build();
    }

    @NonNull
    public SafeRepositoryComponent getSafeRepositoryComponent() {
        return mSafeRepositoryComponent;
    }
}
