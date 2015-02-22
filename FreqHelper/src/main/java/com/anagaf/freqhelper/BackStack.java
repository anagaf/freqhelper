package com.anagaf.freqhelper;

import com.anagaf.freqhelper.model.Key;

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
        private int mPageIndex;
        private Key mKey;

        public Item(int pageIndex, Key key) {
            mPageIndex = pageIndex;
            mKey = key;
        }

        public int getPageIndex() {
            return mPageIndex;
        }

        public Key getKey() {
            return mKey;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Item item = (Item) o;

            return mPageIndex == item.mPageIndex && mKey.equals(item.mKey);

        }

        @Override
        public int hashCode() {
            int result = mPageIndex;
            result = 31 * result + mKey.hashCode();
            return result;
        }
    }
}
