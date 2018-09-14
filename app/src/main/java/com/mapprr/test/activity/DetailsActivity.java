package com.mapprr.test.activity;

    import android.content.Intent;
    import android.graphics.Bitmap;
    import android.net.Uri;
    import android.os.Bundle;
    import android.support.design.widget.AppBarLayout;
    import android.support.design.widget.FloatingActionButton;
    import android.support.design.widget.Snackbar;
    import android.support.v7.app.AppCompatActivity;
    import android.support.v7.widget.Toolbar;
    import android.text.Html;
    import android.view.View;
    import android.view.Menu;
    import android.view.MenuItem;
    import android.widget.ImageView;
    import android.widget.TextView;

    import com.mapprr.test.R;

    import butterknife.BindView;
    import butterknife.ButterKnife;
    import butterknife.Unbinder;

public class DetailsActivity extends AppCompatActivity
{
    private Menu menu;
    private Unbinder unbinder;

    @BindView(R.id.expandedImage)
    ImageView photo;

    @BindView(R.id.link)
    TextView link;

    @BindView(R.id.name)
    TextView name;

    @BindView(R.id.des)
    TextView description;

    String proURL="";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        unbinder = ButterKnife.bind(this);

        final Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        String styledText = "<u><font color='blue'>Click here</font></u>.";
        link.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (getIntent()!=null)
        {
            Bitmap bitmap = (Bitmap) getIntent().getParcelableExtra("photo");
            String pname =  getIntent().getStringExtra("name");
            String des = getIntent().getStringExtra("description");
            proURL =  getIntent().getStringExtra("html_url");

            if (bitmap!=null)
                photo.setImageBitmap(bitmap);

            if (!pname.equalsIgnoreCase(""))
            {
                name.setText(pname);
                setTitle(pname);
            }

            if (!des.equalsIgnoreCase(""))
                description.setText(des);
        }

        link.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (!proURL.equalsIgnoreCase(""))
                {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(proURL));
                    startActivity(i);
                }

            }
        });

        AppBarLayout mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener()
        {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset)
            {
                if (scrollRange == -1)
                {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0)
                {
                    isShow = true;
                    showOption(R.id.action_info);
                }
                else if (isShow)
                {
                    isShow = false;
                    hideOption(R.id.action_info);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_main, menu);
        hideOption(R.id.action_info);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_info) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void hideOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(false);
    }

    private void showOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(true);
    }
}

