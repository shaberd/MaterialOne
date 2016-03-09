package com.hp.design.material.materialone.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hp.design.material.materialone.R;
import com.hp.design.material.materialone.beer.pojo.BeerElement;
import com.hp.design.material.materialone.beer.pojo.Labels;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by dashab on 8/10/15.
 */
public class BeerEntryRecyclerAdapter extends RecyclerView.Adapter<BeerEntryViewHolder> {

    private WeakReference<Context> mContextRef;
    private List<BeerElement> mBeers;

    public BeerEntryRecyclerAdapter(Context context, List<BeerElement> printers) {
        mBeers = printers;
        mContextRef = new WeakReference<>(context);
    }

    @Override
    public BeerEntryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.beer_entry, null);
        return new BeerEntryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BeerEntryViewHolder holder, int position) {
        BeerElement beerElement = mBeers.get(position);
        //set stuff on the viewHolder with data from beerElement object
        if (holder != null && beerElement != null) {
            //TODO: would this code make more sense in the ViewHolder object?

            Labels labels = beerElement.getLabels();
            if(null!=labels) {
                final String url = labels.getIcon();
                Picasso.with(mContextRef.get())
                        .load(url)
                        .placeholder(R.drawable.circle_red)
                        .error(R.drawable.circle_light_grey)
                        .into(holder.mIcon);
            }
            holder.mBeerName.setText(beerElement.getName());
            holder.mBeerPosition = position;
            holder.mAbv.setText(beerElement.getAbv());
            holder.mIbu.setText(beerElement.getIbu());
            holder.mDesc.setText(beerElement.getDescription());
        }
    }

    @Override
    public int getItemCount() {
        return (mBeers != null ? mBeers.size() : 0);
    }

    public void setDevices(List<BeerElement> devices) {
        mBeers = new ArrayList<>(devices);
    }

    public BeerElement removeItem(int position) {
        final BeerElement model = mBeers.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem(int position, BeerElement devices) {
        mBeers.add(position, devices);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final BeerElement model = mBeers.remove(fromPosition);
        mBeers.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }

    public void animateTo(List<BeerElement> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }
    private void applyAndAnimateRemovals(List<BeerElement> newPrinters) {
        for (int i = mBeers.size() - 1; i >= 0; i--) {
            final BeerElement printer = mBeers.get(i);
            if (!newPrinters.contains(printer)) {
                removeItem(i);
            }
        }
    }
    private void applyAndAnimateAdditions(List<BeerElement> newPrinters) {
        for (int i = 0, count = newPrinters.size(); i < count; i++) {
            final BeerElement model = newPrinters.get(i);
            if (!mBeers.contains(model)) {
                addItem(i, model);
            }
        }
    }
    private void applyAndAnimateMovedItems(List<BeerElement> newPrinters) {
        for (int toPosition = newPrinters.size() - 1; toPosition >= 0; toPosition--) {
            final BeerElement model = newPrinters.get(toPosition);
            final int fromPosition = mBeers.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }
}
