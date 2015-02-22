package com.anagaf.freqhelper.ranges;

import com.anagaf.freqhelper.model.keys.Frequency;

public abstract class BaseCtcssRangeTest extends BaseRangeTest {

    @Override
    protected Frequency createFrequency(String[] frequencyComponents) {
        final Integer hz = Integer.parseInt(frequencyComponents[0]);
        final Integer deciHz = Integer.parseInt(frequencyComponents[1]);
        return Frequency.newCtcssFrequency(hz, deciHz);
    }
}
