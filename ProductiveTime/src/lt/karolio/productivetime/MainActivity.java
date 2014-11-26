package lt.karolio.productivetime;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.os.Build;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements ClockFragment.OnFragmentInteractionListener {
	
	//public TextView clock;
	//

    AppSectionsPagerAdapter mAppSectionsPagerAdapter;

    ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(getSupportFragmentManager());

//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//        ClockFragment clockFragment = new ClockFragment();
//        fragmentTransaction.add(R.id.container, clockFragment);
//        fragmentTransaction.commit();

		setContentView(R.layout.activity_main);
        // Check whether the activity is using the layout version with
        // the fragment_container FrameLayout. If so, we must add the first fragment
//        if (findViewById(R.id.container) != null) {
//            System.out.println("After 1st if");
////            // However, if we're being restored from a previous state,
////            // then we don't need to do anything and should return or else
////            // we could end up with overlapping fragments.
//            if (savedInstanceState != null) {
//                System.out.println("bybys jau buvo");
//                return;
//            }
//            System.out.println("After 2nd if");
////            // Create an instance of ExampleFragment
//            ClockFragment firstFragment = new ClockFragment();
////
////            // In case this activity was started with special instructions from an Intent,
////            // pass the Intent's extras to the fragment as arguments
//       //     firstFragment.setArguments(getIntent().getExtras());
////
////            // Add the fragment to the 'fragment_container' FrameLayout
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.container, firstFragment).commit();
//
//        }
//        else
//            System.out.println(findViewById(R.id.container));
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mAppSectionsPagerAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // When swiping between different app sections, select the corresponding tab.
                // We can also use ActionBar.Tab#select() to do this if we have a reference to the
                // Tab.
                //       actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mAppSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by the adapter.
            // Also specify this Activity object, which implements the TabListener interface, as the
            // listener for when this tab is selected.
//            actionBar.addTab(
//                    actionBar.newTab()
//                            .setText(mAppSectionsPagerAdapter.getPageTitle(i))
//                           // .setTabListener(this));
//        }
        }
	}

    public static class AppSectionsPagerAdapter extends FragmentPagerAdapter {

        public AppSectionsPagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    // The first section of the app is the most interesting -- it offers
                    // a launchpad into the other demonstrations in this example application.
                    return new ClockFragment();

                case 1:
                    return new StatsFragment();
                default:
                    // The other sections of the app are dummy placeholders.
             //       Fragment fragment = new StatsFragment();
                  //  Bundle args = new Bundle();
                   // args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, i + 1);
                 //   fragment.setArguments(args);
                    return new FactsFragment();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Section " + (position + 1);
        }
    }



    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

    public void onFragmentInteraction(Uri uri){
        System.out.println(uri.toString());
    }

	/**
	 * A placeholder fragment containing a simple view.
	 */
//	public static class PlaceholderFragment extends Fragment {
//
//
//		@Override
//		public View onCreateView(LayoutInflater inflater, ViewGroup container,
//				Bundle savedInstanceState) {
//			View rootView = inflater.inflate(R.layout.fragment_main, container,
//					false);
//			return rootView;
//		}
//	}
}
