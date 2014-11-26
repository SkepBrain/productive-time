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
        setCountdown();
        System.out.println("New time: " + this.time);
        countdown.start();
	}
	
	public void pauseTimer() {
		countdown.cancel();
		isStarted = false;
	}
	
	public void resumeTimer() {
        setCountdown();
        System.out.println("Resumed time: " + this.time);
        isStarted = true;
		countdown.start();
	}
	
	public boolean getStatus() {
		return isStarted;
	}


    private void setCountdown(){
        countdown = new CountDownTimer(time, 1) {
            @Override
            public void onTick(long millisUntilFinished) {
                mins = String.format(Locale.US, "%02d", Math.round((float)millisUntilFinished / 1000) / 60);
                secs = String.format(Locale.US, "%02d", Math.round((float)millisUntilFinished / 1000) % 60);
                clock.setText(mins+":"+secs);
                time = millisUntilFinished;
            }

            @Override
            public void onFinish() {
                clock.setText("Whoila!");
            }
        };
    }
}
