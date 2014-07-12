package com.anagaf.freqhelper.ranges;

import com.anagaf.freqhelper.Frequency;

/*
1	446.00625
2	446.01875
3	446.03125
4	446.04375
5	446.05625
6	446.06875
7	446.08125
8	446.09375
*/

public class Pmr extends FixedStepRange {

    private static final Frequency BASE_FREQUENCY = new Frequency(446, 0);

    @Override
    public int getChannelCount() {
        return 8;
    }

    @Override
    protected Frequency getBaseFrequency() {
        return BASE_FREQUENCY;
    }

    @Override
    protected int getStep() {
        return 6250;
    }
}
