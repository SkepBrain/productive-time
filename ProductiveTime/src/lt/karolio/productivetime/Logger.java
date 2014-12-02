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
	
	public void addPomodoro(int start, int end, String cat) {
		this.db = mDB.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("start", start);
		cv.put("end", end);
		cv.put("category", cat);
		db.insert("pomodoros", null, cv);
		db.close();
	}
	
	public int getPomodoroCount() {
		this.db = mDB.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM pomodoros", null);
		int count = cursor.getCount();
		db.close();
		return count;
	}
}
