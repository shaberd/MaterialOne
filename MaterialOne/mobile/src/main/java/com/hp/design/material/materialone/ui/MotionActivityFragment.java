package com.hp.design.material.materialone.ui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        data.add("ONE");
        data.add("TWO");
        data.add("THREE");
        return data;
    }

    public class TheAdapter extends RecyclerView.Adapter<TheAdapter.ViewHolder>{

        private List<String> mData;
        public TheAdapter(List<String> strings){
            mData = strings;
        }

        @Override
        public TheAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            CardView v = (CardView)LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_motion, parent, false);
            TheAdapter.ViewHolder viewHolder = new TheAdapter.ViewHolder(v);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.mTextView.setText(mData.get(position));
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            public TextView mTextView;
            public ViewHolder(CardView v){
                super(v);
                mTextView = (TextView) v.findViewById(R.id.text_view_motion_card);
            }
        }
    }
}
