package net.betsafeapp.android.data.source;

import android.content.Context;
import android.support.annotation.NonNull;

import net.betsafeapp.android.data.source.local.BankRollLocalDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tyln on 17/01/2017.
 */

@Module
public class BankRollRepositoryModule {
    public BankRollRepositoryModule() {
        // Empty Constructor
    }

    @Singleton
    @Provides
    @Local
    BankRollLocalDataSource provideSafeLocalDataSource(@NonNull Context context) {
        return new BankRollLocalDataSource(context);
    }
}
