package com.anagaf.freqhelper.ranges;

import com.anagaf.freqhelper.model.ranges.Frs;
import com.anagaf.freqhelper.model.ranges.Range;

public class FrsTest extends BaseChannelRangeTest {

    private final Range mRange = new Frs();

    @Override
    protected String getExpectedFrequenciesFileName() {
        return "frs.txt";
    }

    @Override
    protected Range getRange() {
        return mRange;
    }
}
