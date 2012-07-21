package com.example.core;

import com.example.LocationUtil;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: daniil
 * Date: 15.07.12
 * Time: 9:28
 * To change this template use File | Settings | File Templates.
 */
public class BridgesDescriptions {
    public static final BridgeDescription[] BRIDGES = new BridgeDescription[]{
            new BridgeDescription("Володарский", LocationUtil.location(30.450912, 59.877023), Arrays.asList(new BridgeDescription.ClosedInterval(new Time(2, 0), new Time(3, 40)), new BridgeDescription.ClosedInterval(new Time(4, 15), new Time(5, 40)))),
            new BridgeDescription("Финляндский", LocationUtil.location(30.404631, 59.913965), Arrays.asList(new BridgeDescription.ClosedInterval(new Time(2, 20), new Time(5, 10)))),
            new BridgeDescription("Александра Невского", LocationUtil.location(30.399447, 59.926377), Arrays.asList(new BridgeDescription.ClosedInterval(new Time(2, 20), new Time(5, 5)))),
            new BridgeDescription("Большеохтинский", LocationUtil.location(30.398037, 59.942642), Arrays.asList(new BridgeDescription.ClosedInterval(new Time(2, 0), new Time(4, 55)))),
            new BridgeDescription("Литейный", LocationUtil.location(30.349932, 59.953511), Arrays.asList(new BridgeDescription.ClosedInterval(new Time(1, 40), new Time(4, 40)))),
            new BridgeDescription("Троицкий", LocationUtil.location(30.327465, 59.948762), Arrays.asList(new BridgeDescription.ClosedInterval(new Time(1, 35), new Time(4, 45)))),
            new BridgeDescription("Дворцовый", LocationUtil.location(30.309508, 59.940177), Arrays.asList(new BridgeDescription.ClosedInterval(new Time(1, 5), new Time(4, 45)))),
            new BridgeDescription("Благовещенский", LocationUtil.location(30.288281, 59.936115), Arrays.asList(new BridgeDescription.ClosedInterval(new Time(1, 25), new Time(2, 40)), new BridgeDescription.ClosedInterval(new Time(3, 10), new Time(4, 55)))),
            new BridgeDescription("Биржевой", LocationUtil.location(30.303310, 59.945143), Arrays.asList(new BridgeDescription.ClosedInterval(new Time(2, 0), new Time(4, 40)))),
            new BridgeDescription("Тучков", LocationUtil.location(30.284454, 59.948131), Arrays.asList(new BridgeDescription.ClosedInterval(new Time(2, 0), new Time(2, 50)), new BridgeDescription.ClosedInterval(new Time(3, 35), new Time(4, 50)))),
            new BridgeDescription("Сампсониевский", LocationUtil.location(30.339772, 59.957980), Arrays.asList(new BridgeDescription.ClosedInterval(new Time(2, 10), new Time(2, 40)), new BridgeDescription.ClosedInterval(new Time(3, 20), new Time(4, 20)))),
            new BridgeDescription("Гренадерский", LocationUtil.location(30.332595, 59.967516), Arrays.asList(new BridgeDescription.ClosedInterval(new Time(2, 45), new Time(3, 40)), new BridgeDescription.ClosedInterval(new Time(4, 20), new Time(4, 45)))),
            new BridgeDescription("Кантемировский", LocationUtil.location(30.320405, 59.977733), Arrays.asList(new BridgeDescription.ClosedInterval(new Time(2, 45), new Time(3, 40)), new BridgeDescription.ClosedInterval(new Time(4, 20), new Time(4, 45)))),
    };

    static {
        Arrays.sort(BRIDGES, new Comparator<BridgeDescription>() {
            @Override
            public int compare(BridgeDescription bridgeDescription, BridgeDescription bridgeDescription1) {
                return bridgeDescription.name.compareTo(bridgeDescription1.name);
            }
        });
    }

}
