package com.example;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.core.Bridge;
import com.example.core.BridgeDescription;

import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: daniil
 * Date: 17.07.12
 * Time: 12:03
 * To change this template use File | Settings | File Templates.
 */
public class BridgesListAdapter extends ArrayAdapter<Bridge> {
    private final LayoutInflater inflater;

    public BridgesListAdapter(Context context, Collection<Bridge> bridges) {
        super(context, R.layout.bridges_list_row);
        for (Bridge bridge : bridges) {
            add(bridge);
        }
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (convertView == null) {
            rowView = inflater.inflate(R.layout.bridges_list_row, null);
        }
        TextView nameView = (TextView) rowView.findViewById(R.id.name);
        TextView distanceView = (TextView) rowView.findViewById(R.id.distance);
        TextView timeDeltaView = (TextView) rowView.findViewById(R.id.when_change);
        TextView timetableView = (TextView) rowView.findViewById(R.id.timetable);
        Bridge bridge = getItem(position);
        nameView.setText(bridge.getDescription().name);
        nameView.setTextColor(bridge.isOpen() ? Color.GREEN : Color.RED);
        if (bridge.getDistance() >= 0) {
            distanceView.setText(String.format("%.1f км", bridge.getDistance() / 1000));
        } else {
            distanceView.setText("");
        }
        timeDeltaView.setText(formatDeltaTime(bridge.getMinutesToChange()));
        timetableView.setText(formatTimetable(bridge));
        return rowView;
    }

    private CharSequence formatDeltaTime(int minutesToChange) {
        String delta = String.format("%d:%02d", Math.abs(minutesToChange) / 60, Math.abs(minutesToChange) % 60);
        if (minutesToChange < 0) {
            return "---- через " + delta;
        } else {
            return "_/ \\_ через " + delta;
        }
    }

    private static String formatTimetable(Bridge bridge) {
        List<BridgeDescription.ClosedInterval> closedIntervals = bridge.getDescription().closedIntervals;
        StringBuilder result = new StringBuilder(closedIntervals.get(0).toString());
        for (int i = 1; i < closedIntervals.size(); i++) {
            result.append(", ");
            result.append(closedIntervals.get(i).toString());
        }
        return result.toString();
    }
}
