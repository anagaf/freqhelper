package com.anagaf.freqhelper.model.ranges;

import com.anagaf.freqhelper.R;
import com.anagaf.freqhelper.model.Frequency;

/*
1	446.00625
2	446.01875
3	446.03125
4	446.04375
5	446.05625
6	446.06875
7	446.08125
8	446.09375
*/
public class Pmr extends DynamicRange {

    private static final long BASE_VALUE = Frequency.getChannelFrequencyDecihertz(446, 6, 250);

    @Override
    public Integer getNameStringId() {
        return R.string.pmr;
    }

    @Override
    public int getCount() {
        return 8;
    }

    @Override
    protected long getBaseValue() {
        return BASE_VALUE;
    }

    @Override
    protected long getStep() {
        return 125000;
    }
}
