package com.iom.solve;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DatabaseManager {
	
//	DECLATION OF ALL THE VARIABLES AND CONSTANT THAT WILL BE USED TO CREATE THE TABLE
	
	private static final String DATABASE_NAME = "solve_database";
	private static final String DATABASE_TABLE = "solve_table";
	private static final int DATABASE_VERSION = 1;
	
	
//	DECLARATION OF ALL THE COLUMN REQUIRED TO BE CREATED
	
	public static final String KEY_ROWID = "_id";
	public static final String KEY_QUESTION = "question";
	public static final String KEY_TIME = "time";
	public static final String KEY_RESULT = "result";


	

	
	private DatabaseHelper mDbHelper; 
	private SQLiteDatabase ourDatabase;
	private final Context ourContext;
	
//	THIS IS THE ACTUAL CLASS USED TO CREATE THE DATABASE AND TABLE, IT IS NESTED IN THIS CLASS

//		Beginning if this class

//	This is creating a database dynamically, but in this case the database has been preloaded
	
	public class DatabaseHelper extends SQLiteOpenHelper{		
		
		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			}
		
		
			@Override
			public void onCreate(SQLiteDatabase db) {
				
			db.execSQL("create table " + DATABASE_TABLE + " ("
					+ KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ KEY_QUESTION + " text not null, "
					+ KEY_TIME + " text not null, "
					+ KEY_RESULT + " text not null);");
			
			}
			
			
			@Override
			public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
				db.execSQL("DROP TABLE IF EXIT "+ DATABASE_TABLE);
				onCreate(db);
			}

			

	}
	
//	End of this class
	
	
//	//	Constructor of this external class
	
	
	public DatabaseManager(Context context){
		ourContext = context;		
		
	}
	
//	constructor terminated
	
//	open the database for access
	
	public  DatabaseManager open() throws SQLException {
		mDbHelper = new DatabaseHelper(ourContext);
		ourDatabase = mDbHelper.getWritableDatabase();
		return this;
		}
	
//	Enter Values into the database or create database values
	
	public long createRecords(String question, String time, String result) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_QUESTION, question);
		initialValues.put(KEY_TIME, time);
		initialValues.put(KEY_RESULT, result);
		return ourDatabase.insert(DATABASE_TABLE, null, initialValues);	
		
	}
	
//	close the database after creating the values for security purposes
	public void close() {
		mDbHelper.close();
		}
	
//	Return all data via the cursor to the calling function
	public Cursor getAllData() throws SQLException{
		String[] columns = {KEY_ROWID, KEY_QUESTION, KEY_TIME, KEY_RESULT};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);		
		return c ;
		}
		
      public boolean deleteRecord(long rowId) {
	  return ourDatabase.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0 ;	 }
	
	
	 public Cursor fetchSingleRecord(long rowId) throws SQLException
	 {	String[] columns = {KEY_ROWID, KEY_QUESTION, KEY_TIME, KEY_RESULT};
	 	Cursor c =  ourDatabase.query(true, DATABASE_TABLE, columns , KEY_ROWID + "=" +  rowId, null, null, null, null, null);
	 	if (c != null) { c.moveToFirst(); }
	 	return c;
	 }
	 
	 
	//This session of code msy or may not be written here. i mean in this side of the database 
	 // this will get the string result of author
	public String getQuestion(Long rowId) {
		Cursor c2 = fetchSingleRecord(rowId);
		int rowIndex = c2.getColumnIndex(KEY_QUESTION);
		return c2.getString(rowIndex);
	}


	public String getTime(Long rowId) {
		Cursor c2 = fetchSingleRecord(rowId);
		int rowIndex = c2.getColumnIndex(KEY_TIME);
		return c2.getString(rowIndex);
	}

	public String getResult(Long rowId) {
		Cursor c2 = fetchSingleRecord(rowId);
		int rowIndex = c2.getColumnIndex(KEY_RESULT);
		return c2.getString(rowIndex);
	}

	//

	public void deleteDatabase(){
		ourContext.deleteDatabase(DATABASE_NAME);
	}


}    


