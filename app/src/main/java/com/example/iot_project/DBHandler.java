package com.example.iot_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "colorcatchdb";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "colors";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our course name column
    private static final String NAME_COL = "name";

    // below variable id for our course duration column.
    private static final String HEX_COL = "hex";

    // below variable for our course description column.
    private static final String RGB_COL = "rgb";

    // below variable is for our color hsv column.
    private static final String HSV_COL = "hsv";

    // below variable is for our color cmyk column.
    private static final String CMYK_COL = "cmyk";

    // below variable is for our color cmyk column.
    private static final String TIME_COL = "time";


    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + HEX_COL + " TEXT,"
                + RGB_COL + " TEXT,"
                + HSV_COL + " TEXT,"
                + CMYK_COL + " TEXT,"
                + TIME_COL + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new course to our sqlite database.
    public void addNewColor(String colorName, String colorHex, String colorRgb, String colorHsv, String colorCmyk, String time) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, colorName);
        values.put(HEX_COL, colorHex);
        values.put(RGB_COL, colorRgb);
        values.put(HSV_COL, colorHsv);
        values.put(CMYK_COL, colorCmyk);
        values.put(TIME_COL, time);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    // we have created a new method for reading all the courses.
    public ArrayList<ColorModal> readCourses() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorColor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<ColorModal> colorModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorColor.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                colorModalArrayList.add(new ColorModal(cursorColor.getString(1),
                        cursorColor.getString(2),
                        cursorColor.getString(3),
                        cursorColor.getString(4),
                        cursorColor.getString(5),
                        cursorColor.getString(6)));
            } while (cursorColor.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorColor.close();
        return colorModalArrayList;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public ColorItem getSingleDataInfo(int position) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COL + "=" + position, null);
        ColorItem color = new ColorItem();
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();

            //setting related user info in User Object

            int name_col_index = cursor.getColumnIndex(NAME_COL);
            int hex_col_index = cursor.getColumnIndex(HEX_COL);
            int rgb_col_index = cursor.getColumnIndex(RGB_COL);
            int hsv_col_index = cursor.getColumnIndex(HSV_COL);
            int cmyk_col_index = cursor.getColumnIndex(CMYK_COL);

            color.setName(cursor.getString(name_col_index));
            color.setHex(cursor.getString(hex_col_index));
            color.setRgb(cursor.getString(rgb_col_index));
            color.setHsv(cursor.getString(hsv_col_index));
            color.setCmyk(cursor.getString(cmyk_col_index));
        }
        //close cursor & database
        cursor.close();
        db.close();

        return color;

    }
}
