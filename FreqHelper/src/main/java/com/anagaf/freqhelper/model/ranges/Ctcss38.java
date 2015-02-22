package com.anagaf.freqhelper.model.ranges;

import com.anagaf.freqhelper.R;
import com.anagaf.freqhelper.model.keys.Frequency;
import com.anagaf.freqhelper.model.keys.Key;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
1	67.0	11	97.4	21	136.5	31	192.8
2	71.9	12	100.0	22	141.3	32	203.5
3	74.4	13	103.5	23	146.2	33	210.7
4	77.0	14	107.2	24	151.4	34	218.1
5	79.7	15	110.9	25	156.7	35	225.7
6	82.5	16	114.8	26	162.2	36	233.6
7	85.4	17	118.8	27	167.9	37	241.8
8	88.5	18	123.0	28	173.8	38	250.3
9	91.5	19	127.3	29	179.9
10	94.8	20	131.8	30	186.2
*/
public class Ctcss38 extends StaticRange {
    private static final List<Key> sFrequencies;
    static {
        List<Key> frequencies = new ArrayList<>();
        frequencies.add(Frequency.newCtcssFrequency(67, 0));
        frequencies.add(Frequency.newCtcssFrequency(71,9));
        frequencies.add(Frequency.newCtcssFrequency(74,4));
        frequencies.add(Frequency.newCtcssFrequency(77,0));
        frequencies.add(Frequency.newCtcssFrequency(79,7));
        frequencies.add(Frequency.newCtcssFrequency(82,5));
        frequencies.add(Frequency.newCtcssFrequency(85,4));
        frequencies.add(Frequency.newCtcssFrequency(88,5));
        frequencies.add(Frequency.newCtcssFrequency(91,5));
        frequencies.add(Frequency.newCtcssFrequency(94,8));
        frequencies.add(Frequency.newCtcssFrequency(97,4));
        frequencies.add(Frequency.newCtcssFrequency(100,0));
        frequencies.add(Frequency.newCtcssFrequency(103,5));
        frequencies.add(Frequency.newCtcssFrequency(107,2));
        frequencies.add(Frequency.newCtcssFrequency(110,9));
        frequencies.add(Frequency.newCtcssFrequency(114,8));
        frequencies.add(Frequency.newCtcssFrequency(118,8));
        frequencies.add(Frequency.newCtcssFrequency(123,0));
        frequencies.add(Frequency.newCtcssFrequency(127,3));
        frequencies.add(Frequency.newCtcssFrequency(131,8));
        frequencies.add(Frequency.newCtcssFrequency(136,5));
        frequencies.add(Frequency.newCtcssFrequency(141,3));
        frequencies.add(Frequency.newCtcssFrequency(146,2));
        frequencies.add(Frequency.newCtcssFrequency(151,4));
        frequencies.add(Frequency.newCtcssFrequency(156,7));
        frequencies.add(Frequency.newCtcssFrequency(162,2));
        frequencies.add(Frequency.newCtcssFrequency(167,9));
        frequencies.add(Frequency.newCtcssFrequency(173,8));
        frequencies.add(Frequency.newCtcssFrequency(179,9));
        frequencies.add(Frequency.newCtcssFrequency(186,2));
        frequencies.add(Frequency.newCtcssFrequency(192,8));
        frequencies.add(Frequency.newCtcssFrequency(203,5));
        frequencies.add(Frequency.newCtcssFrequency(210,7));
        frequencies.add(Frequency.newCtcssFrequency(218,1));
        frequencies.add(Frequency.newCtcssFrequency(225,7));
        frequencies.add(Frequency.newCtcssFrequency(233,6));
        frequencies.add(Frequency.newCtcssFrequency(241,8));
        frequencies.add(Frequency.newCtcssFrequency(250,3));
        sFrequencies = Collections.unmodifiableList(frequencies);
    }

    @Override
    protected List<Key> getKeys() {
        return sFrequencies;
    }

    @Override
    public Integer getNameStringId() {
        return R.string.ctcss39;
    }
}
