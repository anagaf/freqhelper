package com.anagaf.freqhelper.ranges;

import com.anagaf.freqhelper.model.keys.Frequency;

public abstract class BaseChannelRangeTest extends BaseRangeTest {

    @Override
    protected long parseValue(String[] components) {
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
