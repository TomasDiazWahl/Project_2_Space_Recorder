package com.example.project_2_space_recorder;

import android.app.Dialog;
import android.content.Context;
import android.widget.EditText;

public class CreatePlanetDialog extends Dialog {

    private EditText editText1;
    private EditText editText2;

    public CreatePlanetDialog(Context context) {
        super(context);
        setContentView(R.layout.create_planet_dialog);

        setTitle("My Dialog");

        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }
}