package com.anagaf.freqhelper.converters;

import com.anagaf.freqhelper.model.Ctcss64;
import com.anagaf.freqhelper.model.Range;

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
