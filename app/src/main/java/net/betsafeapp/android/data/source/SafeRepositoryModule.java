package net.betsafeapp.android.data.source;

import android.content.Context;
import android.support.annotation.NonNull;

import net.betsafeapp.android.data.source.local.SafeLocalDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tyln on 17/01/2017.
 */

@Module
public class SafeRepositoryModule {
    public SafeRepositoryModule() {
        // Empty Constructor
    }

    @Singleton
    @Provides
    @Local
    SafeLocalDataSource provideSafeLocalDataSource(@NonNull Context context) {
        return new SafeLocalDataSource(context);
    }
}
