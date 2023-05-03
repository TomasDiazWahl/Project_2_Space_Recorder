package Utils;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.Toast;

import io.github.muddz.styleabletoast.StyleableToast;

public class Toaster {

    public static void showToast(Context context, String text, int color){

        new StyleableToast.Builder(context)
                .text(text).textColor(Color.BLACK).textSize(20f).backgroundColor(color)
                .gravity(Gravity.TOP).length(Toast.LENGTH_SHORT).show();

        /*new StyleableToast.Builder(context)
                .text(text).textColor(Color.BLACK).textSize(20f).backgroundColor(Color.WHITE)
                .gravity(Gravity.TOP).length(Toast.LENGTH_LONG).show();*/

    }
}
