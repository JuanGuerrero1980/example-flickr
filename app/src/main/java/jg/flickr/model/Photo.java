package jg.flickr.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Juan on 9/28/2016.
 */

public class Photo {

    private String _id;
    private String id;
    private String owner;
    private String secret;
    private String farm;
    private String server;
    private String title;
    private String description;
    private boolean _public;
    private boolean _friend;
    private boolean _family;
    private String posted;
    private String taken;
    private int takengranularity;
    private int takenunknown;
    private String lastupdate;
    private int views;
    private int comments;
    private Owner ownerObject;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getFarm() {
        return farm;
    }

    public void setFarm(String farm) {
        this.farm = farm;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean is_public() {
        return _public;
    }

    public void set_public(boolean _public) {
        this._public = _public;
    }

    public boolean is_friend() {
        return _friend;
    }

    public void set_friend(boolean _friend) {
        this._friend = _friend;
    }

    public boolean is_family() {
        return _family;
    }

    public void set_family(boolean _family) {
        this._family = _family;
    }

    public String getPosted() {
        return posted;
    }

    public void setPosted(String posted) {
        this.posted = posted;
    }

    public String getTaken() {
        return taken;
    }

    public void setTaken(String taken) {
        this.taken = taken;
    }

    public int getTakengranularity() {
        return takengranularity;
    }

    public void setTakengranularity(int takengranularity) {
        this.takengranularity = takengranularity;
    }

    public int getTakenunknown() {
        return takenunknown;
    }

    public void setTakenunknown(int takenunknown) {
        this.takenunknown = takenunknown;
    }

    public String getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(String lastupdate) {
        this.lastupdate = lastupdate;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public Owner getOwnerObject() {
        return ownerObject;
    }

    public void setOwnerObject(Owner ownerObject) {
        this.ownerObject = ownerObject;
    }

    public void parseJson(JSONObject photo) throws JSONException {
        if(photo!=null){
            // "id": "29481544184", "owner": "144494724@N08", "secret": "a6750448d0", "server": "8121", "farm": 9, "title": "Günün anlam ve önemine binaen 😎😅😅😅😘", "ispublic": 1, "isfriend": 0, "isfamily": 0 },
            set_id(photo.getString("id"));
            setOwner(photo.getString("owner"));
            setSecret(photo.getString("secret"));
            setServer(photo.getString("server"));
            setFarm(photo.getString("farm"));
            setTitle(photo.getString("title"));
            set_public((photo.getInt("ispublic")==1?true:false));
            set_friend(photo.getInt("isfriend")==1?true:false);
            set_family(photo.getInt("isfamily")==1?true:false);
        }
    }
}
