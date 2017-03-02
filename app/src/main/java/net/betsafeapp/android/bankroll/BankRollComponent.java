package net.betsafeapp.android.bankroll;

import net.betsafeapp.android.data.source.BankRollRepository;
import net.betsafeapp.android.data.source.BankRollRepositoryComponent;
import net.betsafeapp.android.util.FragmentScoped;

import dagger.Component;

/**
 * Created by tyln on 02/03/2017.
 */

@FragmentScoped
@Component(dependencies = BankRollRepositoryComponent.class, modules = BankRollPresenterModule.class)
interface BankRollComponent {
    void inject(BankRollActivity activity);
}
