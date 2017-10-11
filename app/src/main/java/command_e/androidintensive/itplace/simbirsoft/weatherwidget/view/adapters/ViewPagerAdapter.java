package command_e.androidintensive.itplace.simbirsoft.weatherwidget.view.adapters;

/**
 * Created by artemqa on 03.10.2017.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import command_e.androidintensive.itplace.simbirsoft.weatherwidget.view.fragments.ListFragment;


import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private static final String LOG = "MyLog";

    private final static int NUM_ITEMS = 2;

    private final List<String> listTitle = new ArrayList<>();

    private static ListFragment m1stFragment;
    private static ListFragment m2ndFragment;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Log.d(LOG,"Создали первый фрагмент");
                return  ListFragment.newInstance(listTitle.get(position));

            case 1:
                Log.d(LOG,"Создали второй фрагмент");
                return ListFragment.newInstance(listTitle.get(position));
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listTitle.get(position);
    }

    public void addTitleFragment(String title) {
        Log.d(LOG," Присвоили title  " + title);
        listTitle.add(title);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment createdFragment = (Fragment) super.instantiateItem(container, position);
        switch (position) {
            case 0:

                m1stFragment = (ListFragment) createdFragment;
                Log.d(LOG,"Создали ссылку на первый фрашмент фрагмент" + m1stFragment);
                break;
            case 1:
                m2ndFragment = (ListFragment) createdFragment;
                Log.d(LOG,"Создали ссылку на второй фрагмент " + m2ndFragment);
                break;
        }
        return createdFragment;

    }
    public static Object getFragmentByPosition(int position) {
        switch (position) {
            case 0:
                Log.d(LOG,"Отдали ссылку на первый фрагмент " + m1stFragment);
            if (m1stFragment != null) {
                return m1stFragment;
            }
            return null;
            case 1:
                Log.d(LOG,"Отдали ссылку на второй фрагмент " + m2ndFragment);
            if (m2ndFragment != null) {
                return m2ndFragment;
            }
            return null;
        }
        return null;
    }
}


