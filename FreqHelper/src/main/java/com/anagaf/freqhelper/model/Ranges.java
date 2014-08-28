package com.anagaf.freqhelper.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Ranges {

    private static Collection<Range> sAvailableRanges;

    static {
        List<Range> availableRanges = new ArrayList<Range>();
        availableRanges.add(new Lpd8());
        availableRanges.add(new Lpd69());
        availableRanges.add(new Pmr());
        sAvailableRanges = Collections.unmodifiableCollection(availableRanges);
    }

    public static Collection<Range> availableRanges() {
        return sAvailableRanges;
    }
}
