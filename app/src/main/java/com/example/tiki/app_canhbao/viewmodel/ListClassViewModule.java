package com.example.tiki.app_canhbao.viewmodel;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tiki.app_canhbao.entity.ClassCB;

import java.util.List;

public class ListClassViewModule extends RecyclerView.Adapter<ListClassViewModule.ListClassViewModuleViewHolder> {

    private List<ClassCB> lClassCB;

    public ListClassViewModule(List<ClassCB> lClassCB) {
        this.lClassCB = lClassCB;
    }

    @NonNull
    @Override
    public ListClassViewModuleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View v =
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ListClassViewModuleViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if(lClassCB!=null)
            return lClassCB.size();
        return 0;
    }

    public class ListClassViewModuleViewHolder extends RecyclerView.ViewHolder {

        public ListClassViewModuleViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
