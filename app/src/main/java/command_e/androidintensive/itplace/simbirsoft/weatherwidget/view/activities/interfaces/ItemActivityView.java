package command_e.androidintensive.itplace.simbirsoft.weatherwidget.view.activities.interfaces;

import command_e.androidintensive.itplace.simbirsoft.weatherwidget.realm.model.Day;
import io.realm.RealmResults;

/**
 * Created by artemqa on 18.10.2017.
 */

public interface ItemActivityView {
    void showClickedDay(Day day);
}
