package com.example.kavit.project;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by kavit on 4/16/2017.
 */

public class DatabaseHandler extends AppCompatActivity {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Group20.db";
    public static final String _ID = "_id";
    public static final String TABLE_NAME = "MovieTable";
    public static final String MOVIE_NAME = "MovieName";


    protected Context context;
    public DatabaseHandler(Context context){
        this.context = context;
    }

    public void createDatabase(Context context) {
        SQLiteDatabase dbhandler = null;
        try{
            dbhandler = context.openOrCreateDatabase( DATABASE_NAME,MODE_PRIVATE, null );
            dbhandler.beginTransaction();
            try{

                dbhandler.execSQL("CREATE TABLE IF NOT EXISTS "+ TABLE_NAME + " ("+ _ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , "
                        + MOVIE_NAME+ " text not null, "+"Y_Val_1 float, "+"Z_Val_1 float  ); "
                );
                dbhandler.setTransactionSuccessful();
            }
            catch (SQLiteException e) {
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            }
            finally {
                dbhandler.endTransaction();
                dbhandler.close();
            }
        }catch (SQLException e){
            Toast.makeText( context , e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void insertIntoDB(Context context) {
        SQLiteDatabase dbhandler = null;
        try{
            dbhandler = context.openOrCreateDatabase( DATABASE_NAME,MODE_PRIVATE, null );
            dbhandler.beginTransaction();

            try{

                dbhandler.execSQL( "INSERT INTO "
                        + TABLE_NAME +"(" + MOVIE_NAME +", "+"Y_Val_1, Z_Val_1)"
                        + " VALUES ('xmen', 1.0, 2.0);"
                );


                dbhandler.setTransactionSuccessful(); //commit your changes.setTransactionSuccessful();
                Log.v("Trace_Write","dsd");


            }
            catch (SQLiteException e) {
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            }
            finally {
                dbhandler.endTransaction();
                dbhandler.close();
            }
        }catch (SQLException e){

            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    public Cursor getAllRows() {
        Cursor cursor;
        SQLiteDatabase handler = null;

            handler = context.openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
            String records = "SELECT * FROM "+TABLE_NAME;
            //String count = "SELECT count(*) AS \"Count\"FROM "+TABLE_NAME;
            cursor = handler.rawQuery(records, null);
            int r = 0;
            int size=cursor.getCount();
            if(cursor!=null)
                cursor.moveToFirst();
            //cursor=handler.rawQuery(count,null);
            //size=cursor.getCount();
            return cursor;
    }
}
