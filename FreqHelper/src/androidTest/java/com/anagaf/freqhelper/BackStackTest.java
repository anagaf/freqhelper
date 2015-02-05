package com.anagaf.freqhelper;

import android.test.AndroidTestCase;

public class BackStackTest extends AndroidTestCase {
    public void test() {
        final BackStack backStack = BackStack.getsInstance();
        final Frequency frequency1 = new Frequency(475, 133);
        final Frequency frequency2 = new Frequency(446, 6, 250);
        assertNull(backStack.pop());
        backStack.push(frequency1);
        backStack.push(frequency2);
        backStack.push(frequency2);
        assertEquals(backStack.pop(), frequency2);
        assertEquals(backStack.pop(), frequency1);
        assertNull(backStack.pop());
    }
}