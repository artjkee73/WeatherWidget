package command_e.androidintensive.itplace.simbirsoft.weatherwidget.view.fragments.interfaces;

import command_e.androidintensive.itplace.simbirsoft.weatherwidget.realm.model.Day;
import io.realm.RealmResults;

/**
 * Created by artemqa on 13.10.2017.
 */

public interface FragmentView {
    void showDays(RealmResults<Day> daysList);
}
