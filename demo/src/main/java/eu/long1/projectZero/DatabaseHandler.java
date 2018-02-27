package eu.long1.projectZero;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

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
                + KEY_ID + " TEXT PRIMARY KEY," + KEY_CHARACTER + " TEXT,"
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

    public void addCase(SingleCase c) {
        //initialize db
        SQLiteDatabase db = this.getWritableDatabase();

        //add value
        ContentValues values = new ContentValues();
        values.put(KEY_ID, c.getID());
        values.put(KEY_CHARACTER, c.getCharacter());
        values.put(KEY_REASON, c.getReason());

        //insert and close connection
        db.insert(TABLE_CASE, null, values);
        db.close();
    }

    public SingleCase getCase(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CASE, new String[] { KEY_ID,
                KEY_CHARACTER, KEY_REASON }, KEY_ID + "=?",
                new String[] { String.valueOf(id) },
                null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        SingleCase c = new SingleCase(cursor.getString(0),
                cursor.getString(1), cursor.getString(2));

        return c;
    }

    public List<SingleCase> getAllCases() {
        List<SingleCase> caseList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM" + TABLE_CASE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                SingleCase c = new SingleCase();
                c.setID(cursor.getString(0));
                c.setCharacter(cursor.getString(1));
                c.setReason(cursor.getString(2));

                //add to list
                caseList.add(c);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return caseList;
    }

    public Integer getCaseCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CASE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    public Integer updateCase(SingleCase c) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CHARACTER, c.getCharacter());
        values.put(KEY_REASON, c.getReason());

        return db.update(TABLE_CASE, values, KEY_ID + " = ?",
                new String[] { String.valueOf(c.getID()) });
    }

    public void deleteCase(SingleCase c) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CASE, KEY_ID + " = ? ",
                new String[] { String.valueOf(c.getID()) });
        db.close();
    }


}
