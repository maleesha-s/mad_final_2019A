package com.example.it19004778mad2019a.DATABASE;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "users.db";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRIES2);
        db.execSQL(SQL_CREATE_ENTRIES3);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_DELETE_ENTRIES2);
        db.execSQL(SQL_DELETE_ENTRIES3);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DatabaseMaster.Users.TABLE_Name + " (" +
                    DatabaseMaster.Users._ID + " INTEGER PRIMARY KEY," +
                    DatabaseMaster.Users.COLUMN_Username + " TEXT," +
                    DatabaseMaster.Users.COLUMN_Password + " TEXT," +
                    DatabaseMaster.Users.COLUMN_UserType + " TEXT)";

    private static final String SQL_CREATE_ENTRIES2 =
            "CREATE TABLE " + DatabaseMaster.Movies.TABLE_Name + " (" +
                    DatabaseMaster.Movies._ID + " INTEGER PRIMARY KEY," +
                    DatabaseMaster.Movies.COLUMN_MovieName + " TEXT," +
                    DatabaseMaster.Movies.COLUMN_MovieYear + " INTEGER)";

    private static final String SQL_CREATE_ENTRIES3 =
            "CREATE TABLE " + DatabaseMaster.Comments.TABLE_Name + " (" +
                    DatabaseMaster.Comments._ID + " INTEGER PRIMARY KEY," +
                    DatabaseMaster.Comments.COLUMN_MovieName + " TEXT," +
                    DatabaseMaster.Comments.COLUMN_MovieRating + " INTEGER," +
                    DatabaseMaster.Comments.COLUMN_MovieComments + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DatabaseMaster.Users.TABLE_Name;

    private static final String SQL_DELETE_ENTRIES2 =
            "DROP TABLE IF EXISTS " + DatabaseMaster.Movies.TABLE_Name;

    private static final String SQL_DELETE_ENTRIES3 =
            "DROP TABLE IF EXISTS " + DatabaseMaster.Comments.TABLE_Name;


    public long registerUser(String UserName, String Password, String UserType){
        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(DatabaseMaster.Users.COLUMN_Username, UserName);
        values.put(DatabaseMaster.Users.COLUMN_Password, Password);
        values.put(DatabaseMaster.Users.COLUMN_UserType, UserType);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(DatabaseMaster.Users.TABLE_Name, null, values);

        return newRowId;
    }
    public boolean LoginUser(String username){
        SQLiteDatabase db = this.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                DatabaseMaster.Users.COLUMN_Username,
                DatabaseMaster.Users.COLUMN_Password,
                DatabaseMaster.Users.COLUMN_UserType
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = DatabaseMaster.Users.COLUMN_Username + " LIKE ?";
        String[] selectionArgs = { username };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                DatabaseMaster.Users.COLUMN_Username + " DESC";

        Cursor cursor = db.query(
                DatabaseMaster.Users.TABLE_Name,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        if(cursor.getCount() != 0){
            return true;
        }else {
            return false;
        }
    }
    public boolean addMovie(String movieName, int movieYear){
        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(DatabaseMaster.Movies.COLUMN_MovieName, movieName);
        values.put(DatabaseMaster.Movies.COLUMN_MovieYear, movieYear);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(DatabaseMaster.Movies.TABLE_Name, null, values);

        if(newRowId > 0){
            return true;
        }else{
            return false;
        }
    }
    public long insertComments(String MovieName, int MovieRating, String MovieComments){
        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(DatabaseMaster.Comments.COLUMN_MovieName, MovieName);
        values.put(DatabaseMaster.Comments.COLUMN_MovieRating, MovieRating);
        values.put(DatabaseMaster.Comments.COLUMN_MovieComments, MovieComments);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(DatabaseMaster.Comments.TABLE_Name, null, values);

        return newRowId;
    }
    public Cursor viewMovies(){
        String query = "SELECT * FROM " + DatabaseMaster.Movies.TABLE_Name;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if( db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }
    public Cursor viewComments(){
        String query = "SELECT * FROM " + DatabaseMaster.Comments.TABLE_Name;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if( db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }
    public Cursor viewComments(String mName){
        SQLiteDatabase db = this.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                DatabaseMaster.Comments.COLUMN_MovieName,
                DatabaseMaster.Comments.COLUMN_MovieRating,
                DatabaseMaster.Comments.COLUMN_MovieComments
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = DatabaseMaster.Comments.COLUMN_MovieName + " LIKE ?";
        String[] selectionArgs = { mName };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                DatabaseMaster.Comments.COLUMN_MovieName + " ASC";

        Cursor cursor = db.query(
                DatabaseMaster.Comments.TABLE_Name,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        return cursor;
    }

}

