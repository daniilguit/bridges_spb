package com.ad.bridges_spb.core;


import com.ad.bridges_spb.R;

import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: daniil
 * Date: 17.07.12
 * Time: 21:58
 * To change this template use File | Settings | File Templates.
 */
public enum SortingOrder implements Comparator<Bridge> {
    BY_NAME(0, R.string.by_name) {
        @Override
        public int compare(Bridge bridge, Bridge bridge1) {
            return bridge.getName().compareTo(bridge1.getName());
        }
    },
    BY_OPEN(1, R.string.by_open) {
        @Override
        public int compare(Bridge bridge, Bridge bridge1) {
            return minutesToOpen(bridge) - minutesToOpen(bridge1);
        }

        private int minutesToOpen(Bridge bridge) {
            return bridge.getMinutesToChange() > 0 ? bridge.getMinutesToChange() : (Integer.MAX_VALUE + bridge.getMinutesToChange());
        }
    },
    BY_DISTANCE(2, R.string.by_distance) {
        @Override
        public int compare(Bridge bridge, Bridge bridge1) {
            return Double.compare(bridge.getDistance(), bridge1.getDistance());
        }
    };
    public final int index;
    public final int label;

    SortingOrder(int index, int label) {
        this.index = index;
        this.label = label;
    }

    @Override
    public abstract int compare(Bridge bridge, Bridge bridge1);
}
