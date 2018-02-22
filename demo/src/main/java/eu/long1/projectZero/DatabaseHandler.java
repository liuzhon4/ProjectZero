package eu.long1.projectZero;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Edward on 2018/2/22.
 * Handler for SQLite Database
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "caseManager";
    private static final String TABLE_CASE = "singleCase";
    private static final String KEY_ID = "id";
    private static final String KEY_CHARACTER = "character";
    private static final String KEY_REASON = "reason";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CASE + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_CHARACTER + " TEXT,"
                + KEY_REASON + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    //Update Tables
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CASE);

        // Create tables again
        onCreate(db);
    }
}
