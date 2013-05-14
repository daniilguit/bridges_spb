package com.ad.bridges_spb.core;

import com.ad.bridges_spb.LocationUtil;
import com.ad.bridges_spb.R;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: daniil
 * Date: 15.07.12
 * Time: 9:28
 * To change this template use File | Settings | File Templates.
 */
public class BridgesDescriptions {
    public static final BridgeDescription[] BRIDGES = new BridgeDescription[]{
            new BridgeDescription(R.string.bn_Volodarskij, LocationUtil.location(30.450912, 59.877023), Arrays.asList(new BridgeDescription.ClosedInterval(new Time(2, 0), new Time(3, 45)), new BridgeDescription.ClosedInterval(new Time(4, 15), new Time(5, 45)))),
            new BridgeDescription(R.string.bn_Finljandskij, LocationUtil.location(30.404631, 59.913965), Arrays.asList(new BridgeDescription.ClosedInterval(new Time(2, 20), new Time(5, 30)))),
            new BridgeDescription(R.string.bn_AleksandraNevskogo, LocationUtil.location(30.399447, 59.926377), Arrays.asList(new BridgeDescription.ClosedInterval(new Time(2, 20), new Time(5, 10)))),
            new BridgeDescription(R.string.bn_Bolsheohtinskij, LocationUtil.location(30.398037, 59.942642), Arrays.asList(new BridgeDescription.ClosedInterval(new Time(1, 40), new Time(5, 0)))),
            new BridgeDescription(R.string.bn_Litejnyj, LocationUtil.location(30.349932, 59.953511), Arrays.asList(new BridgeDescription.ClosedInterval(new Time(1, 20), new Time(5, 0)))),
            new BridgeDescription(R.string.bn_Troickij, LocationUtil.location(30.327465, 59.948762), Arrays.asList(new BridgeDescription.ClosedInterval(new Time(1, 15), new Time(5, 0)))),
            new BridgeDescription(R.string.bn_Blagovewenskij, LocationUtil.location(30.288281, 59.936115), Arrays.asList(new BridgeDescription.ClosedInterval(new Time(1, 25), new Time(2, 45)), new BridgeDescription.ClosedInterval(new Time(3, 10), new Time(5, 0)))),
            new BridgeDescription(R.string.bn_Birzhevoj, LocationUtil.location(30.303310, 59.945143), Arrays.asList(new BridgeDescription.ClosedInterval(new Time(1, 5), new Time(5, 10)))),
            new BridgeDescription(R.string.bn_Tuchkov, LocationUtil.location(30.284454, 59.948131), Arrays.asList(new BridgeDescription.ClosedInterval(new Time(1, 5), new Time(2, 35)), new BridgeDescription.ClosedInterval(new Time(3, 5), new Time(5, 15)))),
    };
}
