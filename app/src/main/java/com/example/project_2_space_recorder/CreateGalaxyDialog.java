package com.example.project_2_space_recorder;

import android.app.Dialog;
import android.content.Context;

public class CreateGalaxyDialog extends Dialog {

    public CreateGalaxyDialog(Context context) {
        super(context);
        setContentView(R.layout.create_galaxy_dialog);

        setTitle("Create your Galaxy");

        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }
}