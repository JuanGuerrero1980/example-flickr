package jg.flickr.ui;

import android.content.ContentProvider;
import android.database.Cursor;
import android.net.MailTo;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.NavUtils;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import jg.flickr.R;
import jg.flickr.db.helper.PhotosHelper;
import jg.flickr.db.provider.PhotosContentProvider;
import jg.flickr.http.HttpFlickrRequest;
import jg.flickr.http.HttpResponseListener;
import jg.flickr.ui.adapters.PhotosListAdapter;
import jg.flickr.ui.utils.EndlessRecyclerViewScrollListener;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private static final String TAG = MainActivity.class.getSimpleName();
    public static int LOADER_ID = 0;
    public boolean loaderFinished;
    private RecyclerView recyclerView;
    private PhotosListAdapter adapter;
    private RecyclerView.LayoutManager  layoutManager;

    private HttpFlickrRequest request;
    private EndlessRecyclerViewScrollListener scrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        PhotosHelper.getHelper(MainActivity.this).restart(false);
        recyclerView = (RecyclerView) findViewById(R.id.recicler_view);
        adapter = new PhotosListAdapter(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        Log.i(TAG, "getFromServer.");
        final HttpResponseListener listener = new HttpResponseListener() {
            @Override
            public void success(Object object) {
                MainActivity.this.getContentResolver().notifyChange(Uri.withAppendedPath(
                        PhotosContentProvider.CONTENT_URI_PHOTOS, PhotosContentProvider.PATH_SEGMENT_ALL_PHOTOS), null, false);
            }

            @Override
            public void error(Object object) {

            }
        };
        request = new HttpFlickrRequest(listener, MainActivity.this);
        scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {

                request.getRecent(page);
            }
        };
        recyclerView.setOnScrollListener(scrollListener);
        /*
        recyclerView.setOnFlingListener(new RecyclerViewSwipeListener(true) {
            @Override
            public void onSwipeDown() {
                Toast.makeText(MainActivity.this, "swipe down", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSwipeUp() {
                Toast.makeText(MainActivity.this, "swipe up", Toast.LENGTH_SHORT).show();
            }
        });
        */
        getSupportLoaderManager().restartLoader(LOADER_ID, null, MainActivity.this);




    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        // Consultar todos los registros
        return new CursorLoader(this,  Uri.withAppendedPath(
                PhotosContentProvider.CONTENT_URI_PHOTOS, PhotosContentProvider.PATH_SEGMENT_ALL_PHOTOS),
                null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        //loaderFinished = true;
        adapter.swapCursor(data);
        //adapter.notifyDataSetChanged();
        //emptyView.setText("");
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // perform query here
                request.search(query, 1);
                // workaround to avoid issues with some emulators and keyboard devices firing twice if a keyboard enter is used
                // see https://code.google.com/p/android/issues/detail?id=24599
                searchView.clearFocus();

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // This is the up button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                // overridePendingTransition(R.animator.anim_left, R.animator.anim_right);
                return true;
            case R.id.action_change_view:
                if(layoutManager instanceof StaggeredGridLayoutManager) {
                    layoutManager = new LinearLayoutManager(this);
                    scrollListener.EndlessRecyclerViewScrollListener((LinearLayoutManager) layoutManager);
                }else {
                    layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                    scrollListener.EndlessRecyclerViewScrollListener((StaggeredGridLayoutManager) layoutManager);
                }

                recyclerView.setLayoutManager(layoutManager);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
