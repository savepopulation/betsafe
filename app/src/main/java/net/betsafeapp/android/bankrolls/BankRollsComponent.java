package net.betsafeapp.android.bankrolls;

import android.support.annotation.NonNull;

import net.betsafeapp.android.data.source.BankRollRepositoryComponent;
import net.betsafeapp.android.util.FragmentScoped;

import dagger.Component;

/**
 * Created by tyln on 19/01/2017.
 */

@FragmentScoped
@Component(dependencies = BankRollRepositoryComponent.class, modules = BankRollsPresenterModule.class)
interface BankRollsComponent {
    void inject(@NonNull BankRollsActivity activity);
}
