package com.anagaf.freqhelper.ranges;

import com.anagaf.freqhelper.model.keys.Frequency;

public abstract class BaseChannelRangeTest extends BaseRangeTest {

    @Override
    protected Frequency createFrequency(String[] frequencyComponents) {
        final int megaHz = Integer.parseInt(frequencyComponents[0]);
        final int kiloHz = Integer.parseInt(frequencyComponents[1]);
        final int hz;
        if (frequencyComponents.length == 2) {
            hz = 0;
        } else if (frequencyComponents.length == 3) {
            hz = Integer.parseInt(frequencyComponents[2]);
        } else {
            throw new IllegalArgumentException();
        }
        return Frequency.newChannelFrequency(megaHz, kiloHz, hz);
    }
}
