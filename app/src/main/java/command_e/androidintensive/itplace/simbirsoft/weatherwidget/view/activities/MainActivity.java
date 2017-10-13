package command_e.androidintensive.itplace.simbirsoft.weatherwidget.view.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import command_e.androidintensive.itplace.simbirsoft.weatherwidget.R;
import command_e.androidintensive.itplace.simbirsoft.weatherwidget.model.Model;
import command_e.androidintensive.itplace.simbirsoft.weatherwidget.presenter.Presenter;
import command_e.androidintensive.itplace.simbirsoft.weatherwidget.realm.model.Day;
import command_e.androidintensive.itplace.simbirsoft.weatherwidget.view.activities.interfaces.View;
import command_e.androidintensive.itplace.simbirsoft.weatherwidget.view.adapters.ViewPagerAdapter;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements android.view.View.OnClickListener,View {
    private static final String LOG = "MyLog";
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    Realm realm;
    Button btnAddItems,btnDropDb;
    Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG,"onCreate");
        setContentView(R.layout.activity_main);
        init();

    }

    private void init(){

        Log.d(LOG,"init(main activity)");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);

        btnAddItems = (Button) findViewById(R.id.btn_add_items);
        btnAddItems.setOnClickListener(this);

        btnDropDb = (Button) findViewById(R.id.btn_drop_db);
        btnDropDb.setOnClickListener(this);

        Realm.init(this);
        realm = Realm.getDefaultInstance();

        Model model = new Model(realm);
        presenter = new Presenter(model);
        presenter.attachView(this);
        presenter.viewIsReady();
    }

    private void setupViewPager(ViewPager viewPager) {
        Log.d(LOG,"setupViewPager");
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addTitleFragment(getString(R.string.first_tab));
        adapter.addTitleFragment(getString(R.string.second_tab));
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        Log.d(LOG,"onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(LOG,"onResume");
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!realm.isClosed()) {
            realm.close();
        }
        presenter.detachView();
    }

    @Override
    public void onClick(android.view.View v) {
        switch (v.getId()) {
            case R.id.btn_add_items:

                presenter.add();
                Log.d(LOG,"Созданы фейковые записи");

                break;
            case R.id.btn_drop_db:
                presenter.clear();
                Log.d(LOG,"Записи удалены");
                break;
        }
    }
    public void showDays(RealmResults<Day> daysList){
        Log.d(LOG," переданные из model данные пришли во view(MainActivity) " + daysList);
    }
}