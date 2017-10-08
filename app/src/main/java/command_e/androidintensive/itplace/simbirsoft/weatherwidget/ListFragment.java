package command_e.androidintensive.itplace.simbirsoft.weatherwidget;

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

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class ListFragment extends Fragment {

    private final String LOG = "MyLog";
    Realm realm;
    RecyclerView recyclerView;
    MyAdapter recyclerViewAdapter;
    LinearLayoutManager recyclerViewLayoutManager;

    public ListFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.recycler_view, container, false);
        recyclerView = (RecyclerView) viewGroup.findViewById(R.id.recyclerView);

        recyclerViewLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        recyclerViewAdapter = new MyAdapter(getContext(), findAllItems());
        recyclerView.setAdapter(recyclerViewAdapter);

        refreshRecycleViewOnChangeRealmResult();//обновление данных в RecyclerView
        // при изменении выборки запроса вывода всех записей

        return viewGroup;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.removeAllChangeListeners();
    }


    public RealmResults<Item> findAllItems() {
        Log.d(LOG, "Произошла выборка данных из бд");
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Item.class).findAll();
    }

    private void refreshRecycleViewOnChangeRealmResult() {
        realm = Realm.getDefaultInstance();
        RealmResults<Item> results = realm.where(Item.class).findAll();

        results.addChangeListener(new RealmChangeListener() {
            @Override
            public void onChange(Object o) {
                recyclerViewAdapter.notifyDataSetChanged();
                Log.d(LOG, "Список обновлён");
            }
        });
    }
}


