package com.example.phisicscatalog.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.DialogFragment;

import com.example.phisicscatalog.R;

public class DialogFragmentForShowInfo extends DialogFragment {

    private String title;

    private static final String POSITIVE_BUTTON = "Ok";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        /*
        этот метод не используется для создание диалогового акна используется статический метод getDialog
         */
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        return alertDialog.setTitle(title)
                .setView(R.layout.my_dialog_fragment_part)
                .setPositiveButton(POSITIVE_BUTTON, null)
                .create();
    }

    public static AlertDialog getDialog(Activity activity, String title){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        return builder.setTitle(title)
                .setView(R.layout.my_dialog_fragment_part)
                .setPositiveButton(POSITIVE_BUTTON, null)
                .create();
    }


}
