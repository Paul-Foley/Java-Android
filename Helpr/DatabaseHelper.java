package com.paulfoleyblogs.Helpr.Helpr;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by paulf on 12/02/2017.
 */

class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "User.db";
    private static final String DATABASE_TABLE_CONTACT = "userContact_table";
    private static final String CHILDNAME = "childName";
    private static final String GUARDIANCONTACT = "guardianContactPhone";
    private static final String GUARDIANEMAIL = "guardianEmail";
    private static final String EMAILPREF = "emailPref";
    private static final String PHONEPREF = "phonePref";
    private static final String DATABASE_TABLE_ACTION = "userAction_table";
    private static final String ACTION = "action";
    private static final String DATE = "date";
    private Cursor res;

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + DATABASE_TABLE_CONTACT + " (" + CHILDNAME + " TEXT," + GUARDIANCONTACT + " TEXT, Timestamp " + DATE + " DEFAULT CURRENT_DATE, " + GUARDIANEMAIL + " TEXT, " + EMAILPREF + " TEXT," + PHONEPREF + " TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + DATABASE_TABLE_ACTION + " (" + ACTION + " TEXT, Timestamp DEFAULT CURRENT_DATE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_CONTACT);
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_ACTION);
        onCreate(db);

    }

    void clean() {

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE" +
                " FROM " + DATABASE_TABLE_ACTION + " WHERE Timestamp <date('now', '-4 days')");
    }

    boolean insertDataToUserContact(String childName, String guardianPhone, String guardianEmail, String emailPrf, String phonePref) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CHILDNAME, childName);
        contentValues.put(GUARDIANCONTACT, guardianPhone);
        contentValues.put(GUARDIANEMAIL, guardianEmail);
        contentValues.put(EMAILPREF, emailPrf);
        contentValues.put(PHONEPREF, phonePref);
        long result = db.insert(DATABASE_TABLE_CONTACT, null, contentValues);
        return result != -1;
    }

    void insertDataToAction(String action) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ACTION, action);
        db.insert(DATABASE_TABLE_ACTION, null, contentValues);
    }

    boolean updateDataToUserContact(String childName, String guardianPhone, String guardianEmail, String emailPrf, String phonePref) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CHILDNAME, childName);
        contentValues.put(GUARDIANCONTACT, guardianPhone);
        contentValues.put(GUARDIANEMAIL, guardianEmail);
        contentValues.put(EMAILPREF, emailPrf);
        contentValues.put(PHONEPREF, phonePref);
        long result = db.update(DATABASE_TABLE_CONTACT, contentValues, null, null);
        return result != -1;
    }

    String getChildName() {
        String childName;
        SQLiteDatabase db = this.getWritableDatabase();
        res = db.rawQuery("select +" + CHILDNAME + " from " + DATABASE_TABLE_CONTACT, null);
        res.moveToFirst();
        childName = res.getString(0);

        return childName;
    }

    int getGraphdata(String action, int num) {
        int total;
        SQLiteDatabase db = this.getWritableDatabase();
        res = db.rawQuery("SELECT COUNT(*) AS num FROM " + DATABASE_TABLE_ACTION + " where action='" + action + "' and Timestamp =date('now', '-" + num + " days');", null);
        res.moveToFirst();
        total = res.getInt(0);

        return total;
    }


    String getContactNumber() {
        String contact;
        SQLiteDatabase db = this.getWritableDatabase();
        res = db.rawQuery("select +" + GUARDIANCONTACT + " from " + DATABASE_TABLE_CONTACT, null);
        res.moveToFirst();
        contact = res.getString(0);

        return contact;
    }

    String getContactEmail() {
        String contactEmail;
        SQLiteDatabase db = this.getWritableDatabase();
        res = db.rawQuery("select +" + GUARDIANEMAIL + " from " + DATABASE_TABLE_CONTACT, null);
        res.moveToFirst();
        contactEmail = res.getString(0);

        return contactEmail;
    }

    String getEmailPref() {
        String emailPref;
        SQLiteDatabase db = this.getWritableDatabase();
        res = db.rawQuery("select +" + EMAILPREF + " from " + DATABASE_TABLE_CONTACT, null);
        res.moveToFirst();
        emailPref = res.getString(0);

        return emailPref;
    }

    String getMsgPref() {
        String phonePref;
        SQLiteDatabase db = this.getWritableDatabase();
        res = db.rawQuery("select +" + PHONEPREF + " from " + DATABASE_TABLE_CONTACT, null);
        res.moveToFirst();
        phonePref = res.getString(0);

        return phonePref;
    }

    int checkData() {
        SQLiteDatabase db = this.getWritableDatabase();
        res = db.rawQuery("Select * from " + DATABASE_TABLE_CONTACT, null);
        return res.getCount();

    }
}
