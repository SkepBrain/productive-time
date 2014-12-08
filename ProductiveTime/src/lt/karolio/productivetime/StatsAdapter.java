package lt.karolio.productivetime;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class StatsAdapter extends BaseAdapter {

    public LayoutInflater inflater;
    public List<DayInfo> info = getDataForDayInfoList();


    public StatsAdapter(Context context) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return info.size();
    }

    @Override
    public DayInfo getItem(int position) {
        return info.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.stats_item, parent, false);
        }

        TextView day = (TextView)convertView.findViewById(R.id.day);
        TextView dayTime = (TextView)convertView.findViewById(R.id.day_time);
        TextView nightTime = (TextView)convertView.findViewById(R.id.night_time);
        TextView totalTime = (TextView)convertView.findViewById(R.id.total_time);

        DayInfo dayInfo = info.get(position);
        System.out.println(day.getText() + " " + dayInfo.day);
        day.setText(dayInfo.day+"");
        dayTime.setText(dayInfo.dayTime+"");
        nightTime.setText(dayInfo.nightTime+"");
        totalTime.setText(dayInfo.totalTime+"");

        return convertView;
    }

    public List<DayInfo> getDataForDayInfoList() {
        List<DayInfo> dayInfoList = new ArrayList<DayInfo>();
        for (int i = 0; i < 7; i++) {
            DayInfo info = new DayInfo();
            info.day = i + 1;
            info.dayTime = i * 10;
            info.nightTime = i * 10;
            info.totalTime = info.dayTime + info.nightTime;
            dayInfoList.add(info);
        }
        System.out.println(dayInfoList.size());
        return dayInfoList;
    }

    public class DayInfo{
        int day;
        int dayTime;
        int nightTime;
        int totalTime;
    }
}

