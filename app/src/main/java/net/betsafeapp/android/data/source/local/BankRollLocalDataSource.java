package net.betsafeapp.android.data.source.local;

import android.content.Context;
import android.support.annotation.NonNull;

import net.betsafeapp.android.data.source.BankRollDataSource;

import javax.inject.Singleton;

/**
 * Created by tyln on 17/01/2017.
 */

@Singleton
public class BankRollLocalDataSource implements BankRollDataSource {
    @NonNull
    private Context mContext;

    public BankRollLocalDataSource(@NonNull Context context) {
        this.mContext = context;
    }
}
