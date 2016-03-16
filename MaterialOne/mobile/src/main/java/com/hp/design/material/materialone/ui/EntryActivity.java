package com.hp.design.material.materialone.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.hp.design.material.materialone.Constants;
import com.hp.design.material.materialone.R;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EntryActivity extends AppCompatActivity {

    @Bind(R.id.banner_img)
    ImageView mImageView;

    @Bind(R.id.webview)
    WebView mWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                startActivity(new Intent(getApplicationContext(), BeersActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWebview.loadUrl("http://www.brewerydb.com/developers");
        Picasso.with(getApplicationContext()).load(Constants.BANNER).into(mImageView);

    }

}
