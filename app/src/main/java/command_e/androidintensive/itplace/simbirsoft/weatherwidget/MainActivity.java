package command_e.androidintensive.itplace.simbirsoft.weatherwidget;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String LOG = "MyLog";
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    Realm realm;
    Button btnAddItems;
    Button btnDropDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Realm.init(this);
        realm = Realm.getDefaultInstance();
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
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ListFragment(), getString(R.string.first_tab));
        adapter.addFragment(new ListFragment(), getString(R.string.second_tab));
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!realm.isClosed()) {
            realm.close();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_items:
                Log.d(LOG,"Созданы фейковые записи");
                final List <Item> itemList = new ArrayList<>();

                for(int i= 1;i<11;i++) {
                    Item item = new Item();
                    item.setPictureId(R.drawable.sunny);
                    item.setDayOfWeek("Monday");
                    item.setDate(i + ".01.2017");
                    item.setTemperature("+10");
                    item.setWeatherCharacter("Sunny");
                    itemList.add(item);
                }


                    realm = Realm.getDefaultInstance();
                    realm.executeTransaction(new Realm.Transaction(){
                        @Override
                        public void execute(Realm realm){
                            realm.insert(itemList);
                        }
                    });

                break;
            case R.id.btn_drop_db:
                realm = Realm.getDefaultInstance();
                final RealmResults<Item> results = realm.where(Item.class).findAll();
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        results.deleteAllFromRealm();
                    }
                });

                break;
        }
    }
}
