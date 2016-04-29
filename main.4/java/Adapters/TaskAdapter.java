package Adapters;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.Fragment;

import Tabs.InProgress;
import Tabs.JobDone;

/**
 * Created by sneh on 23/10/15.
 */
public class TaskAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[] { "In Progress", "Job Done" };
    private Context con;
    public TaskAdapter(FragmentManager fm,Context context) {
        super(fm);
        con=context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        if(position == 0)                // if the position is 0 we are returning the First tab
        {
            InProgress tab1 = new InProgress();
            tab1.getContext(con);
            return tab1;
        }
        else                             // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
        {
            JobDone tab2 = new JobDone();
            tab2.getContext(con);
            return tab2;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
