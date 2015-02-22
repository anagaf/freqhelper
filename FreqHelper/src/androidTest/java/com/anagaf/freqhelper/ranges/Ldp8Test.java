package com.anagaf.freqhelper.ranges;

import com.anagaf.freqhelper.model.Lpd8;
import com.anagaf.freqhelper.model.Range;

public class Ldp8Test extends BaseChannelRangeTest {

    private final Range mRange = new Lpd8();

    @Override
    protected String getExpectedFrequenciesFileName() {
        return "lpd8.txt";
    }

    @Override
    protected Range getRange() {
        return mRange;
    }
}
