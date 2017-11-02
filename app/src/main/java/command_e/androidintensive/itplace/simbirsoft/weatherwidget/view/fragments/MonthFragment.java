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
import android.widget.Button;
import command_e.androidintensive.itplace.simbirsoft.weatherwidget.R;
import command_e.androidintensive.itplace.simbirsoft.weatherwidget.model.Model;
import command_e.androidintensive.itplace.simbirsoft.weatherwidget.presenter.FragmentsPresenter;
import command_e.androidintensive.itplace.simbirsoft.weatherwidget.realm.model.Day;
import command_e.androidintensive.itplace.simbirsoft.weatherwidget.view.fragments.interfaces.FragmentView;
import command_e.androidintensive.itplace.simbirsoft.weatherwidget.view.adapters.RecyclerViewAdapter;
import io.realm.Realm;
import io.realm.RealmResults;

public class MonthFragment extends Fragment implements FragmentView {

    private static final int MonthFragment = 1;
    private static final String LOG = "MyLog";
    Realm realm;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    LinearLayoutManager recyclerViewLayoutManager;
    FragmentsPresenter presenter;

    Button btnAddItems;
    Button btnDropDb;

    public static MonthFragment newInstance(String title) {
        Bundle args = new Bundle();
        args.putString("title", title);
        MonthFragment fragment = new MonthFragment();
        fragment.setArguments(args);
        Log.d(LOG, "Create fragment");
        return new MonthFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG, "onCreate fragment");
        realm = Realm.getDefaultInstance();
        Model model = new Model(realm);
        presenter = new FragmentsPresenter(model);
        Log.d(LOG, "отдаю ссылку на фрагмент в презентер" + this);
        presenter.attachView(this);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(LOG, "onCreateView fragment");

        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.recycler_view, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        recyclerViewLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(getContext());
        recyclerView.setAdapter(recyclerViewAdapter);

        btnAddItems = (Button) v.findViewById(R.id.btn_add_items);
        btnAddItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOG,"Созданы фейковые записи");
                presenter.add(MonthFragment);
            }
        });

        btnDropDb = (Button) v.findViewById(R.id.btn_drop_db);
        btnDropDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOG,"Созданы фейковые записи");
                presenter.clear(MonthFragment);

            }
        });


        presenter.viewIsReady(MonthFragment);
        return v;
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
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.removeAllChangeListeners();
        presenter.detachView();
    }

    public void showDays(RealmResults<Day> daysList) {
        Log.d(LOG, "данные пришли из presenter во fragment, передаем их адаптеру" + daysList );
        recyclerViewAdapter.setData(daysList);
    }
}


