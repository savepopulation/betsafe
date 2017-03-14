package net.betsafeapp.android;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by tyln on 14/03/2017.
 */

public abstract class BaseDialogFragment extends DialogFragment implements DialogInterface.OnClickListener{

    @StringRes
    protected abstract int getTitleRes();

    @StringRes
    protected abstract int getMessageRes();

    @StringRes
    protected abstract int getPositiveButtonRes();

    @StringRes
    protected abstract int getNegativeButtonRes();

    @StringRes
    protected abstract int getNeutralButtonRes();

    protected abstract boolean getIsCancelable();

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

        if (getTitleRes() != Constants.NO_RES) {
            alertDialogBuilder.setTitle(getString(getTitleRes()));
        }

        if (getMessageRes() != Constants.NO_RES) {
            alertDialogBuilder.setMessage(getString(getMessageRes()));
        }

        if (getPositiveButtonRes() != Constants.NO_RES) {
            alertDialogBuilder.setPositiveButton(getString(getPositiveButtonRes()), this);
        }

        if (getNegativeButtonRes() != Constants.NO_RES) {
            alertDialogBuilder.setNegativeButton(getString(getNegativeButtonRes()), this);
        }

        if (getNeutralButtonRes() != Constants.NO_RES) {
            alertDialogBuilder.setNeutralButton(getString(getNeutralButtonRes()), this);
        }

        alertDialogBuilder.setCancelable(getIsCancelable());

        return alertDialogBuilder.create();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        dismiss();
    }
}
