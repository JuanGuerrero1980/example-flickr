package jg.flickr.ui.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.bumptech.glide.Glide;

import jg.flickr.R;
import jg.flickr.db.schema.PhotosTable;
import jg.flickr.http.VolleySingleton;
import jg.flickr.utils.Constants;

import static android.R.attr.value;

/**
 * Created by Juan on 10/4/2016.
 */

public class PhotosListAdapter extends RecyclerView.Adapter<PhotosListAdapter.PhotoViewHolder> {
    private Cursor cursor;
    private Context context;
    private int lastPosition;


    public static class PhotoViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;
        public TextView text;

        public PhotoViewHolder(View v) {
            super(v);
            image = (ImageView) v.findViewById(R.id.photo_image_list);

        }
    }

    public PhotosListAdapter(Context context) {
        this.context= context;
    }

    @Override
    public int getItemCount() {
        if (cursor!=null)
            return cursor.getCount();
        return 0;
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_photos_list, viewGroup, false);
        return new PhotoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder viewHolder, int i) {
        cursor.moveToPosition(i);


        String farm = cursor.getString(cursor.getColumnIndex(PhotosTable.COL_FARM));
        String server= cursor.getString(cursor.getColumnIndex(PhotosTable.COL_SERVER));
        String secret= cursor.getString(cursor.getColumnIndex(PhotosTable.COL_SECRET));
        String id= cursor.getString(cursor.getColumnIndex(PhotosTable.COL_ID));


        String [] argus = {farm, server, id, secret, "m"};

        String photoUrl = "http://farm"+ farm +".staticflickr.com/"+ server +"/"+ id +"_"+ secret +"_m.jpg";
        String url = String.format( photoUrl,  farm, server, id, secret, "m");
        //viewHolder.image.setImageUrl(photoUrl, VolleySingleton.getInstance(context).getImageLoader());
        Glide
                .with(context)
                .load(url)
                .centerCrop()
                .placeholder(android.R.drawable.progress_indeterminate_horizontal)
                .crossFade()
                .into(viewHolder.image);
        setAnimation(viewHolder.image, i);


    }

    public void swapCursor(Cursor newCursor) {
        cursor = newCursor;
        notifyDataSetChanged();
    }

    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

}
