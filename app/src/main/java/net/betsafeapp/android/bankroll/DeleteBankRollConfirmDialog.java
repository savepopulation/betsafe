package net.betsafeapp.android.bankroll;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import net.betsafeapp.android.BaseDialogFragment;
import net.betsafeapp.android.Constants;
import net.betsafeapp.android.R;

/**
 * Created by tyln on 14/03/2017.
 */

public class DeleteBankRollConfirmDialog extends BaseDialogFragment {
    static final String TAG = "DeleteBankRollDialog";

    @NonNull
    public static DeleteBankRollConfirmDialog newInstance() {
        return new DeleteBankRollConfirmDialog();
    }

    @Override
    protected int getTitleRes() {
        return Constants.NO_RES;
    }

    @Override
    protected int getMessageRes() {
        return R.string.dialog_message_delete_bankroll;
    }

    @Override
    protected int getPositiveButtonRes() {
        return R.string.button_continue;
    }

    @Override
    protected int getNegativeButtonRes() {
        return R.string.button_cancel;
    }

    @Override
    protected int getNeutralButtonRes() {
        return Constants.NO_RES;
    }

    @Override
    protected boolean getIsCancelable() {
        return true;
    }

    @Override
    protected void onButtonClicked(int which) {
        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                // TODO implement deep search for parent
                if (getParentFragment() instanceof DeleteConfirmationDialogListener) {
                    ((DeleteConfirmationDialogListener) getParentFragment()).onDeleteConfirmed();
                }
                break;
        }
    }

    interface DeleteConfirmationDialogListener {
        void onDeleteConfirmed();
    }
}
