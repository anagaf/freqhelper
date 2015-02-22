package com.anagaf.freqhelper.ranges;

import com.anagaf.freqhelper.model.ranges.Ctcss64;
import com.anagaf.freqhelper.model.ranges.Range;

public class Ctcss64Test extends BaseCtcssRangeTest {

    private final Range mRange = new Ctcss64();

    @Override
    protected String getExpectedFrequenciesFileName() {
        return "ctcss64.txt";
    }

    @Override
    protected Range getRange() {
        return mRange;
    }
}
