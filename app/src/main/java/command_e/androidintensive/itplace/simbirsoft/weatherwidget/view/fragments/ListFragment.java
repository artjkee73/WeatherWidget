package command_e.androidintensive.itplace.simbirsoft.weatherwidget.view.fragments;

/**
 * Created by artemqa on 03.10.2017.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import command_e.androidintensive.itplace.simbirsoft.weatherwidget.R;
import command_e.androidintensive.itplace.simbirsoft.weatherwidget.model.Model;
import command_e.androidintensive.itplace.simbirsoft.weatherwidget.presenter.Presenter;
import command_e.androidintensive.itplace.simbirsoft.weatherwidget.realm.model.Day;
import command_e.androidintensive.itplace.simbirsoft.weatherwidget.view.adapters.RecyclerViewAdapter;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class ListFragment extends Fragment implements command_e.androidintensive.itplace.simbirsoft.weatherwidget.view.activities.interfaces.View
{

    private static final String LOG = "MyLog";
    Realm realm;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    LinearLayoutManager recyclerViewLayoutManager;
    Presenter presenter;

    public static ListFragment newInstance(String title) {
        Bundle args = new Bundle();
        args.putString("title", title);
        ListFragment fragment = new ListFragment();
        fragment.setArguments(args);
        Log.d(LOG, "Create fragment");
        return new ListFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(LOG, "onCreate fragment");

        realm = Realm.getDefaultInstance();
        Model model = new Model(realm);
        presenter = new Presenter(model);
        presenter.attachView(this);
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(LOG, "onCreateView fragment");
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.recycler_view, container, false);
        recyclerView = (RecyclerView) viewGroup.findViewById(R.id.recyclerView);
        init();

        return viewGroup;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d(LOG, "onActivityCreated fragment");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.d(LOG, "onStart fragment");
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.removeAllChangeListeners();
        presenter.detachView();
    }

    public void init() {
        recyclerViewLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(getContext(), findAllItems());
        recyclerView.setAdapter(recyclerViewAdapter);
        presenter.viewIsReady();

        refreshRecycleViewOnChangeRealmResult();//обновление данных в RecyclerView
//         при изменении выборки запроса вывода всех записей
    }


    public RealmResults<Day> findAllItems() {
        Log.d(LOG, "Произошла выборка данных из бд");
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Day.class).findAll();
    }

    public void showDays(RealmResults<Day> daysList) {
        Log.d(LOG, "данные пришли из presenter во fragment, передаем их адаптеру" + daysList );
        recyclerViewAdapter.setData(daysList);
    }

    private void refreshRecycleViewOnChangeRealmResult() {
        realm = Realm.getDefaultInstance();
        RealmResults<Day> results = realm.where(Day.class).findAll();

        results.addChangeListener(new RealmChangeListener() {
            @Override
            public void onChange(Object o) {
                presenter.viewIsReady();
                Log.d(LOG, "Список обновлён");
            }
        });
    }
}


