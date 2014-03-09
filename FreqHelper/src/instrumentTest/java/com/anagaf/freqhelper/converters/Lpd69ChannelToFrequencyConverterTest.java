package com.anagaf.freqhelper.converters;

import android.test.AndroidTestCase;

import com.anagaf.freqhelper.Frequency;

public class Lpd69ChannelToFrequencyConverterTest extends AndroidTestCase {

    public void testGetFrequency() {
        Lpd69ChannelToFrequencyConverter converter = new Lpd69ChannelToFrequencyConverter();
        assertTrue(converter.getFrequency(1).equals(new Frequency(433, 75)));
        assertTrue(converter.getFrequency(69).equals(new Frequency(434, 775)));
    }
}
