package jg.flickr.db.provider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import jg.flickr.db.helper.PhotosHelper;

import static android.os.Build.VERSION_CODES.M;

public class PhotosContentProvider extends ContentProvider {

    public static final String AUTHORITY = "jg.flickr.provider.photos";

    private static final int PHOTOS = 10;
    private static final String PATH_PHOTOS = "photos";

    public static final String PATH_SEGMENT_ALL_PHOTOS = "photos_all";

    public static final Uri CONTENT_URI_PHOTOS = Uri.parse("content://" + AUTHORITY + "/" + PATH_PHOTOS);

    public static final String PHOTOS_CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd.jg.flickr.photos";

    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, PATH_PHOTOS + "/" +  PATH_SEGMENT_ALL_PHOTOS, PHOTOS);

    }

    public PhotosContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case PHOTOS:
                return PHOTOS_CONTENT_TYPE;
            default:
                return null;
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor cursor = null;
        switch (uriMatcher.match(uri)) {
            case PHOTOS:
                cursor = PhotosHelper.getHelper(getContext()).getRecentList();
                cursor.setNotificationUri(getContext().getContentResolver(), uri);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
