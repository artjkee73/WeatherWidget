package command_e.androidintensive.itplace.simbirsoft.weatherwidget.view.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;
import command_e.androidintensive.itplace.simbirsoft.weatherwidget.R;
import command_e.androidintensive.itplace.simbirsoft.weatherwidget.model.Model;
import command_e.androidintensive.itplace.simbirsoft.weatherwidget.presenter.Presenter;
import command_e.androidintensive.itplace.simbirsoft.weatherwidget.realm.model.Day;
import command_e.androidintensive.itplace.simbirsoft.weatherwidget.view.activities.interfaces.View;
import command_e.androidintensive.itplace.simbirsoft.weatherwidget.view.adapters.ViewPagerAdapter;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements View {
    private static final String LOG = "MyLog";
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.viewpager) ViewPager viewPager;
    @BindView(R.id.tablayout) TabLayout  tabLayout;
    @BindView(R.id.btn_add_items) Button btnAddItems;
    @BindView(R.id.btn_drop_db) Button btnDropDb;

    @BindString(R.string.first_tab) String titleWeek;
    @BindString(R.string.second_tab) String titleMonth;

    Presenter presenter;
    Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG,"onCreate");
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();

    }

    private void init(){
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setupViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);

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
        adapter.addTitleFragment(titleWeek);
        adapter.addTitleFragment(titleMonth);
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

    @OnClick(R.id.btn_add_items)
    void onClickBtnAddItems() {
        presenter.add();
        Log.d(LOG,"Созданы фейковые записи");
    }

    @OnClick(R.id.btn_drop_db)
    void onClickBtnDropItems() {
        presenter.clear();
        Log.d(LOG,"Записи удалены");
    }

    public void showDays(RealmResults<Day> daysList){
        Log.d(LOG," переданные из model данные пришли во view(MainActivity) " + daysList);
    }
}
