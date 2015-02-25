package com.anagaf.freqhelper;

import android.test.AndroidTestCase;

import com.anagaf.freqhelper.model.Frequency;

public class BackStackTest extends AndroidTestCase {
    public void test() {
        final BackStack backStack = BackStack.getsInstance();
        final BackStack.Item item1 = new BackStack.Item(3, Frequency.getChannelFrequencyDecihertz(475, 133, 0));
        final BackStack.Item item2 = new BackStack.Item(5, Frequency.getCtcssFrequencyDecihertz(34, 5));
        assertNull(backStack.pop());
        backStack.push(item1);
        backStack.push(item2);
        assertEquals(backStack.pop(), item2);
        assertEquals(backStack.pop(), item1);
        assertNull(backStack.pop());
    }
}
