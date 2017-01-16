package net.betsafeapp.android;

import android.support.annotation.Nullable;

/**
 * Created by tyln on 16/01/2017.
 */

public interface BaseView<T extends BasePresenter> {
    void setPresenter(T presenter);

    void alert(@Nullable String message);
}
