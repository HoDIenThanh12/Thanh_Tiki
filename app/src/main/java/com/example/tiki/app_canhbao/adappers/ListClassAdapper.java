package com.example.tiki.app_canhbao.adappers;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tiki.R;
import com.example.tiki.app_canhbao.entity.ClassCB;
import com.example.tiki.app_canhbao.viewmodel.ListClassViewModule;

import java.util.List;

public class ListClassAdapper  extends RecyclerView.Adapter<ListClassAdapper.ListClassAdapperViewHolder> {

    private Context context;
    private List<ClassCB> lClassCB;

    public ListClassAdapper(Context context) {
        this.context = context;
    }

    public void setlClassCB(List<ClassCB> lClassCB) {
        this.lClassCB = lClassCB;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListClassAdapperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v= layoutInflater.inflate(R.layout.custom_item_class_cb,parent, false);
        return new ListClassAdapperViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListClassAdapperViewHolder holder, int position) {
        ClassCB classCB =lClassCB.get(position);
        if(classCB==null) {
            Log.d("lỗi khởi tạo---> ","fail");
            return;
        }
        Log.e("lỗi khởi tạo---> ","pass" + classCB.get_nameClassCB());
        holder.tvNameClass.setText(classCB.get_nameClassCB()+"sdfsdf 1");
        holder.tvNoteClass.setText(classCB.get_noteClassCB()+"sdfsdfsdf 2" );
    }

    @Override
    public int getItemCount() {
        if(lClassCB!=null)
            return lClassCB.size();
        return 0;
    }

    public class ListClassAdapperViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameClass, tvNoteClass;
        public ListClassAdapperViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameClass=itemView.findViewById(R.id.tv_NameClassCB);
            tvNoteClass=itemView.findViewById(R.id.tv_NoteClassCB);
        }
    }
}
