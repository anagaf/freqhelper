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
            dcsPage = (DcsPage) getSupportFragmentManager().getFragment(savedInstanceState, DcsPage.KEY);
            channelsPage = (ChannelsPage) getSupportFragmentManager().getFragment(savedInstanceState, ChannelsPage.KEY);
            ctcssPage = (CtcssPage) getSupportFragmentManager().getFragment(savedInstanceState, CtcssPage.KEY);
        } else {
            dcsPage = new DcsPage();
            channelsPage = new ChannelsPage();
            ctcssPage = new CtcssPage();
        }
        mPages.add(new PageInfo(dcsPage, R.string.dcs));
        mPages.add(new PageInfo(channelsPage, R.string.channels));
        mPages.add(new PageInfo(ctcssPage, R.string.ctcss));

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
            final Page page = pageInfo.getPage();
            getSupportFragmentManager().putFragment(outState, page.getKey(), page);
        }
    }

    @Override
    public void onBackPressed() {
        mFocusThief.requestFocus();

        final BackStack.Item item = BackStack.getsInstance().pop();
        if (item == null) {
            super.onBackPressed();
        } else {
            final String pageKey = item.getPageKey();
            final int pageIndex = getPageIndexByKey(pageKey);
            final boolean shouldPopToPreventLoop = mCurrentPageIndex > 0
                    && pageIndex != mCurrentPageIndex;
            mViewPager.setCurrentItem(pageIndex);
            if (shouldPopToPreventLoop) {
                BackStack.getsInstance().pop();
            }
            final Page page = mPages.get(pageIndex).getPage();
            page.restoreState(item.getValue());
        }
    }

    private int getPageIndexByKey(String pageKey) {
        int index = -1;
        for (int i = 0; i < mPages.size(); i++) {
            if (mPages.get(i).getPage().getKey().equals(pageKey)) {
                index = i;
                break;
            }
        }
        return index;
    }


    /**
     * ******* Inner Classes *********
     */

    private static class PageInfo {
        private final Page mPage;
        private final int mTitleResId;

        private PageInfo(Page page, int titleResId) {
            mPage = page;
            mTitleResId = titleResId;
        }

        public Page getPage() {
            return mPage;
        }

        public int getTitleResId() {
            return mTitleResId;
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
