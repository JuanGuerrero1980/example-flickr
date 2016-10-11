package jg.flickr.http;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;


import jg.flickr.db.helper.PhotosHelper;
import jg.flickr.model.Photo;
import jg.flickr.model.PhotoList;
import jg.flickr.ui.MainActivity;
import jg.flickr.utils.Constants;

import static android.R.attr.id;

/**
 * Created by Juan on 9/30/2016.
 */

public class HttpFlickrRequest {

    private static final String TAG = HttpFlickrRequest.class.getSimpleName();

    private HttpResponseListener httpResponseListener;
    private Context context;

    public HttpFlickrRequest(HttpResponseListener listener, Context context){
        this.httpResponseListener = listener;
        this.context = context;
    }

    public void getRecent(final int page){
        JsonObjectRequest myRequest = new JsonObjectRequest(Request.Method.GET, Constants.GET_URL + Constants.F_PHOTOS_GETRECENT
                + "&per_page=10&page=" + page,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            processResponseRecent(response);
                            if(httpResponseListener!=null){
                                httpResponseListener.success(null);
                            }
                        } catch (JSONException e) {
                            if(httpResponseListener!=null)
                                httpResponseListener.error(null);
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(httpResponseListener!=null){
                            httpResponseListener.error(null);
                        }
                        Log.d(TAG, "ErrorResponse: "+error.getMessage());
                    }
                }
        );

        myRequest.setRetryPolicy(new DefaultRetryPolicy(Constants.MY_SOCKET_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(context).addToRequestQueue(myRequest);
    }

    public void getInfo(String id){

    }

    public void search(String text, int page){
        JsonObjectRequest myRequest = new JsonObjectRequest(Request.Method.GET, Constants.GET_URL + Constants.F_PHOTOS_SEARCH
                + "&per_page=10&page=" + page + "&text=" + text,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            processResponseRecent(response);
                            if(httpResponseListener!=null){
                                httpResponseListener.success(null);
                            }
                        } catch (JSONException e) {
                            if(httpResponseListener!=null)
                                httpResponseListener.error(null);
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(httpResponseListener!=null){
                            httpResponseListener.error(null);
                        }
                        Log.d(TAG, "ErrorResponse: "+error.getMessage());
                    }
                }
        );

        myRequest.setRetryPolicy(new DefaultRetryPolicy(Constants.MY_SOCKET_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(context).addToRequestQueue(myRequest);
    }

    public void getSize(String id){

    }

    public void getImage(Photo photo){
        String [] args = {};
        String url = String.format((new Constants()).F_PHOTO_URL , args );

    }
    /// process request

    private void processResponseRecent(JSONObject response) throws JSONException {

        PhotoList list = new PhotoList();
        list.parseJson(response);
        if(list.getPage()==1){
            PhotosHelper.getHelper(context).restart(true);
        }
        PhotosHelper.getHelper(context).insertList(list.getPhotos());
        Log.i(TAG,response.toString());
    }
}
