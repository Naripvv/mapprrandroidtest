package com.mapprr.test.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mapprr.test.R;
import com.mapprr.test.adapter.ListAdapter;
import com.mapprr.test.model.ListItem;
import com.mapprr.test.utils.ConstVariable;
import com.mapprr.test.utils.JsonPost;
import com.mapprr.test.utils.Settings;
import com.mapprr.test.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by narayana on 10/25/2017.
 */

public class HomeActivity extends AppCompatActivity
{
    public static   RecyclerView rv_loc;
    ImageView back;
    TextView title;
    public static HomeActivity Instance;
    public static Context mcontext;
    public static ListAdapter requestsAdapter;
    public static List<ListItem> requestsList;

    @Override
    protected void onStart()
    {
        super.onStart();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_home);
        super.onCreate(savedInstanceState);

        rv_loc=(RecyclerView) findViewById(R.id.rv_loc);
        back=(ImageView) findViewById(R.id.back);
        title=(TextView) findViewById(R.id.title);

        back.setVisibility(View.GONE);

        title.setText("Repositories");


        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });
        Instance=this;
        mcontext=this;

        rv_loc.setHasFixedSize(true);
        rv_loc.setLayoutManager(new LinearLayoutManager(mcontext,LinearLayoutManager.VERTICAL,false));

        // server request for repositories list.
        getRespositoriesListRequest();
    }


    public  void getRespositoriesListRequest()
    {
        new Utils(HomeActivity.this);
        Utils.global.mapMain();
        Utils.global.mapMain.put(ConstVariable.URL, Settings.URL__LIST);

        if (Utils.isNetworkAvailable(HomeActivity.this))
        {
            JsonPost.getNetworkResponse(HomeActivity.this,null,Utils.global.mapMain,ConstVariable.List);
        }
        else
        {

        }
    }
    public static  void loadRequestsList(Context context,List<HashMap<String, Object>> viewList, String mode)
    {
        // Utils.e(TAG+"81", "load browseMembersList Data"+mode+viewList);
        if (viewList != null && viewList.size() > 0)
        {
            try
            {
                //Utils.e(TAG+"88", "browseMembersList new");
                requestsList = new ArrayList<ListItem>();

                //Utils.e(TAG+"93", "browseMembersList new "+eventList);

                for (int i = 0; i < viewList.size(); i++)
                {
                    HashMap<String, Object> mp = new HashMap<String, Object>();
                    mp = viewList.get(i);

                    if(!requestsList.contains(mp))
                    {
                        requestsList.add(new ListItem(mp));
                    }
                }
                //Utils.e(TAG+"118", "browseMembersList"+eventList.size());
            }
            catch (Exception e)
            {
                //Utils.e(TAG+"122","Exception======================Exception======================Exception");
                e.printStackTrace();
            }
            finally
            {
                // Utils.e(TAG+"127", "browseMembersList"+eventList.size());
                //Utils.e(TAG+"128", "ok");
                if(!mode.equalsIgnoreCase("update"))
                {
                    setAdapterFriendsRequestList(context);
                }
                else
                {
                    requestsAdapter.notifyItemInserted(requestsList.size());
                    requestsAdapter.notifyDataSetChanged();
                }
            }
        }
        else
        {
            rv_loc.setVisibility(View.GONE);
            //noData.setVisibility(View.VISIBLE);
        }
    }

    public static  void setAdapterFriendsRequestList(final Context context)
    {
        if(rv_loc != null)
        {
            rv_loc.setVisibility(View.VISIBLE);
            //noData.setVisibility(View.GONE);
        }

        //Utils.e(TAG+"156", "setAdapter ok "+eventList);
        requestsAdapter = new ListAdapter(mcontext,requestsList,rv_loc,R.layout.list_rowitem, ConstVariable.List);
        //set the adapter object to the Recyclerview
        //Utils.e(TAG+"159", "setAdapter ok "+eventsAdapter.getItemCount());
        rv_loc.setAdapter(requestsAdapter);
    }
}
