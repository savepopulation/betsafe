package net.betsafeapp.android.data.source.local;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import net.betsafeapp.android.data.source.SafeDataSource;

import javax.inject.Singleton;
import javax.sql.DataSource;

/**
 * Created by tyln on 17/01/2017.
 */

@Singleton
public class SafeLocalDataSource implements SafeDataSource {
    @NonNull
    private Context mContext;

    public SafeLocalDataSource(@NonNull Context context) {
        this.mContext = context;
    }
}
