package net.betsafeapp.android.addbet;

import android.support.annotation.NonNull;

import net.betsafeapp.android.data.source.BankRollRepositoryComponent;
import net.betsafeapp.android.util.FragmentScoped;

import dagger.Component;

/**
 * Created by tyln on 26/01/2017.
 */
@FragmentScoped
@Component(dependencies = BankRollRepositoryComponent.class, modules = AddBetPresenterModule.class)
interface AddBetComponent {
    void inject(@NonNull AddBetActivity activity);
}
