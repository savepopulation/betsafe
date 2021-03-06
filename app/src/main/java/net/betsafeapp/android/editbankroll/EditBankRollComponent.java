package net.betsafeapp.android.editbankroll;

import android.support.annotation.NonNull;

import net.betsafeapp.android.data.source.BankRollRepositoryComponent;
import net.betsafeapp.android.util.FragmentScoped;

import dagger.Component;

/**
 * Created by tyln on 22/03/2017.
 */

@FragmentScoped
@Component(dependencies = BankRollRepositoryComponent.class, modules = EditBankRollModule.class)
interface EditBankRollComponent {
    void inject(@NonNull EditBankRollActivity activity);
}
