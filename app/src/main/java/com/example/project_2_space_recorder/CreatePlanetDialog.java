package com.example.project_2_space_recorder;

import android.app.Dialog;
import android.content.Context;

public class CreatePlanetDialog extends Dialog {

    public CreatePlanetDialog(Context context) {
        super(context);
        setContentView(R.layout.create_planet_dialog);

        setTitle("Create your planet");

        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }
}