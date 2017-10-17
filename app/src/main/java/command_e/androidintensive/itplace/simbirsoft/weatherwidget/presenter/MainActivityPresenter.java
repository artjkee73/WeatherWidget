package command_e.androidintensive.itplace.simbirsoft.weatherwidget.presenter;

import android.util.Log;

import command_e.androidintensive.itplace.simbirsoft.weatherwidget.model.Model;
import command_e.androidintensive.itplace.simbirsoft.weatherwidget.view.activities.interfaces.MainActivityView;

/**
 * Created by artemqa on 17.10.2017.
 */

public class MainActivityPresenter {
    private static final String LOG = "MyLog";
    private MainActivityView view;
    private Model model;

    public MainActivityPresenter(Model model){
        Log.d(LOG,"FragmentsPresenter получил model");
        this.model = model;
    }

    public void attachView(MainActivityView view){

        Log.d(LOG,"FragmentsPresenter получил view" + view );
        this.view = view;
    }

    public void detachView(){
        Log.d(LOG,"FragmentsPresenter отвязал view");
        this.view = null;
    }
    public void viewIsReady( boolean portraitOrientation){

    }
}
