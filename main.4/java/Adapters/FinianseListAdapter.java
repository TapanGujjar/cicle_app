package Adapters;

/**
 * Created by sneh on 6/11/15.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sneh.myapplication.R;

import java.util.ArrayList;

/**
 * Created by Himanshu on 11/4/2015.
 */

class Singlerow{
    String  heading;
    String  money;
    String  Curr;
    Singlerow(String heading,String money,String Curr)
    {
        this.Curr=Curr;
        this.heading=heading;
        this.money=money;
    }

}
public class FinianseListAdapter extends BaseAdapter {
    Context c;
    String[]  money;
    String[]  Curr;
    ArrayList<Singlerow> list;

    public FinianseListAdapter(Context c)
    {   this.c=c;
        list=new ArrayList<Singlerow>();
        String[]  heading = {"Equipment Expenses","Animals Expenses","Worker Expenses","Equipment Expenses","Animals Expenses","Worker Expenses"};
        String[]  money={"USD","USD","USD","USD","USD","USD"};
        String[]  Curr={"143434","212121","121212","143434","212121","121212"};
        int n=6;
        for(int i=0;i<n;i++){
            list.add(new Singlerow(heading[i],money[i],Curr[i]));
        }

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    private class ViewHolder{

        TextView heading;
        TextView money;
        TextView currency;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder=new ViewHolder();


        LayoutInflater inflater=(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=inflater.inflate(R.layout.finance_listview, parent, false);
        TextView heading=(TextView)row.findViewById(R.id.finance_listview_heading);
        TextView money=(TextView)row.findViewById(R.id.finance_listview_money);
        TextView currency=(TextView)row.findViewById(R.id.finance_listview_money);
        Singlerow tmp=list.get(position);
        money.setText(tmp.money);
        heading.setText(tmp.heading);
        currency.setText(tmp.Curr);
        return row;
    }
}
