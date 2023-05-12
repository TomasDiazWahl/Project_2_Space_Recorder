package com.example.project_2_space_recorder;

import android.app.Dialog;
import android.content.Context;

public class ConfirmDialog extends Dialog {

    public ConfirmDialog(Context context) {
        super(context);
        setContentView(R.layout.confirm_dialog);

        setTitle("Confirm");

        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }
}