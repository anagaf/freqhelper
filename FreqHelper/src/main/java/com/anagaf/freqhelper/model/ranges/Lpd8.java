package com.anagaf.freqhelper.model.ranges;

import com.anagaf.freqhelper.R;
import com.anagaf.freqhelper.model.keys.Frequency;

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
public class Lpd8 extends StaticRange {

    private static final List<Frequency> sFrequencies;
    static {
        List<Frequency> frequencies = new ArrayList<>();
        frequencies.add(Frequency.newChannelFrequency(433, 75, 0));
        frequencies.add(Frequency.newChannelFrequency(433, 100, 0));
        frequencies.add(Frequency.newChannelFrequency(433, 200, 0));
        frequencies.add(Frequency.newChannelFrequency(433, 300, 0));
        frequencies.add(Frequency.newChannelFrequency(433, 350, 0));
        frequencies.add(Frequency.newChannelFrequency(433, 475, 0));
        frequencies.add(Frequency.newChannelFrequency(433, 625, 0));
        frequencies.add(Frequency.newChannelFrequency(433, 800, 0));
        sFrequencies = Collections.unmodifiableList(frequencies);
    }

    @Override
    public Integer getNameStringId() {
        return R.string.lpd8;
    }

    @Override
    protected List<Frequency> getKeys() {
        return sFrequencies;
    }
}
