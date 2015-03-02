package com.anagaf.freqhelper.app;

import java.util.EmptyStackException;
import java.util.Stack;

public class BackStack {
    private final static BackStack sInstance = new BackStack();

    private final Stack<Item> mStack = new Stack<>();

    public static BackStack getsInstance() {
        return sInstance;
    }

    public void push(Item item) {
        if (mStack.isEmpty() || !mStack.peek().equals(item)) {
            mStack.push(item);
        }
    }

    public Item pop() {
        try {
            return mStack.pop();
        } catch (EmptyStackException ex) {
            return null;
        }
    }

    public static class Item {
        private final int mPageIndex;
        private final long mValue;

        public Item(int pageIndex, long value) {
            mPageIndex = pageIndex;
            mValue = value;
        }

        public int getPageIndex() {
            return mPageIndex;
        }

        public long getValue() {
            return mValue;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Item item = (Item) o;

            return mPageIndex == item.mPageIndex && mValue == item.mValue;

        }

        @Override
        public int hashCode() {
            int result = mPageIndex;
            result = 31 * result + (int) (mValue ^ (mValue >>> 32));
            return result;
        }
    }
}
