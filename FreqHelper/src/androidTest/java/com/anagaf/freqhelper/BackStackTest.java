package com.anagaf.freqhelper;

import android.test.AndroidTestCase;

import com.anagaf.freqhelper.model.keys.Frequency;

public class BackStackTest extends AndroidTestCase {
    public void test() {
        final BackStack backStack = BackStack.getsInstance();
        final BackStack.Item item1 = new BackStack.Item(3, Frequency.newChannelFrequency(475, 133, 0).getDeciHertz());
        final BackStack.Item item2 = new BackStack.Item(5, Frequency.newCtcssFrequency(34, 5).getDeciHertz());
        assertNull(backStack.pop());
        backStack.push(item1);
        backStack.push(item2);
        assertEquals(backStack.pop(), item2);
        assertEquals(backStack.pop(), item1);
        assertNull(backStack.pop());
    }
}
