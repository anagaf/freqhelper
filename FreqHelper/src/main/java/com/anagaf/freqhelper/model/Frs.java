package com.anagaf.freqhelper.model;

import com.anagaf.freqhelper.Frequency;
import com.anagaf.freqhelper.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
01  462.5625
02  462.5875
03  462.6125
04  462.6375
05  462.6625
06  462.6875
07  462.7125
08  467.5625
09  467.5875
10  467.6125
11  467.6375
12  467.6625
13  467.6875
14  467.7125
*/

public class Frs extends StaticRange {

    private static final List<Frequency> sFrequencies;
    static {
        final int SEGMENT_CAPACITY = 7;
        final Frequency LOW_SEGMENT_BASE_FREQUENCY = Frequency.newChannelFrequency(462, 562, 500);
        final Frequency HIGH_SEGMENT_BASE_FREQUENCY = Frequency.newChannelFrequency(467, 562, 500);
        final int STEP = 25000;

        final List<Frequency> frequencies = new ArrayList<>();
        fillFrequencies(frequencies, LOW_SEGMENT_BASE_FREQUENCY, SEGMENT_CAPACITY, STEP);
        fillFrequencies(frequencies, HIGH_SEGMENT_BASE_FREQUENCY, SEGMENT_CAPACITY, STEP);
        sFrequencies = Collections.unmodifiableList(frequencies);
    }

    private static void fillFrequencies(List<Frequency> frequencies, Frequency baseFrequency, int count, int step) {
        for (int i = 0; i < count; i++) {
            final Frequency frequency = baseFrequency.append(step * i);
            frequencies.add(frequency);
        }
    }

    @Override
    public Integer getNameStringId() {
        return R.string.frs;
    }

    @Override
    protected List<Frequency> getFrequencies() {
        return sFrequencies;
    }
}
