package com.anagaf.freqhelper.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.anagaf.freqhelper.R;
import com.anagaf.freqhelper.app.BackStack;
import com.anagaf.freqhelper.ui.pages.ChannelsPage;
import com.anagaf.freqhelper.ui.pages.CtcssPage;
import com.anagaf.freqhelper.ui.pages.DcsPage;
import com.anagaf.freqhelper.ui.pages.Page;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    private final static String DCS_PAGE_TAG = "dcsPage";
    private final static String CHANNELS_PAGE_TAG = "channelsPage";
    private final static String CTCSS_PAGE_TAG = "ctcssPage";

    private final List<PageInfo> mPages = new ArrayList<>();
    private ViewPager mViewPager;
    private int mCurrentPageIndex = -1;
    private View mFocusThief;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final Page dcsPage;
        final Page channelsPage;
        final Page ctcssPage;
        if (savedInstanceState != null) {
            dcsPage = (DcsPage) getSupportFragmentManager().getFragment(savedInstanceState, DCS_PAGE_TAG);
            channelsPage = (ChannelsPage) getSupportFragmentManager().getFragment(savedInstanceState, CHANNELS_PAGE_TAG);
            ctcssPage = (CtcssPage) getSupportFragmentManager().getFragment(savedInstanceState, CTCSS_PAGE_TAG);
        } else {
            dcsPage = createPage(new DcsPage(), 0);
            channelsPage = createPage(new ChannelsPage(), 1);
            ctcssPage = createPage(new CtcssPage(), 2);
        }
        mPages.add(new PageInfo(dcsPage, DCS_PAGE_TAG, R.string.dcs));
        mPages.add(new PageInfo(channelsPage, CHANNELS_PAGE_TAG, R.string.channels));
        mPages.add(new PageInfo(ctcssPage, CTCSS_PAGE_TAG, R.string.ctcss));

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(new Adapter(getSupportFragmentManager()));
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {
            }

            @Override
            public void onPageSelected(int index) {
                if (mCurrentPageIndex > 0 && index != mCurrentPageIndex) {
                    mPages.get(mCurrentPageIndex).getPage().pushCurrentStateToBackStack();
                }
                mCurrentPageIndex = index;
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
        mViewPager.setCurrentItem(1); // channels

        mFocusThief = findViewById(R.id.focus_thief);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        for (PageInfo pageInfo : mPages) {
            getSupportFragmentManager().putFragment(outState, pageInfo.getTag(), pageInfo.getPage());
        }
    }

    @Override
    public void onBackPressed() {
        mFocusThief.requestFocus();

        final BackStack.Item item = BackStack.getsInstance().pop();
        if (item == null) {
            super.onBackPressed();
        } else {
            final boolean shouldPopToPreventLoop = mCurrentPageIndex > 0
                    && item.getPageIndex() != mCurrentPageIndex;
            mViewPager.setCurrentItem(item.getPageIndex());
            if (shouldPopToPreventLoop) {
                BackStack.getsInstance().pop();
            }
            final Page page = mPages.get(item.getPageIndex()).getPage();
            page.restoreState(item.getValue());
        }
    }

    private Page createPage(Page page, int index) {
        final Bundle args = new Bundle();
        args.putInt(Page.PAGE_INDEX_KEY, index);
        page.setArguments(args);
        return page;
    }

    /**
     * ******* Inner Classes *********
     */

    private static class PageInfo {
        private final Page mPage;
        private final String mTag;
        private final int mTitleResId;

        private PageInfo(Page page, String tag, int titleResId) {
            mPage = page;
            mTag = tag;
            mTitleResId = titleResId;
        }

        public Page getPage() {
            return mPage;
        }

        public int getTitleResId() {
            return mTitleResId;
        }

        public String getTag() {
            return mTag;
        }
    }

    private class Adapter extends FragmentPagerAdapter {

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mPages.get(position).getPage();
        }

        @Override
        public int getCount() {
            return mPages.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getString(mPages.get(position).getTitleResId());
        }
    }
}
