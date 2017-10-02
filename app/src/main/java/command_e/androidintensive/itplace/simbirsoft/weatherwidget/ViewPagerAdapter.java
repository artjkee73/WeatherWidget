package command_e.androidintensive.itplace.simbirsoft.weatherwidget;

/**
 * Created by artemqa on 03.10.2017.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {


    private final List<Fragment> listFragment = new ArrayList<>();
    private final List<String> listTitle = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listTitle.get(position);
    }

    public void addFragment(Fragment fragment, String title) {
        listFragment.add(fragment);
        listTitle.add(title);
    }

}
