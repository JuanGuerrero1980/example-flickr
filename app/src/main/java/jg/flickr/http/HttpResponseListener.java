package jg.flickr.http;

import java.util.Objects;

/**
 * Created by Juan on 9/28/2016.
 */

public interface HttpResponseListener {
    void success(Object object);
    void error(Object object);
}
