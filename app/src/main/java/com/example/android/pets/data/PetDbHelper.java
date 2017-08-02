package com.example.android.pets.data;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.android.pets.data.PetContract;
import com.example.android.pets.data.PetContract.PetEntry;

import java.util.ArrayList;


/**
 * Created by limeg_000 on 7/28/2017.
 */

public class PetDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "PetDbHelper.db";

    public PetDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_PETS_TABLE = "CREATE TABLE " + PetEntry.TABLE_NAME + " (" +
                PetEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                PetEntry.COLUMN_PET_NAME + " TEXT NOT NULL," +
                PetEntry.COLUMN_PET_BREED + " TEXT," +
                PetEntry.COLUMN_PET_SEX + " INTEGER NOT NULL," +
                PetEntry.COLUMN_PET_WEIGHT + " INTEGER NOT NULL DEFAULT 0);";

        db.execSQL(SQL_CREATE_PETS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
    }


    /*........................ CODE FOR DATABASE MANAGER IS BELOW ........................*/


public ArrayList<Cursor> getData(String Query){
    //get writable database
    SQLiteDatabase sqlDB = this.getWritableDatabase();
    String[] columns = new String[] { "message "};

    //an arraylist of cursor to save two cursors has results from the query
    //other cursor stores error message if any errors are triggered
    ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
    MatrixCursor Cursor2 = new MatrixCursor(columns);
    alc.add(null);
    alc.add(null);

    try{
        String maxQuery = Query;
        //execute the query results will be saved in Cursor c
        Cursor c = sqlDB.rawQuery(maxQuery, null);

        //add value to cursor 2
        Cursor2.addRow(new Object[] { "Success" });

        alc.set(1,Cursor2);
        if (null != c && c.getCount() > 0){
            alc.set(0,c);
            c.moveToFirst();

            return alc;
        }
        return alc;
    } catch(SQLException sqlEx){
        Log.d("Printing exception", sqlEx.getMessage());
        //if any exceptions are triggered save the error message to cursor and return the arraylist
        Cursor2.addRow(new Object[] { "" + sqlEx.getMessage() });
        alc.set(1,Cursor2);
        return alc;
    } catch(Exception ex){
        Log.d("Printing exception", ex.getMessage());

        //if any exceptions are triggered save the error message to cursor and return the arraylist
        Cursor2.addRow(new Object[] { "" + ex.getMessage() });
        alc.set(1,Cursor2);
        return alc;
    }
}


    /*..................................................................................*/

}
