package com.a37do.ssumnow;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TabFragment1 tab1;
    private TabFragment2 tab2;
    private TabFragment3 tab3;
    private Animation animBounceUp;
    private Animation animBounceUpRight;
    private Animation animBounceUpLeft;
    private Animation animBounceDown;
    private Animation animBounceDownRight;
    private Animation animBounceDownLeft;
    private FloatingActionButton fabLike;
    private FloatingActionButton fabSuperLike;
    private FloatingActionButton fabNo;
    private FloatingActionButton fabBack;
    private boolean isFabUp = true;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //View to be drawn under status bar
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        tab1 = new TabFragment1();
        tab2 = new TabFragment2();
        tab3 = new TabFragment3();

        fabLike = (FloatingActionButton) findViewById(R.id.fab_like);
        fabSuperLike = (FloatingActionButton) findViewById(R.id.fab_superlike);
        fabNo = (FloatingActionButton) findViewById(R.id.fab_no);
        fabBack = (FloatingActionButton) findViewById(R.id.fab_back);

        animBounceDown = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce_down);
        animBounceUp = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce_up);
        animBounceUpRight = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce_up_right);
        animBounceUpLeft = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce_up_left);
        animBounceDownRight = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce_down_right);
        animBounceDownLeft = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce_down_left);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        final TabLayout.Tab tab1 = tabLayout.getTabAt(0);
        final TabLayout.Tab tab2 = tabLayout.getTabAt(1);
        final TabLayout.Tab tab3 = tabLayout.getTabAt(2);

        tab1.setIcon(R.drawable.selector_tab1).setCustomView(R.layout.tab_icon);
        tab2.setIcon(R.drawable.selector_tab2).setCustomView(R.layout.tab_icon);
        tab3.setIcon(R.drawable.selector_tab3).setCustomView(R.layout.tab_icon);

        tabLayout.getTabAt(1).select();
        tabLayout.getTabAt(0).select();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_like);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ViewPager.SimpleOnPageChangeListener pageChangeListener = new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        moveFabUp();
                        break;
                    case 1:
                        moveFabDown();
                        break;
                    case 2:
                        moveFabDown();
                        break;

                    default:
                        break;
                }
            }
        };

        mViewPager.addOnPageChangeListener(pageChangeListener);

    }

    private void moveFabUp() {
        if (!isFabUp) {
            fabLike.startAnimation(animBounceUp);
            fabNo.startAnimation(animBounceUp);
            fabBack.startAnimation(animBounceUpRight);
            fabSuperLike.startAnimation(animBounceUpLeft);
            isFabUp = true;
        }
    }

    private void moveFabDown() {
        if (isFabUp) {
            fabLike.startAnimation(animBounceDown);
            fabNo.startAnimation(animBounceDown);
            fabBack.startAnimation(animBounceDownLeft);
            fabSuperLike.startAnimation(animBounceDownRight);
            isFabUp = false;
        }
    }
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment
            switch (position) {
                case 0:
                    return tab1;
                case 1:
                    return tab2;
                case 2:
                    return tab3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }
}
