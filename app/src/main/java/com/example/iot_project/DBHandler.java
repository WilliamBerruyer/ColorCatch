package com.example.iot_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "colorcatchdb";

    // below int is our database version
    private static final int DB_VERSION = 1;

    /****************************************************
     ******************* Color Table ********************
     ***************************************************/
    // below variable is for our color table name.
    private static final String TABLE_COLORS_NAME = "colors";

    // below variable is for the id column.
    private static final String ID_COL = "id";

    // below variable is for the name column
    private static final String NAME_COL = "name";

    // below variable id for the hex column.
    private static final String HEX_COL = "hex";

    // below variable for the rgb column.
    private static final String RGB_COL = "rgb";

    // below variable is for the hsv column.
    private static final String HSV_COL = "hsv";

    // below variable is for the cmyk column.
    private static final String CMYK_COL = "cmyk";

    // below variable is for the time of scan column.
    private static final String TIME_COL = "time";

    // below variable is for the liked column.
    private static final String LIKED_COL = "liked";

    /****************************************************
     ******************* Palette Table ******************
     ***************************************************/
    // below variable is for our palette table name.
    private static final String TABLE_PALETTES_NAME = "palettes";

    // below variable is for the id column.
    private static final String ID_COL_PAL = "id";

    // below variable id for the hex column.
    private static final String HEX_COL_PAL = "hex";

    // below variable for the color one column.
    private static final String COLOR1_COL = "color1";

    // below variable is for the color two column.
    private static final String COLOR2_COL = "color2";

    // below variable is for the color three column.
    private static final String COLOR3_COL = "color3";

    // below variable is for the color four column.
    private static final String COLOR4_COL = "color4";

    // below variable is for the liked column.
    private static final String LIKED_COL_PAL = "liked";

    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * Create the tables in the database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_COLORS_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + HEX_COL + " TEXT,"
                + RGB_COL + " TEXT,"
                + HSV_COL + " TEXT,"
                + CMYK_COL + " TEXT,"
                + LIKED_COL + " BOOLEAN" + " CHECK (" + LIKED_COL + " IN (0, 1))" + ","
                + TIME_COL + " TEXT)";

        String queryPalettes = "CREATE TABLE " + TABLE_PALETTES_NAME + " ("
                + ID_COL_PAL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HEX_COL_PAL + " TEXT,"
                + COLOR1_COL + " TEXT,"
                + COLOR2_COL + " TEXT,"
                + COLOR3_COL + " TEXT,"
                + COLOR4_COL + " TEXT,"
                + LIKED_COL_PAL + " BOOLEAN" + " CHECK (" + LIKED_COL_PAL + " IN (0, 1))" + ")";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
        db.execSQL(queryPalettes);
    }

    /**
     * Add new color to our sqlite database
     */
    public void addNewColor(String colorName, String colorHex, String colorRgb, String colorHsv, String colorCmyk, int like, String time) {

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
        values.put(LIKED_COL, like);
        values.put(TIME_COL, time);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_COLORS_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    /**
     * Add new palette to our sqlite database
     */
    public void addNewPalette(String hex_original, String c1, String c2, String c3, String c4, int like) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(HEX_COL_PAL, hex_original);
        values.put(COLOR1_COL, c1);
        values.put(COLOR2_COL, c2);
        values.put(COLOR3_COL, c3);
        values.put(COLOR4_COL, c4);
        values.put(LIKED_COL_PAL, like);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_PALETTES_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    /**
     * Read all the rows inside the colors table in db
     */
    public ArrayList<ColorModal> readColors() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorColor = db.rawQuery("SELECT * FROM " + TABLE_COLORS_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<ColorModal> colorModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorColor.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                colorModalArrayList.add(new ColorModal(cursorColor.getString(1),
                        cursorColor.getString(2),
                        cursorColor.getInt(6),
                        cursorColor.getString(7)));
            } while (cursorColor.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorColor.close();
        return colorModalArrayList;
    }

    /**
     * Read only the colors liked in colors table in db
     */
    public ArrayList<ColorModal> readColorsLiked() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorColor = db.rawQuery("SELECT * FROM " + TABLE_COLORS_NAME + " WHERE " + LIKED_COL + " = " + 1, null);

        // on below line we are creating a new array list.
        ArrayList<ColorModal> colorModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorColor.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list. (1: colorName, 2: colorHex, 6: Liked, 7: Time)
                colorModalArrayList.add(new ColorModal(cursorColor.getString(1),
                        cursorColor.getString(2),
                        cursorColor.getInt(6),
                        cursorColor.getString(7)));
            } while (cursorColor.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorColor.close();
        return colorModalArrayList;
    }

    /**
     * Read all the liked palettes from palettes table in db
     */
    public ArrayList<PaletteModal> readLikedPalettes() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorPalette = db.rawQuery("SELECT * FROM " + TABLE_PALETTES_NAME + " WHERE " + LIKED_COL_PAL + " = " + 1, null);

        // on below line we are creating a new array list.
        ArrayList<PaletteModal> paletteModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorPalette.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                // (1: color0(hex), 2: color1, 3: color2, 4: color3, 5: color4, 6: liked)
                paletteModalArrayList.add(new PaletteModal(cursorPalette.getString(1),
                        cursorPalette.getString(2),
                        cursorPalette.getString(3),
                        cursorPalette.getString(4),
                        cursorPalette.getString(5),
                        cursorPalette.getInt(6)));
            } while (cursorPalette.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorPalette.close();
        return paletteModalArrayList;
    }

    /**
     * Read the palette corresponding to color given in params in palettes table in db
     */
    public ArrayList<PaletteModal> readSpecificPalettes(String color) {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        //SELECT distinct palettes.ID, palettes.hex, palettes.COlOR1, palettes.COlOR2, palettes.COlOR3, palettes.COlOR4, palettes.COlOR5  FROM palettes left outer join colors on colors.hex = palettes.hex;
        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorPalette = db.rawQuery("SELECT distinct palettes.hex" + ", " + COLOR1_COL + ", " + COLOR2_COL + ", " + COLOR3_COL + ", " + COLOR4_COL + ", " + " palettes.liked" + " FROM " + TABLE_PALETTES_NAME + " LEFT OUTER JOIN " + TABLE_COLORS_NAME + " ON colors.hex " + "=" + " palettes.hex" + " WHERE " + " colors.hex " + "= " + DatabaseUtils.sqlEscapeString(color), null);

        // on below line we are creating a new array list.
        ArrayList<PaletteModal> paletteModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorPalette.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                paletteModalArrayList.add(new PaletteModal(cursorPalette.getString(0),
                        cursorPalette.getString(1),
                        cursorPalette.getString(2),
                        cursorPalette.getString(3),
                        cursorPalette.getString(4),
                        cursorPalette.getInt(5)));
            } while (cursorPalette.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorPalette.close();
        return paletteModalArrayList;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COLORS_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PALETTES_NAME);
        onCreate(db);
    }

    /**
     * Read the color corresponding to the id given in params in colors table in db
     */
    public ColorItem readSpecificColor(int position) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_COLORS_NAME + " WHERE " + ID_COL + "=" + position, null);
        ColorItem color = new ColorItem();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            //setting related user info in User Object

            int name_col_index = cursor.getColumnIndex(NAME_COL);
            int hex_col_index = cursor.getColumnIndex(HEX_COL);
            int rgb_col_index = cursor.getColumnIndex(RGB_COL);
            int hsv_col_index = cursor.getColumnIndex(HSV_COL);
            int cmyk_col_index = cursor.getColumnIndex(CMYK_COL);
            int liked_col_index = cursor.getColumnIndex(LIKED_COL);

            color.setName(cursor.getString(name_col_index));
            color.setHex(cursor.getString(hex_col_index));
            color.setRgb(cursor.getString(rgb_col_index));
            color.setHsv(cursor.getString(hsv_col_index));
            color.setCmyk(cursor.getString(cmyk_col_index));
            color.setLiked(Integer.parseInt(cursor.getString(liked_col_index)));
        }
        //close cursor & database
        cursor.close();
        db.close();

        return color;
    }

    /**
     * Modify liked column accordingly (0 or 1) to the color with id given in params in colors table in db
     */
    public void addColorLikeToDB(int position) {
        int likedValue = getLikedCol(position);
        SQLiteDatabase db = this.getWritableDatabase();

        if (likedValue == 0) {
            db.execSQL("UPDATE " + TABLE_COLORS_NAME + " SET " + LIKED_COL + " = " + 1 + " WHERE " + ID_COL + " = " + position);
        } else if (likedValue == 1) {
            db.execSQL("UPDATE " + TABLE_COLORS_NAME + " SET " + LIKED_COL + " = " + 0 + " WHERE " + ID_COL + " = " + position);
        }

        //close cursor & database
        db.close();
    }

    /**
     * Modify liked column accordingly (0 or 1) to the palette with id given in params in palettes table in db
     */
    public void addPaletteLikeToDB(int position) {
        int likedValue = getLikedPal(position);
        SQLiteDatabase db = this.getWritableDatabase();

        if (likedValue == 0) {
            db.execSQL("UPDATE " + TABLE_PALETTES_NAME + " SET " + LIKED_COL_PAL + " = " + 1 + " WHERE " + ID_COL_PAL + " = " + position);
        } else if (likedValue == 1) {
            db.execSQL("UPDATE " + TABLE_PALETTES_NAME + " SET " + LIKED_COL_PAL + " = " + 0 + " WHERE " + ID_COL_PAL + " = " + position);
        }

        //close cursor & database
        db.close();
    }


    /**
     * Get if a color is liked or not in the colors table in database 0 if no, 1 if yes
     */
    public int getLikedCol(int position) {
        int output = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_COLORS_NAME + " WHERE " + ID_COL + "=" + position, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int liked_col_index = cursor.getColumnIndex(LIKED_COL);
            output = cursor.getInt(liked_col_index);
        }
        //close cursor & database
        cursor.close();
        db.close();
        return output;
    }

    /**
     * Get if a palette is liked or not in the palettes table in database 0 if no, 1 if yes
     */
    public int getLikedPal(int position) {
        int output = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PALETTES_NAME + " WHERE " + ID_COL_PAL + "=" + position, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int liked_col_index = cursor.getColumnIndex(LIKED_COL_PAL);
            output = cursor.getInt(liked_col_index);
        }
        //close cursor & database
        cursor.close();
        db.close();
        return output;
    }
}
