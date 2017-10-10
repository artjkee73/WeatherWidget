package command_e.androidintensive.itplace.simbirsoft.weatherwidget.view.adapters;

/**
 * Created by artemqa on 03.10.2017.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import command_e.androidintensive.itplace.simbirsoft.weatherwidget.view.fragments.ListFragment;


import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final static int NUM_ITEMS = 2;

    private final List<String> listTitle = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ListFragment();
            case 1:
                return new ListFragment();
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
        listTitle.add(title);
    }

}
