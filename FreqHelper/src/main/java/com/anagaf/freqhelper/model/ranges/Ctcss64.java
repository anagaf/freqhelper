package com.anagaf.freqhelper.model.ranges;

import com.anagaf.freqhelper.R;
import com.anagaf.freqhelper.model.keys.Frequency;

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

public class Ctcss64 extends StaticRange {
    private static final List<Long> sFrequencies;
    static {
        List<Long> frequencies = new ArrayList<>();
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(33, 0));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(35, 4));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(36, 6));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(37, 9));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(39, 6));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(44, 4));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(47, 5));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(49, 2));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(51, 2));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(53, 0));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(54, 9));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(56, 8));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(58, 8));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(63, 0));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(67, 0));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(69, 4));
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
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(159, 8));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(162, 2));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(165, 5));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(167, 9));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(171, 3));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(173, 8));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(177, 3));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(179, 9));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(183, 5));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(186, 2));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(189, 9));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(192, 8));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(196, 6));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(199, 5));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(203, 5));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(206, 5));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(210, 7));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(218, 1));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(225, 7));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(229, 1));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(233, 6));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(241, 8));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(250, 3));
        frequencies.add(Frequency.getCtcssFrequencyDecihertz(254, 1));
        sFrequencies = Collections.unmodifiableList(frequencies);
    }

    @Override
    public Integer getNameStringId() {
        return R.string.ctcss64;
    }

    @Override
    protected List<Long> getValues() {
        return sFrequencies;
    }
}
