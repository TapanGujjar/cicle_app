package Tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.sneh.myapplication.R;

import Adapters.InProgressListAdapter;
import Adapters.TodaysTaskListAdapter;

/**
 * Created by sneh on 5/11/15.
 */
public class TodaysDone extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_view, container, false);

        String[] button = {"Do stuff","Do stuff","Do stuff"};
        ListView lv = (ListView) view.findViewById(R.id.listView);
        lv.setAdapter(new TodaysTaskListAdapter(getContext(), button));

        return view;
    }
}

