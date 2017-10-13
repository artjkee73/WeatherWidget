package command_e.androidintensive.itplace.simbirsoft.weatherwidget.presenter;

import android.util.Log;

import command_e.androidintensive.itplace.simbirsoft.weatherwidget.model.Model;
import command_e.androidintensive.itplace.simbirsoft.weatherwidget.realm.model.Day;
import command_e.androidintensive.itplace.simbirsoft.weatherwidget.view.activities.interfaces.View;
import io.realm.RealmResults;

/**
 * Created by artemqa on 09.10.2017.
 */

public class Presenter {
    private static final String LOG = "MyLog";;

    private View view;
    private Model model;

    public Presenter(Model model){
        Log.d(LOG,"Presenter получил model");
        this.model = model;
    }

    public void attachView( View view){

        Log.d(LOG,"Presenter получил view");
        this.view = view;
    }
    public void detachView(){
        Log.d(LOG,"Presenter отвязал view");
        this.view = null;
    }

    public void viewIsReady(){
        Log.d(LOG,"Загрузка данных из бд во view при открытии приложения ");
        loadDays();
    }

    public void add(){
        Log.d(LOG,"Presenter говорит model добавить данные");
        model.addDays(new Model.CompleteCallback(){
        @Override
            public void onComplete(){
            Log.d(LOG,"Сработал callback для обновления данных после добавления");
                loadDays();

        }
    });
    }
    public void clear(){
        Log.d(LOG,"Presenter говорит model очистить данные");
        model.clearDays(new Model.CompleteCallback(){
            @Override
            public void onComplete(){
                loadDays();
            }
        });
    }


    public void loadDays(){
        Log.d(LOG,"Presenter говорит model дать данные");
        model.loadDays(new Model.LoadCallback(){
            @Override
            public void onLoad(RealmResults<Day> daysList){
                Log.d(LOG,"Presenter говорит view показать данные переданные моделью");
                view.showDays(daysList);
            }
        });
    }



}
