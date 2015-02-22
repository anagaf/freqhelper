package com.anagaf.freqhelper.ranges;

import com.anagaf.freqhelper.model.ranges.Ctcss39;
import com.anagaf.freqhelper.model.ranges.Range;

public class Ctcss39Test extends BaseCtcssRangeTest {

    private Range mRange = new Ctcss39();

    @Override
    protected String getExpectedFrequenciesFileName() {
        return "ctcss39.txt";
    }

    @Override
    protected Range getRange() {
        return mRange;
    }
}
