package command_e.androidintensive.itplace.simbirsoft.weatherwidget.view.fragments;

/**
 * Created by artemqa on 03.10.2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import command_e.androidintensive.itplace.simbirsoft.weatherwidget.R;
import command_e.androidintensive.itplace.simbirsoft.weatherwidget.realm.model.Day;
import command_e.androidintensive.itplace.simbirsoft.weatherwidget.view.adapters.RecyclerViewAdapter;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class ListFragment extends Fragment {

    private final String LOG = "MyLog";
    Realm realm;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    LinearLayoutManager recyclerViewLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.recycler_view, container, false);
        recyclerView = (RecyclerView) viewGroup.findViewById(R.id.recyclerView);
        init();

        return viewGroup;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.removeAllChangeListeners();
    }

    public void init(){
        recyclerViewLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(getContext(), findAllItems());
        recyclerView.setAdapter(recyclerViewAdapter);

//        refreshRecycleViewOnChangeRealmResult();//обновление данных в RecyclerView
                                                // при изменении выборки запроса вывода всех записей
    }

    public RealmResults<Day> findAllItems() {
        Log.d(LOG, "Произошла выборка данных из бд");
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Day.class).findAll();
    }

    public void showDays( RealmResults<Day> daysList){
        Log.d(LOG,"данне пришли из MainActivity во fragment, передаем их адаптеру");
        recyclerViewAdapter.setData(daysList);
    }

    private void refreshRecycleViewOnChangeRealmResult() {
        realm = Realm.getDefaultInstance();
        RealmResults<Day> results = realm.where(Day.class).findAll();

        results.addChangeListener(new RealmChangeListener() {
            @Override
            public void onChange(Object o) {
                recyclerViewAdapter.notifyDataSetChanged();
                Log.d(LOG, "Список обновлён");
            }
        });
    }
}


