package command_e.androidintensive.itplace.simbirsoft.weatherwidget.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import command_e.androidintensive.itplace.simbirsoft.weatherwidget.R;
import command_e.androidintensive.itplace.simbirsoft.weatherwidget.realm.model.Day;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by artemqa on 09.10.2017.
 */

public class Model {
    private Realm realm;
    private static final int WeekFragment = 0;
    private static final int MonthFragment = 1;
    private static final String LOG = "MyLog";
   public Model (Realm realm){
        this.realm = realm;
    }

    public void loadDays(int fragmentType ,LoadCallback loadCallback) {

        switch (fragmentType){

            case WeekFragment:
                RealmResults<Day> daysListWeek = realm.where(Day.class)
                        .between("id", 1, 7)
                        .findAll();
                loadCallback.onLoad(daysListWeek);
                break;
            case MonthFragment:
                RealmResults<Day> daysListMonth = realm.where(Day.class)
                        .findAll();
                loadCallback.onLoad(daysListMonth);
                break;

        }
    }

    public void addDays( CompleteCallback callback){

        final List<Day> daysList = new ArrayList<>();

        for(int i= 1;i<31;i++) {
            Day day = new Day();
            day.setId(i);
            day.setPictureId(R.drawable.sunny);
            day.setDayOfWeek("Monday");
            day.setDate(i + ".01.2017");
            day.setTemperature("+10");
            day.setWeatherCharacter("Sunny");
            daysList.add(day);
        }

        realm.executeTransaction(new Realm.Transaction(){
            @Override
            public void execute(Realm realm){
                realm.insert(daysList);
                Log.d(LOG,"Model добавил данные в БД");
            }
        });

        callback.onComplete();
    }


    public void clearDays(CompleteCallback callback){

        final RealmResults<Day> results = realm.where(Day.class).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                results.deleteAllFromRealm();
            }
        });
        callback.onComplete();
    }

    public interface CompleteCallback{
        void onComplete();
    }
    public interface LoadCallback{
        void onLoad(RealmResults<Day> daysList);
    }
}
