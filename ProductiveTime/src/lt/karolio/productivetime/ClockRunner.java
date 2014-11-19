package lt.karolio.productivetime;

import java.util.Locale;

import android.os.CountDownTimer;
import android.widget.TextView;

public class ClockRunner {
	private boolean isStarted = true;
	private long time;
	private TextView clock;
	private String mins;
	private String secs;
	private CountDownTimer countdown;
	
	public ClockRunner(TextView clockView, int time) {
		clock = clockView;
		this.time = time * 1000;
		countdown = new CountDownTimer(this.time, 1000) {
		     public void onTick(long millisUntilFinished) {
		    	 mins = String.format(Locale.US, "%02d", (millisUntilFinished / 1000) / 60);
		    	 secs = String.format(Locale.US, "%02d", (millisUntilFinished / 1000) % 60);
		         clock.setText(mins+":"+secs);
		     }
		     public void onFinish() {
		         clock.setText("Whoila!");
		     }
		};
		countdown.start();
	}
	
	public void pauseTimer() {
		countdown.cancel();
		isStarted = false;
	}
	
	public void resumeTimer() {
		time = Integer.parseInt(mins) * 60 + Integer.parseInt(secs);
		countdown = new CountDownTimer(time, 1000) {
		     public void onTick(long millisUntilFinished) {
		    	 mins = String.format(Locale.US, "%02d", (millisUntilFinished / 1000) / 60);
		    	 secs = String.format(Locale.US, "%02d", (millisUntilFinished / 1000) % 60);
		         clock.setText(mins+":"+secs);
		     }
		     public void onFinish() {
		         clock.setText("Whoila!");
		     }
		};
		isStarted = true;
		countdown.start();
	}
	
	public boolean getStatus() {
		return isStarted;
	}

}
