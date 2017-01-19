package net.betsafeapp.android.data.source;

import android.support.annotation.NonNull;

import net.betsafeapp.android.data.source.SafeDataSource;
import net.betsafeapp.android.data.source.local.SafeLocalDataSource;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by tyln on 17/01/2017.
 */

@Singleton
public class SafeRepository implements SafeDataSource {
    @NonNull
    private SafeLocalDataSource mSafeLocalDataSource;

    @Inject
    SafeRepository(@NonNull @Local SafeLocalDataSource safeLocalDataSource) {
        this.mSafeLocalDataSource = safeLocalDataSource;
    }
}
