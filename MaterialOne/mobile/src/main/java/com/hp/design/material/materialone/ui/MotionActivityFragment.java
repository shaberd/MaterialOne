package com.hp.design.material.materialone.ui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hp.design.material.materialone.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class MotionActivityFragment extends Fragment {

    public MotionActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_motion, container, false);
    }
}
