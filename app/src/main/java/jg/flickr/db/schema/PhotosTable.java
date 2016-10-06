package jg.flickr.db.schema;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Juan on 5/26/2016.
 */
public class PhotosTable {

    public static final String TABLE_NAME = "photos";

    public static final String COL__ID = "_id";
    public static final String COL_ID = "id";
    public static final String COL_OWNER = "owner";
    public static final String COL_SECRET = "secret";
    public static final String COL_SERVER = "server";
    public static final String COL_FARM = "farm";
    public static final String COL_TITLE = "title";
    public static final String COL_IS_PUBLIC = "ispublic";
    public static final String COL_IS_FRIEND = "isfriend";
    public static final String COL_IS_FAMILY = "isfamily";


    private static final String TABLE_CREATE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_NAME + " (  " + COL__ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_ID + " TEXT, "
            + COL_OWNER + " TEXT, "
            + COL_SECRET + " TEXT, "
            + COL_SERVER + " TEXT, "
            + COL_FARM + " TEXT, "
            + COL_TITLE + " TEXT,"
            + COL_IS_PUBLIC + " INTEGER DEFAULT 0,"
            + COL_IS_FRIEND + " INTEGER DEFAULT 0,"
            + COL_IS_FAMILY + " INTEGER DEFAULT 0)";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(TABLE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {

    }
}
