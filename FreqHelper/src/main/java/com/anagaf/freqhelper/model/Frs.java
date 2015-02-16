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

public class Frs extends AbstractRange {

    private static final List<Frequency> sFrequencies;
    static {
        List<Frequency> frequencies = new ArrayList<>();
        frequencies.add(new Frequency(462, 562, 500));
        frequencies.add(new Frequency(462, 587, 500));
        frequencies.add(new Frequency(462, 612, 500));
        frequencies.add(new Frequency(462, 637, 500));
        frequencies.add(new Frequency(462, 662, 500));
        frequencies.add(new Frequency(462, 687, 500));
        frequencies.add(new Frequency(462, 712, 500));
        frequencies.add(new Frequency(467, 562, 500));
        frequencies.add(new Frequency(467, 587, 500));
        frequencies.add(new Frequency(467, 612, 500));
        frequencies.add(new Frequency(467, 637, 500));
        frequencies.add(new Frequency(467, 662, 500));
        frequencies.add(new Frequency(467, 712, 500));
        sFrequencies = Collections.unmodifiableList(frequencies);
    }

    @Override
    public Frequency getFrequency(int channel) {
        return sFrequencies.get(channel);
    }

    @Override
    public Integer getNameStringId() {
        return R.string.frs;
    }

    @Override
    public int getChannelCount() {
        return 22;
    }

}
