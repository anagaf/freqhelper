package com.anagaf.freqhelper.model.ranges;

import com.anagaf.freqhelper.R;
import com.anagaf.freqhelper.model.keys.Frequency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
1	67.0	11	94.8	21	131.8	31	186.2
2	69.3	12	97.4	22	136.5	32	192.8
3	71.9	13	100.0	23	141.3	33	203.5
4	74.4	14	103.5	24	146.2	34	210.7
5	77.0	15	107.2	25	151.4	35	218.1
6	79.7	16	110.9	26	156.7	36	225.7
7	82.5	17	114.8	27	162.2	37	233.6
8	85.4	18	118.8	28	167.9	38	241.8
9	88.5	19	123.0	29	173.8	39	250.3
10	91.5	20	127.3	30	179.9
*/
public class Ctcss39 extends StaticRange {
    private static final List<Long> sFrequencies;
    static {
        List<Long> frequencies = new ArrayList<>();
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(67, 0));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(69, 3));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(71, 9));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(74, 4));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(77, 0));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(79, 7));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(82, 5));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(85, 4));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(88, 5));

        frequencies.add(Frequency.getCtcssFrequencyDecihertz(91, 5));

        frequencies.add(Frequency.getCtcssFrequencyDecihertz(94, 8));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(97, 4));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(100, 0));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(103, 5));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(107, 2));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(110, 9));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(114, 8));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(118, 8));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(123, 0));

        frequencies.add(Frequency.getCtcssFrequencyDecihertz(127, 3));


        frequencies.add(Frequency.getCtcssFrequencyDecihertz(131, 8));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(136, 5));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(141, 3));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(146, 2));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(151, 4));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(156, 7));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(162, 2));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(167, 9));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(173, 8));

        frequencies.add(Frequency.getCtcssFrequencyDecihertz(179, 9));


        frequencies.add(Frequency.getCtcssFrequencyDecihertz(186, 2));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(192, 8));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(203, 5));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(210, 7));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(218, 1));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(225, 7));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(233, 6));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(241, 8));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(250, 3));
        sFrequencies = Collections.unmodifiableList(frequencies);
    }

    @Override
    protected List<Long> getValues() {
        return sFrequencies;
    }

    @Override
    public Integer getNameStringId() {
        return R.string.ctcss39;
    }
}
