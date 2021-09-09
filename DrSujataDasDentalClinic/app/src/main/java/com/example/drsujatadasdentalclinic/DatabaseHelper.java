package com.example.drsujatadasdentalclinic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import static com.example.drsujatadasdentalclinic.NewAppointment.patients;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "patients_db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        // create patient_records table
        db.execSQL(PatientRecords.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + PatientRecords.TABLE_NAME);
        // Create tables again
        onCreate(db);
    }

    public long insertPatient(String name, String gender, String phone){
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(PatientRecords.COLUMN_NAME, name);
        values.put(PatientRecords.COLUMN_GENDER, gender);
        values.put(PatientRecords.COLUMN_PHONE, phone);

        // insert row
        long id = db.insert(PatientRecords.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public PatientRecords getPatientRecord(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(PatientRecords.TABLE_NAME,
                new String[]{PatientRecords.COLUMN_ID, PatientRecords.COLUMN_NAME, PatientRecords.COLUMN_GENDER, PatientRecords.COLUMN_PHONE },
                PatientRecords.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        PatientRecords patientRecord = new PatientRecords(
                cursor.getInt(cursor.getColumnIndex(PatientRecords.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(PatientRecords.COLUMN_NAME)),
                cursor.getString(cursor.getColumnIndex(PatientRecords.COLUMN_GENDER)),
                cursor.getString(cursor.getColumnIndex(PatientRecords.COLUMN_PHONE)));

        // close the db connection
        cursor.close();

        return patientRecord;
    }

    public void populatePatients(){
        // Select All Query
        String selectQuery = "SELECT  * FROM " + PatientRecords.TABLE_NAME + " ORDER BY " +
                PatientRecords.COLUMN_NAME + " ASC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                patients.add(cursor.getString(cursor.getColumnIndex(PatientRecords.COLUMN_NAME)));
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();
    }
}
