package com.anagaf.freqhelper;

import java.util.EmptyStackException;
import java.util.Stack;

public class BackStack {
    private final static BackStack sInstance = new BackStack();

    private final Stack<Frequency> mStack = new Stack<>();

    public static BackStack getsInstance() {
        return sInstance;
    }

    public void push(Frequency frequency) {
        if (mStack.isEmpty() || !mStack.peek().equals(frequency)) {
            mStack.push(frequency);
        }
    }

    public Frequency pop() {
        try {
            return mStack.pop();
        } catch (EmptyStackException ex) {
            return null;
        }
    }
}
