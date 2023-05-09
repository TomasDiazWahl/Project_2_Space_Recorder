package com.example.project_2_space_recorder;

import android.app.Dialog;
import android.content.Context;

public class CreateSystemDialog extends Dialog {


    public CreateSystemDialog(Context context) {
        super(context);
        setContentView(R.layout.create_system_dialog);

        setTitle("Create your Solar System");

        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }
}