package net.betsafeapp.android.data.source;

import net.betsafeapp.android.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

/**
 * Created by tyln on 17/01/2017.
 */
@Singleton
@Component(modules = {SafeRepositoryModule.class, ApplicationModule.class})
public interface SafeRepositoryComponent {
    SafeRepository getSafeRepository();
}
