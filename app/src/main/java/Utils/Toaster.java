package Utils;

import android.content.Context;
import android.widget.Toast;

public class Toaster {

    public static Toast newToast(Context context, int layout){
        return new Toast(context);
    }
}
