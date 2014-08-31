package com.anagaf.freqhelper.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Ranges {

    private static List<Range> sAvailableRanges;

    static {
        List<Range> availableRanges = new ArrayList<Range>();
        availableRanges.add(new Lpd69());
        availableRanges.add(new Lpd8());
        availableRanges.add(new Pmr());
        sAvailableRanges = Collections.unmodifiableList(availableRanges);
    }

    public static List<Range> availableRanges() {
        return sAvailableRanges;
    }
}
