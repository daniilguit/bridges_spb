package com.ad.bridges_spb.core;

import android.location.Location;

import java.util.Calendar;

/**
* Created with IntelliJ IDEA.
* User: daniil
* Date: 17.07.12
* Time: 12:01
* To change this template use File | Settings | File Templates.
*/
public class Bridge {
    private BridgesList list;
    private final BridgeDescription description;

    private String name;

    public BridgesList getList() {
        return list;
    }

    public void setList(BridgesList list) {
        this.list = list;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    private double distance;
    private int minutesToChange;
    private boolean favourite;

    Bridge(BridgesList list, BridgeDescription description, boolean favourite) {
        this.list = list;
        this.description = description;
        this.favourite = favourite;
    }

    public boolean isOpen() {
        return getMinutesToChange() > 0;
    }

    public BridgeDescription getDescription() {
        return description;
    }

    public double getDistance() {
        return distance;
    }

    public int getMinutesToChange() {
        return minutesToChange;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
        list.notifyUpdated(this);

    }

    void update(Calendar now, Location userLocation) {
        minutesToChange = minutesToChange(now);
        if (userLocation != null) {
            distance = getDescription().location.distanceTo(userLocation);
        } else {
            distance = -1;
        }
    }

    private int minutesToChange(Calendar now) {
        for (BridgeDescription.ClosedInterval closedInterval : getDescription().closedIntervals) {
            Calendar from = closedInterval.from.today(now);
            if (now.compareTo(from) < 0) {
                return -deltaMinutes(now, from);
            }
            Calendar to = closedInterval.to.today(now);
            if (now.compareTo(to) < 0) {
                return deltaMinutes(now, to);
            }
        }
        return deltaMinutes(getDescription().closedIntervals.get(0).from.nearestDateTime(now), now);
    }

    private int deltaMinutes(Calendar now, Calendar from) {
        return (int) ((now.getTimeInMillis() - from.getTimeInMillis()) / 1000 / 60);
    }
}
