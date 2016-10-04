package jg.flickr.model;

/**
 * Created by Juan on 10/3/2016.
 */

public class Owner {

    /*
    {"nsid": "144809460@N07", "username": "rcmd_cfdfw_10_1", "realname": "cfdfw 10_1", "location": "", "iconserver": 0, "iconfarm": 0, "path_alias": "" }
     */

    private String nsid;
    private String username;
    private String realname;
    private String location;
    private int iconserver;
    private int iconfarm;
    private String path_alias;

    public String getNsid() {
        return nsid;
    }

    public void setNsid(String nsid) {
        this.nsid = nsid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getIconserver() {
        return iconserver;
    }

    public void setIconserver(int iconserver) {
        this.iconserver = iconserver;
    }

    public int getIconfarm() {
        return iconfarm;
    }

    public void setIconfarm(int iconfarm) {
        this.iconfarm = iconfarm;
    }

    public String getPath_alias() {
        return path_alias;
    }

    public void setPath_alias(String path_alias) {
        this.path_alias = path_alias;
    }
}
