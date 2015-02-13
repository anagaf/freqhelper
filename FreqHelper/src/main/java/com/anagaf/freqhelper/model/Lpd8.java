package com.anagaf.freqhelper.model;

import com.anagaf.freqhelper.BuildConfig;
import com.anagaf.freqhelper.Frequency;
import com.anagaf.freqhelper.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
1	433.075
2	433.100
3	433.200
4	433.300
5	433.350
6	433.475
7	433.625
8	433.800
*/
public class Lpd8 extends AbstractRange {

    private static final List<Frequency> sFrequencies;
    static {
        List<Frequency> frequencies = new ArrayList<>();
        frequencies.add(new Frequency(433, 75));
        frequencies.add(new Frequency(433, 100));
        frequencies.add(new Frequency(433, 200));
        frequencies.add(new Frequency(433, 300));
        frequencies.add(new Frequency(433, 350));
        frequencies.add(new Frequency(433, 475));
        frequencies.add(new Frequency(433, 625));
        frequencies.add(new Frequency(433, 800));
        sFrequencies = Collections.unmodifiableList(frequencies);
    }

    @Override
    public Integer getNameStringId() {
        return R.string.lpd8;
    }

    @Override
    public int getChannelCount() {
        return sFrequencies.size();
    }

    @Override
    public Frequency getFrequency(int channel) {
        if (BuildConfig.DEBUG && (channel < 1 || channel > getChannelCount())) {
            throw new IllegalArgumentException();
        }
        return sFrequencies.get(channel - 1);
    }
}
