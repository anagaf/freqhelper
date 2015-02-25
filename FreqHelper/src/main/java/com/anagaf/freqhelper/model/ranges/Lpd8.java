package com.anagaf.freqhelper.model.ranges;

import com.anagaf.freqhelper.R;
import com.anagaf.freqhelper.model.Frequency;

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

    private static final List<Long> sValues;
    static {
        List<Long> values = new ArrayList<>();
        values.add(Frequency.getChannelFrequencyDecihertz(433, 75, 0));
        values.add(Frequency.getChannelFrequencyDecihertz(433, 100, 0));
        values.add(Frequency.getChannelFrequencyDecihertz(433, 200, 0));
        values.add(Frequency.getChannelFrequencyDecihertz(433, 300, 0));
        values.add(Frequency.getChannelFrequencyDecihertz(433, 350, 0));
        values.add(Frequency.getChannelFrequencyDecihertz(433, 475, 0));
        values.add(Frequency.getChannelFrequencyDecihertz(433, 625, 0));
        values.add(Frequency.getChannelFrequencyDecihertz(433, 800, 0));
        sValues = Collections.unmodifiableList(values);
    }

    @Override
    public Integer getNameStringId() {
        return R.string.lpd8;
    }

    @Override
    protected List<Long> getValues() {
        return sValues;
    }
}
