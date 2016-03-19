package com.hp.design.material.materialone.ui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hp.design.material.materialone.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class MotionActivityFragment extends Fragment {

    @Bind(R.id.the_recycler_view)
    protected RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public MotionActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_motion, container, false);
        ButterKnife.bind(this, view);

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new TheAdapter(initData());
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    private List<String> initData(){
        List<String> data = new ArrayList<>();
        data.add("IPA");
        data.add("Pale Ale");
        data.add("Porter");
        data.add("Stout");
        data.add("Belgian");
        data.add("Blonde");
        data.add("Red Ale");
        return data;
    }

    public class TheAdapter extends RecyclerView.Adapter<TheAdapter.ViewHolder>{

        private List<String> mData;
        public TheAdapter(List<String> strings){
            mData = strings;
        }

        @Override
        public TheAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_motion, parent, false);
            TheAdapter.ViewHolder viewHolder = new TheAdapter.ViewHolder(v);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mTextView.setText(mData.get(position));
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            public TextView mTextView;
            public CardView mCardView;
            private LinearLayout mClickTarget;
            public ViewHolder(View v){
                super(v);
                mCardView = (CardView) v.findViewById(R.id.card_view);
                mClickTarget = (LinearLayout)v.findViewById(R.id.click_target);
                mClickTarget.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mCardView.setCardElevation(mCardView.getCardElevation()+5);
                    }
                });
                mClickTarget.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        mCardView.setCardElevation(0);
                        return true;
                    }
                });
                mTextView = (TextView) v.findViewById(R.id.text_view_motion_card);
            }
        }
    }
}
