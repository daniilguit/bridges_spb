#!/bin/env python
# coding=utf-8

import codecs
import urllib

class Bridge:
    pass
lines = codecs.open('timetable.txt', 'r', 'utf-8').readlines()
bridges = []
bridge = None
for line in lines:
    splitted_line = line.split('\t')
    if splitted_line[0] == '2':
        bridge.timetable +=  [((splitted_line[1], splitted_line[3]))]
    else:
        bridge = Bridge()
        bridges.append(bridge)
        bridge.name = splitted_line[0]
        bridge.timetable = [((splitted_line[2], splitted_line[4]))]

def format_time_java(time):
    time = time.split(':')
    return u'new Time(%s, %s)' % (time[0], time[1] if time[1][0] != '0' else time[1][1])

def format_timetable_java(timetable):
    return u'Arrays.asList(' + u','.join(map(lambda x: u'new Bridge.ClosedInterval(%s, %s)' % (format_time_java(x[0]), format_time_java(x[1])), timetable)) + u')'

def get_location(bridge_name):
    url = u'http://geocode-maps.yandex.ru/1.x/?' + urllib.urlencode({'geocode':(bridge_name + u' мост петербург').encode('utf-8')})
    for line in urllib.urlopen(url):
        line = line.strip()
        if line.startswith('<pos>'):
            line = line[5:-6]
            return line.split(' ')

out = codecs.open('timetable.java', 'w', 'utf-8')
for bridge in bridges:
    location = get_location(bridge.name)
    line = (u'new Bridge("{0}", location({1}, {2}), {3})').format(bridge.name, location[0], location[1], format_timetable_java(bridge.timetable))
    out.write(line)
    out.write(',\n')
out.close()