package jg.flickr.db.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import jg.flickr.db.Database;
import jg.flickr.db.schema.PhotosTable;

import static android.R.attr.x;

/**
 * Created by Juan on 10/3/2016.
 */

public class PhotosHelper extends Database{

    private static PhotosHelper photosHelper;

    private PhotosHelper(Context context){
        super(context);
    }

    public static synchronized PhotosHelper getHelper(Context ctx) {
        if (photosHelper == null) {
            photosHelper = new PhotosHelper(ctx);
        }
        return photosHelper;
    }

    public long insertPhoto(ContentValues values){
        long rows;
        rows = database.insert(PhotosTable.TABLE_NAME,null,values);
        return rows;
    }

    public long updatePhoto(ContentValues values){
        long rows;
        String _id = values.getAsString(PhotosTable.COL_ID);
        rows = database.update(PhotosTable.TABLE_NAME, values, PhotosTable.COL_ID + "=?",  new String[]{_id});
        return rows;
    }

    public long deletePhoto(String _id){
        long rows;
        rows = database.delete(PhotosTable.TABLE_NAME, PhotosTable.COL_ID + "=?",  new String[]{_id});
        return rows;
    }

    public Cursor getRecentList(){
        Cursor cursor=databaseRO.rawQuery("SELECT * FROM " + PhotosTable.TABLE_NAME, null);
        return cursor;
    }

}
