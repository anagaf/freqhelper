package com.anagaf.freqhelper.app;

import org.jetbrains.annotations.NotNull;

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
        @NotNull
        private final String mPageKey;
        private final long mValue;

        public Item(@NotNull String pageKey, long value) {
            mPageKey = pageKey;
            mValue = value;
        }

        @NotNull
        public String getPageKey() {
            return mPageKey;
        }

        public long getValue() {
            return mValue;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Item item = (Item) o;

            return mValue == item.mValue && mPageKey.equals(item.mPageKey);

        }

        @Override
        public int hashCode() {
            int result = mPageKey.hashCode();
            result = 31 * result + (int) (mValue ^ (mValue >>> 32));
            return result;
        }
    }
}
