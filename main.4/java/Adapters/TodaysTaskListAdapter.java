package Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.sneh.myapplication.Building;
import com.example.sneh.myapplication.R;

/**
 * Created by sneh on 23/10/15.
 */
public class TodaysTaskListAdapter extends BaseAdapter {

    String [] button;
    Context context;
    private static LayoutInflater inflater=null;

    public TodaysTaskListAdapter(Context context, String[] button) {
        this.button = button;
        this.context = context;
        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return button.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder
    {
        Button button;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        final View rowView;
        rowView = inflater.inflate(R.layout.tasks, null);

        holder.button = (RadioButton) rowView.findViewById(R.id.radioButton);
        holder.button.setText(button[position]);

        /*rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, Building.class));
            }
        });*/

        return rowView;
    }
}