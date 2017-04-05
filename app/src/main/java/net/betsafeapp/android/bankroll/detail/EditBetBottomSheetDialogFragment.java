package net.betsafeapp.android.bankroll.detail;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;

import net.betsafeapp.android.R;

/**
 * Created by tyln on 05/04/2017.
 */

public final class EditBetBottomSheetDialogFragment extends BottomSheetDialogFragment {
    public static final String TAG = "EditBetBottomSheetDialogFragment";

    @NonNull
    public static EditBetBottomSheetDialogFragment newInstance() {
        return new EditBetBottomSheetDialogFragment();
    }

    @NonNull
    private BottomSheetBehavior.BottomSheetCallback mBottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            // Empty method
        }
    };

    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);

        final View contentView = View.inflate(getActivity(), R.layout.fragment_dialog_edit_bet, null);
        dialog.setContentView(contentView);

        final CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        final CoordinatorLayout.Behavior behavior = params.getBehavior();

        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetCallback);
        }
    }
}
