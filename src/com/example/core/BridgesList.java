package com.example.core;

import android.content.SharedPreferences;
import android.location.Location;
import com.example.fragments.AbstractFragment;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: daniil
 * Date: 17.07.12
 * Time: 12:23
 * To change this template use File | Settings | File Templates.
 */
public class BridgesList extends AbstractList<Bridge>{
    private final List<Bridge> storage = new ArrayList<Bridge>();
    private final List<BridgesListener> listeners = new ArrayList<BridgesListener>();
    private final SharedPreferences preferences;

    public BridgesList(SharedPreferences preferences, BridgeDescription[] descriptions) {
        this.preferences = preferences;
        for (BridgeDescription description : descriptions) {
            storage.add(new Bridge(this, description, preferences.getBoolean(description.name, false)));
        }
        Collections.sort(storage, SortingOrder.BY_NAME);
    }

    public void addListener(BridgesListener listener) {
        listeners.add(listener);
    }

    public void removeListener(BridgesListener listener) {
        listeners.remove(listener);
    }

    public void update(Location location) {
        Calendar now = Calendar.getInstance();
        for (Bridge bridge : storage) {
            bridge.update(now, location);
        }
        for (BridgesListener listener : listeners) {
            listener.updated();
        }
    }

    void notifyUpdated(Bridge bridge) {
        SharedPreferences.Editor edit = preferences.edit();
        edit.putBoolean(bridge.getDescription().name, bridge.isFavourite());
        edit.commit();
        for (BridgesListener listener : listeners) {
            listener.updated(bridge);
        }
    }

    @Override
    public Bridge get(int i) {
        return storage.get(i);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
