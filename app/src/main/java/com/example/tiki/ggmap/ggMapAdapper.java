package com.example.tiki.ggmap;


import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tiki.R;
import com.example.tiki.ggmap.roomdatabase.ggMapEntity;

import java.util.List;

public class ggMapAdapper extends RecyclerView.Adapter<ggMapAdapper.ggMapAdapperViewHolder> {

    List<ggMapEntity> lggMap;

    public ggMapAdapper() {
    }

    public ggMapAdapper(List<ggMapEntity> lggMap) {
        this.lggMap = lggMap;
    }

    public void setLggMap(List<ggMapEntity> lggMap) {
        this.lggMap = lggMap;
    }

    @NonNull
    @Override
    public ggMapAdapperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_ggmap_local, parent, false);
        return new ggMapAdapperViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ggMapAdapperViewHolder holder, int position) {
        ggMapEntity gg=lggMap.get(position);
        if(gg==null)
            return;
        holder.tvSTT.setText("stt: "+position);
        holder.tvLat.setText("laitude: "+gg.get_latitude()+"");
        holder.tvLong.setText("longitude: "+gg.get_longitude()+"");
        holder.tvTime.setText("DateTime: "+gg.get_time()+"");
        holder.tvAdres.setText("Address: "+gg.get_address()+"");
    }

    @Override
    public int getItemCount() {
        if(lggMap!=null)
            return lggMap.size();
        return 0;
    }

    public class ggMapAdapperViewHolder extends RecyclerView.ViewHolder {
        private TextView tvSTT, tvLong, tvLat, tvTime, tvAdres;
        public ggMapAdapperViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLat=itemView.findViewById(R.id.tv_latiC);
            tvLong=itemView.findViewById(R.id.tv_longiC);
            tvAdres=itemView.findViewById(R.id.tv_adresC);
            tvTime=itemView.findViewById(R.id.tv_timeC);
            tvSTT=itemView.findViewById(R.id.tv_stv);
        }
    }

}
