package jg.flickr.ui;

import android.net.MailTo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import jg.flickr.R;
import jg.flickr.http.HttpFlickrRequest;
import jg.flickr.http.HttpResponseListener;
import jg.flickr.http.VolleySingleton;
import jg.flickr.utils.Constants;

import static jg.flickr.utils.Constants.MY_SOCKET_TIMEOUT_MS;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "getFromServer.");
        final HttpResponseListener listener = new HttpResponseListener() {
            @Override
            public void success(Object object) {

            }

            @Override
            public void error(Object object) {

            }
        };

        HttpFlickrRequest request = new HttpFlickrRequest(listener, MainActivity.this);
        request.getRecent(1);
    }


}
