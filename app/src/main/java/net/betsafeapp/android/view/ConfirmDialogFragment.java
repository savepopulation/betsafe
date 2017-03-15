package net.betsafeapp.android.view;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import net.betsafeapp.android.Constants;
import net.betsafeapp.android.util.ValidationUtil;

/**
 * Created by tyln on 15/03/2017.
 */

public class ConfirmDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {
    public static final String TAG_DIALOG_BAKNROLL_DELETE = "DeleteBankrollDialogFragment";
    public static final String TAG_DIALOG_BANKROLL_CLOSE = "CloseBankrollDialogFragment";

    private static final String BUNDLE_TITLE = "title";
    private static final String BUNDLE_MESSAGE = "message";
    private static final String BUNDLE_BUTTON_POSITIVE = "button_positive";
    private static final String BUNDLE_BUTTON_NEGATIVE = "button_negavite";
    private static final String BUNDLE_CANCELABLE = "cancelable";

    @Nullable
    private String mTitle;

    @NonNull
    private String mMessage;

    @NonNull
    private String mPositiveButtonText;

    @Nullable
    private String mNegativeButtonText;

    private boolean mIsCancelable;

    @NonNull
    public static ConfirmDialogFragment newInstance(@Nullable String title,
                                                    @NonNull String message,
                                                    @NonNull String positiveButtonText,
                                                    @Nullable String negativeButtonText,
                                                    boolean isCancelable) {
        final ConfirmDialogFragment instance = new ConfirmDialogFragment();
        final Bundle args = new Bundle();
        args.putString(BUNDLE_TITLE, title);
        args.putString(BUNDLE_MESSAGE, message);
        args.putString(BUNDLE_BUTTON_POSITIVE, positiveButtonText);
        args.putString(BUNDLE_BUTTON_NEGATIVE, negativeButtonText);
        args.putBoolean(BUNDLE_CANCELABLE, isCancelable);
        instance.setArguments(args);
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            mTitle = args.getString(BUNDLE_TITLE);
            mMessage = args.getString(BUNDLE_MESSAGE, "");
            mPositiveButtonText = args.getString(BUNDLE_BUTTON_POSITIVE, "");
            mNegativeButtonText = args.getString(BUNDLE_BUTTON_NEGATIVE);
            mIsCancelable = args.getBoolean(BUNDLE_CANCELABLE, true);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

        if (!ValidationUtil.isNullOrEmpty(mTitle)) {
            alertDialogBuilder.setTitle(mTitle);
        }

        if (!ValidationUtil.isNullOrEmpty(mMessage)) {
            alertDialogBuilder.setMessage(mMessage);
        }

        if (!ValidationUtil.isNullOrEmpty(mPositiveButtonText)) {
            alertDialogBuilder.setPositiveButton(mPositiveButtonText, this);
        }

        if (!ValidationUtil.isNullOrEmpty(mNegativeButtonText)) {
            alertDialogBuilder.setNegativeButton(mNegativeButtonText, this);
        }

        alertDialogBuilder.setCancelable(mIsCancelable);

        return alertDialogBuilder.create();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        dismiss();
        switch (i) {
            case DialogInterface.BUTTON_POSITIVE:
                confirm();
                break;

            case DialogInterface.BUTTON_NEGATIVE:
                cancel();
                break;
        }
    }

    private void confirm() {
        if (getParentFragment() != null && getParentFragment() instanceof ConfirmListener) {
            ((ConfirmListener) getParentFragment()).onConfirm();
        }
    }

    private void cancel() {
        if (getParentFragment() != null && getParentFragment() instanceof ConfirmListener) {
            ((ConfirmListener) getParentFragment()).onCancel();
        }
    }

    public interface ConfirmListener {
        void onConfirm();

        void onCancel();
    }
}
