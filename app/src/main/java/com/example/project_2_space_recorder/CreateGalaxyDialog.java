/*
 * Authors: Tomas Diaz-Wahl & Alexis Petignat
 * Date: 2023-05-12
 * Purpose: This class creates a dialogue that allows a user to enter fields while creating a
 * new galaxy object.
 * */

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