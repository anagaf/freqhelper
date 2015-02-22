package com.anagaf.freqhelper.ranges;

import com.anagaf.freqhelper.model.Lpd69;
import com.anagaf.freqhelper.model.Range;

public class Lpd69Test extends BaseChannelRangeTest {

    private final Range mRange = new Lpd69();

    @Override
    protected String getExpectedFrequenciesFileName() {
        return "lpd69.txt";
    }

    @Override
    protected Range getRange() {
        return mRange;
    }
}
