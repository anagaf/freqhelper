package com.anagaf.freqhelper.ranges;

import com.anagaf.freqhelper.Frequency;

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
