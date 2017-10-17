package command_e.androidintensive.itplace.simbirsoft.weatherwidget.presenter;

import android.util.Log;

import command_e.androidintensive.itplace.simbirsoft.weatherwidget.model.Model;
import command_e.androidintensive.itplace.simbirsoft.weatherwidget.realm.model.Day;
import command_e.androidintensive.itplace.simbirsoft.weatherwidget.view.activities.interfaces.FragmentView;
import io.realm.RealmResults;

/**
 * Created by artemqa on 09.10.2017.
 */

public class FragmentsPresenter {
    private static final String LOG = "MyLog";

    private FragmentView view;
    private Model model;

    public FragmentsPresenter(Model model){
        Log.d(LOG,"FragmentsPresenter получил model");
        this.model = model;
    }

    public void attachView(FragmentView view){

        Log.d(LOG,"FragmentsPresenter получил view" + view );
        this.view = view;
    }
    public void detachView(){
        Log.d(LOG,"FragmentsPresenter отвязал view");
        this.view = null;
    }

    public void viewIsReady( int fragmentType){
        Log.d(LOG,"Загрузка данных из бд во view при открытии приложения ");
        loadDays(fragmentType);
    }

    public void add( int fragmentType ){
        Log.d(LOG,"FragmentsPresenter говорит model добавить данные");
        model.addDays(new Model.CompleteCallback(){
        @Override
            public void onComplete(){
            Log.d(LOG,"Сработал callback для обновления данных после добавления");
                loadDays(fragmentType);

        }
    });
    }
    public void clear(int fragmentType){
        Log.d(LOG,"FragmentsPresenter говорит model очистить данные");
        model.clearDays(new Model.CompleteCallback(){
            @Override
            public void onComplete(){
                Log.d(LOG,"Сработал callback для обновления данных после добавления");
                loadDays(fragmentType);
            }
        });
    }


    private void loadDays(int fragmentType){

        Log.d(LOG,"FragmentsPresenter говорит model дать данные для fragmenta");
        model.loadDays(fragmentType , new Model.LoadCallback(){
            @Override
            public void onLoad(RealmResults<Day> daysList){
                Log.d(LOG,"ссылка на привязанную view :" + view);
                view.showDays(daysList);
            }
        });
    }



}
