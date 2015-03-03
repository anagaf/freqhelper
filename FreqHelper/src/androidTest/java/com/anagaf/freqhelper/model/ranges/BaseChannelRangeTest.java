package com.anagaf.freqhelper.model.ranges;

import com.anagaf.freqhelper.model.Frequency;

public abstract class BaseChannelRangeTest extends BaseFrequencyRangeTest {

    @Override
    protected long parseExpectedFrequency(String[] components) {
        final int megaHz = Integer.parseInt(components[0]);
        final int kiloHz = Integer.parseInt(components[1]);
        final int hz;
        if (components.length == 2) {
            hz = 0;
        } else if (components.length == 3) {
            hz = Integer.parseInt(components[2]);
        } else {
            throw new IllegalArgumentException();
        }
        return Frequency.getChannelFrequencyDecihertz(megaHz, kiloHz, hz);
    }
}
