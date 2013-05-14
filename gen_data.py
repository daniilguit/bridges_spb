#!/bin/env python
# coding=utf-8

import codecs
import urllib

names = {
    u'Александра Невского' : 'R.string.bn_AleksandraNevskogo',
    u'Биржевой' : 'R.string.bn_Birzhevoj',
    u'Благовещенский' : 'R.string.bn_Blagovewenskij',
    u'Большеохтинский' : 'R.string.bn_Bolsheohtinskij',
    u'' : 'R.string.bn_Dvorcovyj',
    u'Финляндский' : 'R.string.bn_Finljandskij',
    u'' : 'R.string.bn_Grenaderskij',
    u'' : 'R.string.bn_Kantemirovskij',
    u'Литейный' : 'R.string.bn_Litejnyj',
    u'' : 'R.string.bn_Sampsonievskij',
    u'Троицкий' : 'R.string.bn_Troickij',
    u'Тучков' : 'R.string.bn_Tuchkov',
    u'Володарский' : 'R.string.bn_Volodarskij',
}

coords = {
    'R.string.bn_AleksandraNevskogo' : 'LocationUtil.location(30.399447, 59.926377)',
    'R.string.bn_Birzhevoj' : 'LocationUtil.location(30.303310, 59.945143)',
    'R.string.bn_Blagovewenskij' : 'LocationUtil.location(30.288281, 59.936115)',
    'R.string.bn_Bolsheohtinskij' : 'LocationUtil.location(30.398037, 59.942642)',
    'R.string.bn_Dvorcovyj' : 'LocationUtil.location(30.309508, 59.940177)',
    'R.string.bn_Finljandskij' : 'LocationUtil.location(30.404631, 59.913965)',
    'R.string.bn_Grenaderskij' : 'LocationUtil.location(30.332595, 59.967516)',
    'R.string.bn_Kantemirovskij' : 'LocationUtil.location(30.320405, 59.977733)',
    'R.string.bn_Litejnyj' : 'LocationUtil.location(30.349932, 59.953511)',
    'R.string.bn_Sampsonievskij' : 'LocationUtil.location(30.339772, 59.957980)',
    'R.string.bn_Troickij' : 'LocationUtil.location(30.327465, 59.948762)',
    'R.string.bn_Tuchkov' : 'LocationUtil.location(30.284454, 59.948131)',
    'R.string.bn_Volodarskij' : 'LocationUtil.location(30.450912, 59.877023)'
}

class Bridge:
    pass
lines = codecs.open('timetable.txt', 'r', 'utf-8').readlines()
bridges = []
bridge = None
for line in lines:
    splitted_line = line.split('\t')
    if splitted_line[1] == '2':
        bridge.timetable += [((splitted_line[2], splitted_line[5]))]
    else:
        bridge = Bridge()
        bridges.append(bridge)
        bridge.name = splitted_line[0]
        bridge.timetable = [((splitted_line[2], splitted_line[5]))]

def format_time_java(time):
    time = time.split(':')
    return u'new Time(%s, %s)' % (time[0], time[1] if time[1][0] != '0' else time[1][1])

def format_timetable_java(timetable):
    return u'Arrays.asList(' + u','.join(map(lambda x: u'new BridgeDescription.ClosedInterval(%s, %s)' % (format_time_java(x[0]), format_time_java(x[1])), timetable)) + u')'

def get_location(bridge_name):
    return coords[names[bridge_name]]

out = codecs.open('timetable.java', 'w', 'utf-8')
for bridge in bridges:
    line = (u'new BridgeDescription({0}, {1}, {2})').format(names[bridge.name], get_location(bridge.name), format_timetable_java(bridge.timetable))
    out.write(line)
    out.write(',\n')
out.close()