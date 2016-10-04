package jg.flickr.db;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

public class Database {
    protected static SQLiteDatabase database;
    protected static SQLiteDatabase databaseRO;
    private Object mLockObject = new Object();
    protected Context ctx;

    protected Database(Context ctx) {
        synchronized (mLockObject){
            this.ctx = ctx;
            if (database == null) {
                DatabaseOpenHelper helper = new DatabaseOpenHelper(ctx);
                database = helper.getWritableDatabase();
                databaseRO = helper.getReadableDatabase();
            }
        }
    }

    public static SQLiteDatabase getWritableDatabase(){
        return database;
    }

    @SuppressLint("NewApi")
    public static synchronized void beginTrans(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            database.beginTransactionNonExclusive();
        else
            database.beginTransaction();
    }

    public static synchronized void commitTrans(){
        database.setTransactionSuccessful();
    }

    public static synchronized void endTrans(){
        database.endTransaction();
    }
}