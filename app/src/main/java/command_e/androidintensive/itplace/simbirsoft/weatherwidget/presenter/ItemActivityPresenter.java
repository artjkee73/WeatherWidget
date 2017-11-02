package command_e.androidintensive.itplace.simbirsoft.weatherwidget.presenter;

import android.util.Log;

import command_e.androidintensive.itplace.simbirsoft.weatherwidget.model.Model;
import command_e.androidintensive.itplace.simbirsoft.weatherwidget.realm.model.Day;
import command_e.androidintensive.itplace.simbirsoft.weatherwidget.view.activities.interfaces.ItemActivityView;

/**
 * Created by artemqa on 18.10.2017.
 */

public class ItemActivityPresenter {
    private static final String LOG = "MyLog";

    private ItemActivityView view;
    private Model model;

    public ItemActivityPresenter(Model model){
        Log.d(LOG,"ItemActivityPresenter получил model");
        this.model = model;
    }

    public void attachView(ItemActivityView view){

        Log.d(LOG,"ItemActivityPresenter получил view" + view );
        this.view = view;
    }
    public void detachView(){
        Log.d(LOG,"ItemActivityPresenter отвязал view");
        this.view = null;
    }
    public void loadDay(String date){

        Log.d(LOG,"ItemActivityPresenter говорит model дать данные для ItemActivity");
        model.loadClickedDay(date , new Model.LoadOneItemCallback(){
            @Override
            public void onLoadDay(Day day){
                Log.d(LOG,"ссылка на привязанную view :" + view);
                view.showClickedDay(day);
            }
        });
    }
}
