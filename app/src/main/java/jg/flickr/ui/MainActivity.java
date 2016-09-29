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
            public void success() {

            }

            @Override
            public void error() {

            }
        };
        JsonObjectRequest myRequest = new JsonObjectRequest(Request.Method.GET, Constants.GET_URL + Constants.F_PHOTOS_GETRECENT
                + "&per_page=1&nojsoncallback=1&format=json&api_key=" + Constants.API_KEY,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        processResponse(response);
                        if(listener!=null){
                            listener.success();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(listener!=null){
                            listener.error();
                        }
                        Log.d(TAG, "ErrorResponse: "+error.getMessage());
                    }
                }
        );
        // necesite poner un timeout alto porque el servidor no me respondia a tiempo y la llamada se iba por timeouterror
        myRequest.setRetryPolicy(new DefaultRetryPolicy(Constants.MY_SOCKET_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(MainActivity.this).addToRequestQueue(myRequest);
    }

    private void processResponse(JSONObject response) {
        Log.i(TAG,response.toString());
    }
}
