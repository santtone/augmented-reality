package fi.fifthelement.augmentedreality.utils;


import android.content.Context;
import android.os.Vibrator;

public class Device {

    private static int defaultVibrationTime = 50;

    public Device(){}

    public static void vibrate(Context context){
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(defaultVibrationTime);
    }
}
