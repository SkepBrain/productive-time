package lt.karolio.productivetime;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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

    AppSectionsPagerAdapter mAppSectionsPagerAdapter;

    ViewPager mViewPager;
    public static Logger logger;
    public static int pomodoroTime = 25;
    public static int shortBreakTime = 5;
    public static int longBreakTime = 30;

    ActionBar actionBar;
    public ArrayList<View> buttons = new ArrayList<View>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		

        actionBar = getSupportActionBar();
        actionBar.hide();

        setButtons();
        mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mAppSectionsPagerAdapter);
       mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // When swiping between different app sections, select the corresponding tab.
                // We can also use ActionBar.Tab#select() to do this if we have a reference to the
                // Tab.
                switch (position){
                    case 0:
                        System.out.println(position);
                        buttons.get(0).setBackgroundResource(R.drawable.ic_clock_selected);
                        buttons.get(1).setBackgroundResource(R.drawable.ic_calendar);
                        buttons.get(2).setBackgroundResource(R.drawable.ic_stuff);
                        buttons.get(3).setBackgroundResource(R.drawable.ic_settings);
                        break;
                    case 1:
                        System.out.println(position);
                        buttons.get(0).setBackgroundResource(R.drawable.ic_clock);
                        buttons.get(1).setBackgroundResource(R.drawable.ic_calendar_selected);
                        buttons.get(2).setBackgroundResource(R.drawable.ic_stuff);
                        buttons.get(3).setBackgroundResource(R.drawable.ic_settings);
                        break;
                    case 2:
                        System.out.println(position);
                        buttons.get(0).setBackgroundResource(R.drawable.ic_clock);
                        buttons.get(1).setBackgroundResource(R.drawable.ic_calendar);
                        buttons.get(2).setBackgroundResource(R.drawable.ic_stuff_selected);
                        buttons.get(3).setBackgroundResource(R.drawable.ic_settings);
                        break;
                    case 3:
                        System.out.println(position);
                        buttons.get(0).setBackgroundResource(R.drawable.ic_clock);
                        buttons.get(1).setBackgroundResource(R.drawable.ic_calendar);
                        buttons.get(2).setBackgroundResource(R.drawable.ic_stuff);
                        buttons.get(3).setBackgroundResource(R.drawable.ic_settings_selected);
                        break;
                    default:
                        System.out.println(position + "meu");
                }
            }
        });
        mViewPager.setOffscreenPageLimit(5);
        /*mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
        	
			public void onPageSelected(int pageNumber) {
				(mAppSectionsPagerAdapter.getItem(pageNumber)).renew();
			}
			
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}
			
			public void onPageScrollStateChanged(int arg0) {
			}
        });*/
        logger = new Logger(this);
	}

       public class ShowOnClickListener implements OnClickListener{

        int position;
        public ShowOnClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v){
            mViewPager.setCurrentItem(position, true);
        }

    }

    public void setButtons(){
        buttons.add(findViewById(R.id.clockButton));
        buttons.add(findViewById(R.id.calendarButton));
        buttons.add(findViewById(R.id.stuffButton));
        buttons.add(findViewById(R.id.settingsButton));

        for(int i = 0; i < 4; i++){
            buttons.get(i).setOnClickListener(new ShowOnClickListener(i));
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
                	return ClockFragment.newInstance(pomodoroTime, shortBreakTime, longBreakTime);
                case 1:
                    return new StatsFragment();
                default:
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
}
