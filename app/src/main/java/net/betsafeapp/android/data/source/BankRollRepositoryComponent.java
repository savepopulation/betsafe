package net.betsafeapp.android.data.source;

import net.betsafeapp.android.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by tyln on 17/01/2017.
 */
@Singleton
@Component(modules = {BankRollRepositoryModule.class, ApplicationModule.class})
public interface BankRollRepositoryComponent {
    BankRollRepository getBankRollRepository();
}
