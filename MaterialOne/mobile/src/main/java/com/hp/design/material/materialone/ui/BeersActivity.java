package com.hp.design.material.materialone.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.hp.design.material.materialone.Constants;
import com.hp.design.material.materialone.R;
import com.hp.design.material.materialone.beer.pojo.Beer;
import com.hp.design.material.materialone.beer.BreweryDB;
import com.hp.design.material.materialone.beer.pojo.BeerElement;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BeersActivity extends AppCompatActivity {

    private static final String TAG = "asdf";

    @Bind(R.id.beers_rv)
    RecyclerView mBeersRv;
    private final List<BeerElement> mBeersList = new ArrayList<>();
    private RecyclerView.Adapter<BeerEntryViewHolder> mBeersRvAdapter;

    BreweryDB mBreweryDB;
    int mPageCount = 0;
    private boolean mDoneLoading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    LinearLayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beers);
        ButterKnife.bind(this);
        mBeersRvAdapter = new BeerEntryRecyclerAdapter(getApplicationContext(), mBeersList);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mBeersRv.setLayoutManager(mLayoutManager);
        mBeersRv.setAdapter(mBeersRvAdapter);
        mBeersRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy>0){
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();
                    if (mDoneLoading)
                    {
                        if ( (visibleItemCount + pastVisiblesItems) >= totalItemCount)
                        {
                            Log.v("...", "Last Item Wow !");
                            updateData();
                            //Do pagination.. i.e. fetch new data
                        }
                    }
                }
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MotionActivity.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
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
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBeersList.clear();
        mBeersRvAdapter.notifyDataSetChanged();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mBreweryDB = retrofit.create(BreweryDB.class);
        updateData();

    }

    private void updateData() {
        if (null != mBreweryDB) {
            mDoneLoading = false;
            Call<Beer> beers = mBreweryDB.getBeers(Constants.API_KEY, "-10", mPageCount++);
            beers.enqueue(new Callback<Beer>() {
                @Override
                public void onResponse(Call<Beer> call, Response<Beer> response) {
                    if (null != response) {
                        Log.d(TAG, "onResponse: message: " + response.message());
                        Log.d(TAG, "onResponse: code: " + response.code());
                        Log.d(TAG, "onResponse: errorBody: " + response.errorBody());
                        Beer beerList = response.body();
                        if (beerList != null && beerList.getData() != null) {
                            Log.d(TAG, "getCurrentpage: " + beerList.getCurrentpage());
                            Log.d(TAG, "getNumberofpages: " + beerList.getNumberofpages());
                            Log.d(TAG, "getTotalresults: " + beerList.getTotalresults());
                            for (BeerElement de : beerList.getData()) {
                                mBeersList.add(de);
                                mBeersRvAdapter.notifyDataSetChanged();
                                Log.d(TAG, "onResponse: " + de.getName() + " " + de.getAbv() + " " + de.getDescription());
                            }
                        }
                        Log.d(TAG, "onResponse: count: " + mBeersList.size());
                        mDoneLoading = true;
//                    try {
//                        Log.d(TAG, "onResponse: RAW: " + response.raw().body().string());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }

                    }
                }

                @Override
                public void onFailure(Call<Beer> call, Throwable t) {
                    Log.e(TAG, "onFailure: ", t);
                    mDoneLoading = true;

                }
            });
        }
    }
}
