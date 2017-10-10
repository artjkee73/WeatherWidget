package command_e.androidintensive.itplace.simbirsoft.weatherwidget.utils;

/**
 * Created by artemqa on 10.10.2017.
 */

public class Util {
    public static String getFragmentTag(int viewPagerId, int fragmentPosition)
    {
        return "android:switcher:" + viewPagerId + ":" + fragmentPosition;
    }
}
