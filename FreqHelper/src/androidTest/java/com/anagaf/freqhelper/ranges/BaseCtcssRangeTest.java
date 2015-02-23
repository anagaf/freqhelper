package com.anagaf.freqhelper.ranges;

import com.anagaf.freqhelper.model.keys.Frequency;

public abstract class BaseCtcssRangeTest extends BaseRangeTest {

    @Override
    protected long parseValue(String[] components) {
        final Integer hz = Integer.parseInt(components[0]);
        final Integer deciHz = Integer.parseInt(components[1]);
        return Frequency.getCtcssFrequencyDecihertz(hz, deciHz);
    }
}
