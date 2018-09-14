package com.mapprr.test.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mapprr.test.R;
import com.mapprr.test.activity.DetailsActivity;
import com.mapprr.test.model.ListItem;
import com.mapprr.test.utils.Utils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Lenovo on 2/18/2017.
 */

public class ListAdapter extends RecyclerView.Adapter
{
    private List<ListItem> adapterList;
    String Tag = "GroupAdapter";
    private int itemLayout;
    public static int adapterMode;
    public Context mcontext;

    public ListAdapter(Context context, List<ListItem> students, RecyclerView recyclerView, int layout, int mode)
    {
        adapterList = 	students;
        itemLayout 	= 	layout;
        adapterMode	=	mode;
        mcontext=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        RecyclerView.ViewHolder vh;

            View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent,false);
            vh = new ListViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position)
    {
        if (holder instanceof ListViewHolder)
        {
            ListItem singleItemData = (ListItem) adapterList.get(position);

            final HashMap<String, Object> detailMap = singleItemData.getMapMain();
            Utils.e("paymentslistadapter 107", "" + detailMap);

            try
            {
                ((ListViewHolder) holder).root.setTag(detailMap);

                if (detailMap.containsKey("avatar_url") && detailMap.get("avatar_url")!= null)
                {
                    Utils.setImagePiccaso(mcontext,detailMap.get("avatar_url").toString(),((ListViewHolder) holder).photo);
                }

                if(detailMap.containsKey("name") && !detailMap.get("name").toString().equalsIgnoreCase(""))
                {
                    ((ListViewHolder) holder).name.setText(detailMap.get("name").toString());
                }

                if(detailMap.containsKey("full_name") && !detailMap.get("full_name").toString().equalsIgnoreCase(""))
                {
                    ((ListViewHolder) holder).fullname.setText(detailMap.get("full_name").toString());
                }

                if(detailMap.containsKey("watchers_count") && !detailMap.get("watchers_count").toString().equalsIgnoreCase(""))
                {
                    ((ListViewHolder) holder).wcount.setText(detailMap.get("watchers_count").toString());
                }

                if(detailMap.containsKey("forks_count") && !detailMap.get("forks_count").toString().equalsIgnoreCase(""))
                {
                    ((ListViewHolder) holder).ccount.setText(detailMap.get("forks_count").toString());
                }

                ((ListViewHolder) holder).root.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {

                        Utils.global.mapData = (HashMap<String,Object>) v.getTag();

                        if (Utils.global.mapData.size()>0)
                        {
                            Intent i=new Intent(mcontext, DetailsActivity.class);

                            ((ListViewHolder) holder).photo.invalidate();
                            BitmapDrawable drawable = (BitmapDrawable) ((ListViewHolder) holder).photo.getDrawable();
                            Bitmap bitmap = drawable.getBitmap();

                            i.putExtra("photo",bitmap);

                            if (Utils.global.mapData.containsKey("name")&&!Utils.global.mapData.get("name").toString().equalsIgnoreCase(""))
                            i.putExtra("name",Utils.global.mapData.get("name").toString());

                            if (Utils.global.mapData.containsKey("html_url")&&!Utils.global.mapData.get("html_url").toString().equalsIgnoreCase(""))
                                i.putExtra("html_url",Utils.global.mapData.get("html_url").toString());

                            if (Utils.global.mapData.containsKey("description")&&!Utils.global.mapData.get("description").toString().equalsIgnoreCase(""))
                                i.putExtra("description",Utils.global.mapData.get("description").toString());

                            mcontext.startActivity(i);
                        }
                    }
                });
           }
            catch (Exception e)
            {
                Utils.e(Tag+"180","Exception======================Exception======================Exception");
                e.printStackTrace();
            }
            ((ListViewHolder) holder).item= singleItemData;
        }
        else
        {

        }
    }

    @Override
    public int getItemCount()
    {
        return adapterList.size();
    }

    public static class ListViewHolder extends RecyclerView.ViewHolder
    {
        //Notification
        public ListItem item;
        public View gView;
        //***

        public TextView name,fullname,wcount,ccount;
        public ImageView photo;
        public RelativeLayout root;
        public ListViewHolder(View v)
        {
            super(v);
            try
            {
                name=(TextView) v.findViewById(R.id.name);
                fullname=(TextView) v.findViewById(R.id.fullname);
                wcount=(TextView) v.findViewById(R.id.wcount);
                ccount=(TextView) v.findViewById(R.id.ccount);
                photo=(ImageView) v.findViewById(R.id.photo);
                root=(RelativeLayout) v.findViewById(R.id.rowitem_root);
            }
            catch (Exception e)
            {
                Utils.e("ProfileEventRecycle 212", "Exception======================Exception======================Exception");
                e.printStackTrace();
            }
        }
    }
}