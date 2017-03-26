package net.betsafeapp.android.bankroll;

import android.support.annotation.NonNull;

import net.betsafeapp.android.bankroll.bets.BankRollBetsPresenterModule;
import net.betsafeapp.android.bankroll.detail.BankRollDetailPresenterModule;
import net.betsafeapp.android.data.source.BankRollRepositoryComponent;
import net.betsafeapp.android.util.FragmentScoped;

import dagger.Component;

/**
 * Created by tyln on 02/03/2017.
 */

@FragmentScoped
@Component(dependencies = BankRollRepositoryComponent.class,
        modules = {BankRollDetailPresenterModule.class, BankRollBetsPresenterModule.class})
public interface BankRollComponent {
    void inject(@NonNull BankRollActivity activity);
}
