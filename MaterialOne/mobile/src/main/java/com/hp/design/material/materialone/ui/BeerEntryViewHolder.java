package com.hp.design.material.materialone.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hp.design.material.materialone.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by simingth on 6/23/15.
 */
public class BeerEntryViewHolder extends RecyclerView.ViewHolder{

    @Bind(R.id.icon_iv)
    public ImageView mIcon;

    @Bind(R.id.beer_name)
    public TextView mBeerName;

    @Bind(R.id.ibu_tv)
    public TextView mIbu;

    @Bind(R.id.abv_tv)
    public TextView mAbv;

    @Bind(R.id.desc_tv)
    public TextView mDesc;

    public int mBeerPosition;

    public BeerEntryViewHolder(View view){
        super(view);
        ButterKnife.bind(this, view);
    }
}
