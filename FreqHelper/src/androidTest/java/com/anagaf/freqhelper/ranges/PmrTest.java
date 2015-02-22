package com.anagaf.freqhelper.ranges;

import com.anagaf.freqhelper.model.ranges.Pmr;
import com.anagaf.freqhelper.model.ranges.Range;

public class PmrTest extends BaseChannelRangeTest {

    private final Range mRange = new Pmr();

    @Override
    protected String getExpectedFrequenciesFileName() {
        return "pmr.txt";
    }

    @Override
    protected Range getRange() {
        return mRange;
    }
}
