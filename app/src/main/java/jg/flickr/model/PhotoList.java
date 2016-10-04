package jg.flickr.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Juan on 9/29/2016.
 */

public class PhotoList {

    private int page;
    private int pages;
    private int perPage;
    private int total;
    private Collection<Photo> photos = new ArrayList<Photo>();

    public PhotoList(){

    }

    public PhotoList(JSONObject object) throws JSONException {
        parseJson(object);
    }
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Collection<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Collection<Photo> photos) {
        this.photos = photos;
    }

    public void parseJson(JSONObject response) throws JSONException {
        if(response!=null){
            JSONObject p = response.getJSONObject("photos");
            //"page": 1, "pages": 10, "perpage": 100, "total": "1000",               "photo": [
            this.setPage(p.getInt("page"));
            this.setPages(p.getInt("pages"));
            this.setPerPage(p.getInt("perpage"));
            this.setTotal(p.getInt("total"));
            JSONArray ps = p.getJSONArray("photo");
            for (int i = 0 ; i<ps.length(); i++){
                JSONObject photoJson = ps.getJSONObject(i);
                Photo photoModel = new Photo();
                photoModel.parseJson(photoJson);
                this.getPhotos().add(photoModel);
            }
        }
    }
}
