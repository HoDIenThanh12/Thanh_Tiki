package com.example.tiki.app_canhbao.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tiki.R;

public class FragmentLogin extends Fragment {
    private View fView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fView=  inflater.inflate(R.layout.fragment_login, container, false);
        return fView;
    }
}