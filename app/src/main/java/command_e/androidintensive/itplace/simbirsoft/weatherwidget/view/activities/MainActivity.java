package command_e.androidintensive.itplace.simbirsoft.weatherwidget.view.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;

import butterknife.ButterKnife;
import butterknife.BindString;
import butterknife.BindView;
import command_e.androidintensive.itplace.simbirsoft.weatherwidget.R;
import command_e.androidintensive.itplace.simbirsoft.weatherwidget.model.Model;
import command_e.androidintensive.itplace.simbirsoft.weatherwidget.presenter.MainActivityPresenter;
import command_e.androidintensive.itplace.simbirsoft.weatherwidget.utils.Util;
import command_e.androidintensive.itplace.simbirsoft.weatherwidget.view.activities.interfaces.MainActivityView;
import command_e.androidintensive.itplace.simbirsoft.weatherwidget.view.adapters.RecyclerViewAdapter;
import command_e.androidintensive.itplace.simbirsoft.weatherwidget.view.adapters.ViewPagerAdapter;
import command_e.androidintensive.itplace.simbirsoft.weatherwidget.view.fragments.WeekFragment;
import io.realm.Realm;


public class MainActivity extends AppCompatActivity implements MainActivityView{
    private static final String LOG = "MyLog";
    private static final int POSITION_WEEK_FRAGMENT_ON_VIEWPAGER = 0;
    private static final int VIEWPAGER_ID = R.id.viewpager;
    private static final boolean PORTRAIT_ORIENTATION = true;
    private static final boolean LAND_ORIENTATION = false;
    //binding for portrait orientation
    @Nullable @BindView(R.id.viewpager) ViewPager viewPager;
    @Nullable @BindView(R.id.tablayout) TabLayout  tabLayout;

    @Nullable @BindString(R.string.first_tab) String titleWeek;
    @Nullable @BindString(R.string.second_tab) String titleMonth;

    //binding for land orientation
    @Nullable @BindView (R.id.fl_master) FrameLayout frMaster;
    @Nullable @BindView (R.id.fl_detail) FrameLayout frDetail;



    MainActivityPresenter presenter;
    Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG,"onCreate");
        setContentView(R.layout.activity_main);
        Realm.init(this);
        ButterKnife.bind(this);

        if (getOrientation()==PORTRAIT_ORIENTATION){
            initPortrait();
        } else if(getOrientation()==LAND_ORIENTATION){
            initLand();
        }

        realm = Realm.getDefaultInstance();
        Model model = new Model(realm);
        presenter = new MainActivityPresenter(model);
        presenter.attachView(this);
        presenter.viewIsReady(getOrientation());
    }

    private void initPortrait(){
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initLand(){
        FragmentManager fm = getSupportFragmentManager();
        WeekFragment weekFragment = new WeekFragment();
        fm.beginTransaction()
                .add(R.id.fl_master,weekFragment)
                .commit();
    }

    private void setupViewPager(ViewPager viewPager) {
        Log.d(LOG,"setupViewPager");
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addTitleFragment(titleWeek);
        adapter.addTitleFragment(titleMonth);
        viewPager.setAdapter(adapter);
    }

    private boolean getOrientation(){
      return (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
