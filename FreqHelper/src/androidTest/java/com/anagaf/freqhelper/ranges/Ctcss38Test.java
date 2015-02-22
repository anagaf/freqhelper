package com.anagaf.freqhelper.ranges;

import com.anagaf.freqhelper.model.ranges.Ctcss38;
import com.anagaf.freqhelper.model.ranges.Range;

public class Ctcss38Test extends BaseCtcssRangeTest {

    private Range mRange = new Ctcss38();

    @Override
    protected String getExpectedFrequenciesFileName() {
        return "ctcss38.txt";
    }

    @Override
    protected Range getRange() {
        return mRange;
    }
}
