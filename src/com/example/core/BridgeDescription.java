package com.example.core;

import android.location.Location;

import java.util.List;

public class BridgeDescription {
    public final String name;
    public final List<ClosedInterval> closedIntervals;
    public final Location location;

    public BridgeDescription(String name, Location location, List<ClosedInterval> closedIntervals) {
        this.name = name;
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