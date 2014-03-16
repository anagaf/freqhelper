package com.anagaf.freqhelper.ranges;

import com.anagaf.freqhelper.Frequency;

public class Lpd69 extends FixedStepRange {

    private static final Frequency BASE_FREQUENCY = new Frequency(433, 50);

    @Override
    public int getChannelCount() {
        return 69;
    }

    @Override
    protected Frequency getBaseFrequency() {
        return BASE_FREQUENCY;
    }

    @Override
    protected int getStep() {
        return 25000;
    }
}
