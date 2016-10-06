package jg.flickr.db.helper;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;

import jg.flickr.db.Database;
import jg.flickr.db.provider.PhotosContentProvider;
import jg.flickr.db.schema.PhotosTable;
import jg.flickr.model.Photo;
import jg.flickr.ui.MainActivity;

import static android.R.attr.x;
import static android.icu.text.MessagePattern.ArgType.SELECT;
import static java.security.AccessController.getContext;

/**
 * Created by Juan on 10/3/2016.
 */

public class PhotosHelper extends Database{

    private static PhotosHelper photosHelper;
    private Context _context;

    private PhotosHelper(Context context){
        super(context);this._context=context;
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

    public void insertList(ArrayList<Photo> photos){
        for (Photo p : photos){
            insertPhoto(p.getContentValues());
        }
    }

    public long restart(boolean all){
        long rows;
        if(all){
            rows = database.delete(PhotosTable.TABLE_NAME, null,  null);
        }else{
            rows = database.delete(PhotosTable.TABLE_NAME, PhotosTable.COL__ID + " NOT IN (" +
                    "                SELECT "+PhotosTable.COL__ID+" FROM "+PhotosTable.TABLE_NAME+" ORDER BY "+PhotosTable.COL__ID+" DESC LIMIT 8" +
                    "        )",  null);
        }
        _context.getContentResolver().notifyChange(Uri.withAppendedPath(
                PhotosContentProvider.CONTENT_URI_PHOTOS, PhotosContentProvider.PATH_SEGMENT_ALL_PHOTOS), null, false);
        return rows;

    }
}
