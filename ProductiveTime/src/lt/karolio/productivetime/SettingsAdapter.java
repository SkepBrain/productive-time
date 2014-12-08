package lt.karolio.productivetime;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SettingsAdapter extends BaseAdapter {

    public LayoutInflater inflater;
    public List<String> data = new ArrayList<String>();

    public SettingsAdapter(Context context) {
        data.add("Pomodoro");
        data.add("Short Break");
        data.add("Long Break");
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public String getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(android.R.layout.simple_list_item_2, parent, false);
        }

        TextView primary = (TextView)convertView.findViewById(android.R.id.text1);
        TextView secondary = (TextView)convertView.findViewById(android.R.id.text2);

        String text = data.get(position);

        primary.setText(text);
        secondary.setText("hi" + " min");

        return convertView;
    }
}
