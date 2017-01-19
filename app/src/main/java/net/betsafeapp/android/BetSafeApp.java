package net.betsafeapp.android;

import android.app.Application;
import android.support.annotation.NonNull;

import net.betsafeapp.android.data.source.DaggerBankRollRepositoryComponent;
import net.betsafeapp.android.data.source.BankRollRepositoryComponent;
import net.betsafeapp.android.data.source.BankRollRepositoryModule;

/**
 * Created by tyln on 16/01/2017.
 */

public class BetSafeApp extends Application {
    @NonNull
    private BankRollRepositoryComponent mBankRollRepositoryComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mBankRollRepositoryComponent = DaggerBankRollRepositoryComponent.builder()
                .applicationModule(new ApplicationModule((this)))
                .bankRollRepositoryModule(new BankRollRepositoryModule())
                .build();
    }

    @NonNull
    public BankRollRepositoryComponent getBankRollRepositoryComponent() {
        return mBankRollRepositoryComponent;
    }
}
