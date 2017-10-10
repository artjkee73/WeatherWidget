package command_e.androidintensive.itplace.simbirsoft.weatherwidget.model;

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
   public Model (Realm realm){
        this.realm = realm;
    }

    public void loadDays(LoadCallback loadCallback) {
        RealmResults<Day> daysList = realm.where(Day.class).findAll();
        loadCallback.onLoad(daysList);
    }

    public void addDays( CompleteCallback callback){

        final List<Day> daysList = new ArrayList<>();

        for(int i= 1;i<11;i++) {
            Day day = new Day();
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
