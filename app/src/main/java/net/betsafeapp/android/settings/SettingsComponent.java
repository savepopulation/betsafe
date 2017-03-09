package net.betsafeapp.android.settings;

import android.app.Activity;
import android.support.annotation.NonNull;

import net.betsafeapp.android.util.FragmentScoped;

import dagger.Component;
import dagger.Module;

/**
 * Created by tyln on 09/03/2017.
 */

@FragmentScoped
@Component(modules = SettingsPresenterModule.class)
interface SettingsComponent {
    void inject(@NonNull SettingsActivity activity);
}
