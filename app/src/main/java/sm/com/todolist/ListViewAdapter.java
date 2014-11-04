package sm.com.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {

    private Context c;
    private List<String> ourList;

    public ListViewAdapter(Context c, List<String> ojects){
        this.c = c;
        this.ourList = ojects;
    }

    @Override
    public int getCount() {
        return ourList.size();
    }

    @Override
    public Object getItem(int i) {
        return ourList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater myInflater = (LayoutInflater)
                c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null){
            view = myInflater.inflate(R.layout.list_item, null);
        }

        TextView itemTextView = (TextView) view.findViewById(R.id.item_textview);
        itemTextView.setText(getItem(i).toString());

        return view;
    }
}
