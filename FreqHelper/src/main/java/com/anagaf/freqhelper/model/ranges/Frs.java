package com.anagaf.freqhelper.model.ranges;

import com.anagaf.freqhelper.R;
import com.anagaf.freqhelper.model.Frequency;

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

    private static final List<Long> sValues;
    static {
        final int SEGMENT_CAPACITY = 7;
        final long LOW_SEGMENT_BASE_VALUE = Frequency.getChannelFrequencyDecihertz(462, 562, 500);
        final long HIGH_SEGMENT_BASE_VALUE = Frequency.getChannelFrequencyDecihertz(467, 562, 500);
        final int STEP_DECI_HZ = 250000;

        final List<Long> values = new ArrayList<>();
        fillFrequencies(values, LOW_SEGMENT_BASE_VALUE, SEGMENT_CAPACITY, STEP_DECI_HZ);
        fillFrequencies(values, HIGH_SEGMENT_BASE_VALUE, SEGMENT_CAPACITY, STEP_DECI_HZ);
        sValues = Collections.unmodifiableList(values);
    }

    private static void fillFrequencies(List<Long> values, long base, int count, int step) {
        for (int i = 0; i < count; i++) {
            values.add(base + step * i);
        }
    }

    @Override
    public Integer getNameStringId() {
        return R.string.frs;
    }

    @Override
    protected List<Long> getValues() {
        return sValues;
    }
}
