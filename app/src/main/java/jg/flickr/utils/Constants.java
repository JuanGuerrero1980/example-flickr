package jg.flickr.utils;

/**
 * Created by Juan on 9/28/2016.
 */

public class Constants {

    public static final String DATA_BASE_NAME = "flickr_db";
    public static String GET_URL = "https://api.flickr.com/services/rest/?method=";
    // para traer las fotos recientes (cuando no se ingresa texto a buscar)
    public static String API_KEY = "c6748ae4bfa1f9cdfaaced0a262ce225";
    public static String F_PHOTOS_GETRECENT = "flickr.photos.getRecent&nojsoncallback=1&format=json&api_key=" + Constants.API_KEY;
    public static String F_PHOTOS_SEARCH = "flickr.photos.search&nojsoncallback=1&format=json&api_key=" + Constants.API_KEY;

    /**
     * https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}_[mstzb].jpg
     */
    public String F_PHOTO_URL = "http://farm{0}.staticflickr.com/{1}/{2}_{3}_{4}.jpg";

    public static int MY_SOCKET_TIMEOUT_MS = 50000;
}
