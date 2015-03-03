package com.anagaf.freqhelper.model.ranges;

import java.util.ArrayList;
import java.util.List;

public class DcsTest extends BaseRangeTest {
    private List<Long> mDirectCodes = new ArrayList<>();
    private List<Long> mInverseCodes = new ArrayList<>();

    @Override
    protected void parseExpectedValue(String[] components) {
        mDirectCodes.add(Long.parseLong(components[0]));
        mInverseCodes.add(Long.parseLong(components[1]));
    }

    @Override
    protected String getExpectedValuesFileName() {
        return "dcs.txt";
    }

    public void test() {
        final Range range = new Dcs();
        assertEquals(mDirectCodes.size(), range.getCount());
        assertEquals(mInverseCodes.size(), range.getCount());

        for (int i = 0; i < range.getCount(); i++) {
            final long expectedDirectCode = mDirectCodes.get(i);
            final long expectedInverseCode = mInverseCodes.get(i);
            final long directCode = range.getValue(i + 1);
            final long inverseCode = Dcs.getInverseCode(directCode);
            assertEquals(expectedDirectCode, directCode);
            assertEquals(expectedInverseCode, inverseCode);
            assertEquals(directCode, Dcs.getDirectCode(inverseCode));
        }
    }
}
