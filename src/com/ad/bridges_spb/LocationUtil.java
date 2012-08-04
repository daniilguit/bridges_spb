package com.ad.bridges_spb;

import android.location.Location;

import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: daniil
 * Date: 21.07.12
 * Time: 20:58
 * To change this template use File | Settings | File Templates.
 */
public class LocationUtil {
    public static Location location(double longtitude, double altitude) {
        Location location = new Location("");
        location.setLongitude(longtitude);
        location.setLatitude(altitude);
        return location;
    }

    public static String locationUri(Location location, int zoom) {
        return String.format(Locale.ENGLISH, "geo:%f,%f?z=%d", location.getLatitude(), location.getLongitude(), zoom);
    }
    public static String locationUri(Location location, int zoom, String label) {
        return String.format(Locale.ENGLISH, "geo:%f,%f?z=%d (%s)", location.getLatitude(), location.getLongitude(), zoom, label);
    }
}
