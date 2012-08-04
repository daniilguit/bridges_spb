package com.ad.bridges_spb;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.ad.bridges_spb.core.Bridge;
import com.ad.bridges_spb.core.BridgeDescription;

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
    private String afterString;
    private String kmString;
    private int closedBridgeColor;
    private int nearlyClosedBridgeColor;
    private int openBridgeColor;

    public BridgesListAdapter(Context context, Collection<Bridge> bridges) {
        super(context, R.layout.bridges_list_row);
        for (Bridge bridge : bridges) {
            add(bridge);
        }
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void loadResources() {
        Resources resources = getContext().getResources();
        afterString = resources.getString(R.string.after_part);
        kmString = resources.getString(R.string.km_part);
        closedBridgeColor = resources.getColor(R.color.closed_bridge_row);
        nearlyClosedBridgeColor = resources.getColor(R.color.nearly_closed_bridge_row);
        openBridgeColor = resources.getColor(R.color.open_bridge_row);
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
        nameView.setText(bridge.getName());

        int minutesToChange = bridge.getMinutesToChange();
        nameView.setTextColor(chooseTitleColorId(minutesToChange));
        if (bridge.getDistance() >= 0) {
            distanceView.setText(String.format("%.1f %s", bridge.getDistance() / 1000, kmString));
        } else {
            distanceView.setText("");
        }
        timeDeltaView.setText(formatDeltaTime(minutesToChange));
        timetableView.setText(formatTimetable(bridge));
        return rowView;
    }

    private int chooseTitleColorId(int minutesToChange) {

        if (minutesToChange < 0) {
            return closedBridgeColor;
        }
        if (minutesToChange < 15) {
            return nearlyClosedBridgeColor;
        }
        return openBridgeColor;
    }

    private CharSequence formatDeltaTime(int minutesToChange) {
        String nextState = minutesToChange < 0 ? "----" : "_/ \\_ ";
        return String.format("%s %s %d:%02d",nextState, afterString, Math.abs(minutesToChange) / 60, Math.abs(minutesToChange) % 60);
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
