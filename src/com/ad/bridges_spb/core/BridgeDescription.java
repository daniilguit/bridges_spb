package com.ad.bridges_spb.core;

import android.location.Location;

import java.util.List;

public class BridgeDescription {
    public final int nameId;
    public final List<ClosedInterval> closedIntervals;
    public final Location location;

    public BridgeDescription(int nameId, Location location, List<ClosedInterval> closedIntervals) {
        this.nameId = nameId;
        this.closedIntervals = closedIntervals;
        this.location = location;
    }

    public static class ClosedInterval {
        final Time from;
        final Time to;

        public ClosedInterval(Time from, Time to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return from + "-" + to;
        }
    }
}