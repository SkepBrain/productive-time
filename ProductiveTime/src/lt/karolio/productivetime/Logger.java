package lt.karolio.productivetime;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.app.Activity;

public class Logger {
	private Database mDB;
	private SQLiteDatabase db;
	
	public Logger(Activity activity) {
		this.mDB = new Database(activity.getBaseContext());
	}
	
	public void addPomodoro(int start, int end, String cat, int isFinished) {
		this.db = mDB.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("start", start);
		cv.put("end", end);
		cv.put("category", cat);
		cv.put("isFinished", isFinished);
		db.insert("pomodoros", null, cv);
		db.close();
	}
	
	public int getPomodoroCount(int intervalStart, int intervalEnd) {
		this.db = mDB.getReadableDatabase();
		//Cursor cursor = db.rawQuery("SELECT * FROM pomodoros WHERE ((isFinished=1) AND (start BETWEEN "+intervalStart+" AND "+intervalEnd+") OR (end BETWEEN "+intervalStart+" AND "+intervalEnd+"))", null);
		Cursor cursor = db.rawQuery("SELECT * FROM pomodoros WHERE isFinished=1 AND ((start BETWEEN "+intervalStart+" AND "+intervalEnd+") OR (end BETWEEN "+intervalStart+" AND "+intervalEnd+"))", null);
		int count = cursor.getCount();
		return count;
	}
	
	public int getPomodoroTime(int intervalStart, int intervalEnd) {
		this.db = mDB.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM pomodoros WHERE (start BETWEEN "+intervalStart+" AND "+intervalEnd+") OR (end BETWEEN "+intervalStart+" AND "+intervalEnd+")", null);
		int time = 0;
		while (cursor.moveToNext()) {
		    time = time + (cursor.getInt(cursor.getColumnIndex("end")) - cursor.getInt(cursor.getColumnIndex("start")));
		}
		return time;
	}
}
