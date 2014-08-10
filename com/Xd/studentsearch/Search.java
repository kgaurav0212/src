package com.Xd.studentsearch;

import java.io.FileOutputStream;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Search {

	public static final String KEY_ROWID = "row_id";
	public static final String KEY_NAME = "persons_name";
	public static final String KEY_EMAIL = "persons_email";
	public static final String KEY_ROLL = "persons_roll";
	public static final String KEY_HALL = "persons_hall";
	public static final String KEY_ROOM = "persons_room";
	public static final String KEY_BATCH = "persons_batch";
	public static final String KEY_PROG = "persons_programme";
	public static final String KEY_DEPT = "persons_department";
	public static final String KEY_GENDER = "persons_gender";
	public static final String KEY_BGROUP = "persons_bloodg";
	private static final String DATABASE_NAME = "Students";
	private static final String DATABASE_TABLE = "st_table";
	private static final int DATABASE_VERSION = 1;
	private DbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	String FILENAME = "Full_Data";
	FileOutputStream fos = null;
	String[] columns = new String[] { KEY_ROWID, KEY_NAME, KEY_ROLL, KEY_DEPT,
			KEY_PROG, KEY_BATCH, KEY_GENDER, KEY_HALL, KEY_ROOM, KEY_EMAIL,
			KEY_BGROUP };

	public class DbHelper extends SQLiteOpenHelper {

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " ( " + KEY_ROWID
					+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME
					+ " TEXT NOT NULL, " + KEY_BGROUP + " TEXT NOT NULL, "
					+ KEY_EMAIL + " TEXT NOT NULL, " + KEY_ROLL
					+ " TEXT NOT NULL, " + KEY_DEPT + " TEXT NOT NULL, "
					+ KEY_PROG + " TEXT NOT NULL, " + KEY_HALL
					+ " TEXT NOT NULL, " + KEY_ROOM + " TEXT NOT NULL, "
					+ KEY_GENDER + " TEXT NOT NULL, " + KEY_BATCH
					+ " TEXT NOT NULL);");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXIST " + DATABASE_TABLE);
			onCreate(db);
		}
	}

	public Search(Context c) {
		ourContext = c;
	}

	public Search open() throws SQLException {
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		ourHelper.close();
	}

	public long createEntry(String roll, String name, String prog, String dept,
			String hall, String email, String bgroup, String gender,
			String room, String batch) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();

		cv.put(KEY_ROLL, roll);
		cv.put(KEY_NAME, name);
		cv.put(KEY_PROG, prog);
		cv.put(KEY_BATCH, batch);
		cv.put(KEY_DEPT, dept);
		cv.put(KEY_GENDER, gender);
		cv.put(KEY_BGROUP, bgroup);
		cv.put(KEY_EMAIL, email);
		cv.put(KEY_HALL, hall);
		cv.put(KEY_ROOM, room);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
	}

	public Cursor getData(String name, String dept, String batch, String prog,
			String hall, String gender) {
		Integer rRoll;
		// TODO Auto-generated method stub
		char cname[] = name.toCharArray();
		// dept = new String(defnull(dept));
		// prog = new String(defnull(prog));
		// gender = new String(defnull(gender));
		// name = new String(defnull(name));
		if (cname[1] <= '9' && cname[1] >= '0')
			name = KEY_ROLL + "=" + name;
		else if (name.contains("@")) {
			name = KEY_EMAIL + " LIKE '%" + name + "%'";
		} else if (!name.contentEquals("NOT NULL"))
			name = KEY_NAME + " LIKE '%" + name + "%'";
		else
			name = KEY_NAME + " IS NOT NULL";
		if (!dept.contentEquals("NOT NULL"))
			dept = KEY_DEPT + " LIKE '%" + dept + "%'";
		else
			dept = KEY_DEPT + " IS NOT NULL";
		if (!batch.contentEquals("NOT NULL"))
			batch = KEY_BATCH + " = " + batch;
		else
			batch = KEY_BATCH + " IS NOT NULL";
		if (!prog.contentEquals("NOT NULL"))
			prog = KEY_PROG + " LIKE '%" + prog + "%'";
		else
			prog = KEY_PROG + " IS NOT NULL";
		if (!hall.contentEquals("NOT NULL"))
			hall = KEY_HALL + " LIKE '%" + hall + "%'";
		else
			hall = KEY_HALL + " IS NOT NULL";
		if (!gender.contentEquals("NOT NULL"))
			gender = KEY_GENDER + " LIKE '%" + gender + "%'";
		else
			gender = KEY_GENDER + " IS NOT NULL";

		return ourDatabase.query(DATABASE_TABLE, columns, name + " AND " + dept
				+ " AND " + prog + " AND " + batch + " AND " + hall + " AND "
				+ gender, null, null, null, null);

	}

	public String getfullData() {
		// TODO Auto-generated method stub
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null,
				null, null);
		String result = "";
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			result = result + c.getString(c.getColumnIndex(KEY_ROWID))
					+ c.getString(c.getColumnIndex(KEY_NAME)) + "\n";
		}
		return result;
	}

	public void DeleteEntry(long lRow1) throws SQLException {
		// TODO Auto-generated method stub
		ourDatabase.delete(DATABASE_TABLE, KEY_ROWID + "=" + lRow1, null);
	}
}
