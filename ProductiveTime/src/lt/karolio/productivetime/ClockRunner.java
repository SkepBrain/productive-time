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
	private int type;
	private int startTime;
	private int endTime;
	private CountDownTimer countdown;
	private int pomodoroCount;
	private long shortBreakTime = 5;
	private long longBreakTime = 30;
	private long workingTime = 25;
	
	public ClockRunner(TextView clockView, int pTime, int sTime, int lTime) {
		clock = clockView;
		this.workingTime = pTime * 1000;
		this.shortBreakTime = sTime * 1000;
		this.longBreakTime = lTime * 1000;
		this.time = workingTime;
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
	
	public void stopTimer() {
		countdown.cancel();
		clock.setText("00:00");
		endTime = (int) (System.currentTimeMillis() / 1000L);
		if (type == 0) {
			MainActivity.logger.addPomodoro(startTime, endTime, "Papai", 0);
		}
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
                endTime = (int) (System.currentTimeMillis() / 1000L);
                if (type == 0) {
                	pomodoroCount++;
                	MainActivity.logger.addPomodoro(startTime, endTime, "Papai", 1);
                	if (pomodoroCount%3 == 0) {
                		setLongBreak();
                	}
                	else {
                		setShortBreak();
                	}
                }
                else {
                	time = workingTime;
                	type = 0;
                	setCountdown();
                	countdown.start();
                }
            }
        };
        startTime = (int) (System.currentTimeMillis() / 1000L);
    }
    
    private void setShortBreak() {
    	this.time = shortBreakTime;
    	this.type = 1;
    	setCountdown();
    	countdown.start();
    }
    
    private void setLongBreak() {
    	this.time = longBreakTime;
    	this.type = 2;
    	setCountdown();
    	countdown.start();
    }
}
