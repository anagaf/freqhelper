package com.anagaf.freqhelper.model.ranges;

import com.anagaf.freqhelper.R;
import com.anagaf.freqhelper.model.Frequency;

/*
1	433.075	24	433.650	47	434.225
2	433.100	25	433.675	48	434.250
3	433.125	26	433.700	49	434.275
4	433.150	27	433.725	50	434.300
5	433.175	28	433.750	51	434.325
6	433.200	29	433.775	52	434.350
7	433.225	30	433.800	53	434.375
8	433.250	31	433.825	54	434.400
9	433.275	32	433.850	55	434.425
10	433.300	33	433.875	56	434.450
11	433.325	34	433.900	57	434.475
12	433.350	35	433.925	58	434.500
13	433.375	36	433.950	59	434.525
14	433.400	37	433.975	60	434.550
15	433.425	38	434.000	61	434.575
16	433.450	39	434.025	62	434.600
17	433.475	40	434.050	63	434.625
18	433.500	41	434.075	64	434.650
19	433.525	42	434.100	65	434.675
20	433.550	43	434.125	66	434.700
21	433.575	44	434.150	67	434.725
22	433.600	45	434.175	68	434.750
23	433.625	46	434.200	69	434.775
*/
public class Lpd69 extends DynamicRange {

    private static final long BASE_VALUE = Frequency.getChannelFrequencyDecihertz(433, 75, 0);

    @Override
    public Integer getNameStringId() {
        return R.string.lpd69;
    }

    @Override
    public int getCount() {
        return 69;
    }

    @Override
    protected long getBaseValue() {
        return BASE_VALUE;
    }

    @Override
    protected long getStep() {
        return 250000;
    }
}
