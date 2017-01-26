package net.betsafeapp.android.addbankroll;

import net.betsafeapp.android.data.source.BankRollRepositoryComponent;
import net.betsafeapp.android.util.FragmentScoped;

import dagger.Component;

/**
 * Created by tyln on 19/01/2017.
 */

@FragmentScoped
@Component(dependencies = BankRollRepositoryComponent.class, modules = AddBankRollModule.class)
interface AddBankRollComponent {
    void inject(AddBankRollActivity activity);
}
