package com.anagaf.freqhelper.model;

import com.anagaf.freqhelper.BuildConfig;
import com.anagaf.freqhelper.Frequency;
import com.anagaf.freqhelper.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
1	33.0	17	71.9	33	123.0	49	183.5
2	35.4	18	74.4	34	127.3	50	186.2
3	36.6	19	77.0	35	131.8	51	189.9
4	37.9	20	79.7	36	136.5	52	192.8
5	39.6	21	82.5	37	141.3	53	196.6
6	44.4	22	85.4	38	146.2	54	199.5
7	47.5	23	88.5	39	151.4	55	203.5
8	49.2	24	91.5	40	156.7	56	206.5
9	51.2	25	94.8	41	159.8	57	210.7
10	53.0	26	97.4	42	162.2	58	218.1
11	54.9	27	100.0	43	165.5	59	225.7
12	56.8	28	103.5	44	167.9	60	229.1
13	58.8	29	107.2	45	171.3	61	233.6
14	63.0	30	110.9	46	173.8	62	241.8
15	67.0	31	114.8	47	177.3	63	250.3
16	69.4	32	118.8	48	179.9	64	254.1
*/

public class Ctcss64 extends AbstractStaticRange {
    private static final List<Frequency> sFrequencies;
    static {
        List<Frequency> frequencies = new ArrayList<>();
        frequencies.add(Frequency.newCtcssFrequency(33, 0));
        frequencies.add(Frequency.newCtcssFrequency(35, 4));
        frequencies.add(Frequency.newCtcssFrequency(36, 6));
        frequencies.add(Frequency.newCtcssFrequency(37, 9));
        frequencies.add(Frequency.newCtcssFrequency(39, 6));
        frequencies.add(Frequency.newCtcssFrequency(44, 4));
        frequencies.add(Frequency.newCtcssFrequency(47, 5));
        frequencies.add(Frequency.newCtcssFrequency(49, 2));
        frequencies.add(Frequency.newCtcssFrequency(51, 2));
        frequencies.add(Frequency.newCtcssFrequency(53, 0));
        frequencies.add(Frequency.newCtcssFrequency(54, 9));
        frequencies.add(Frequency.newCtcssFrequency(56, 8));
        frequencies.add(Frequency.newCtcssFrequency(58, 8));
        frequencies.add(Frequency.newCtcssFrequency(63, 0));
        frequencies.add(Frequency.newCtcssFrequency(67, 0));
        frequencies.add(Frequency.newCtcssFrequency(69, 4));
        sFrequencies = Collections.unmodifiableList(frequencies);
    }

    @Override
    public Integer getNameStringId() {
        return R.string.ctcss64;
    }

    @Override
    protected List<Frequency> getFrequencies() {
        return sFrequencies;
    }
}
