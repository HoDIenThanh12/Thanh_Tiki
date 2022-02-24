package com.example.tiki.app_canhbao.adappers;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tiki.R;
import com.example.tiki.app_canhbao.entity.Meettings;
import com.example.tiki.app_canhbao.interfaces.EventUser;

import java.util.List;

public class ListMeetingAdaper extends RecyclerView.Adapter<ListMeetingAdaper.ListMeetingAdaperViewHondler> {
    List<Meettings> lMeetting;
    Context context;
    EventUser eventUser;
    public ListMeetingAdaper(Context context) {
        this.context = context;
    }

    public ListMeetingAdaper(EventUser eventUser) {
        this.eventUser = eventUser;
    }

    public void setlMeetting(List<Meettings> lMeetting) {
        this.lMeetting = lMeetting;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListMeetingAdaperViewHondler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.items_meeting, parent, false);
        return new ListMeetingAdaperViewHondler(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListMeetingAdaperViewHondler holder, int position) {
        Meettings met = lMeetting.get(position);
        if(met==null)
            return;
        holder.tvNameMeeting.setText(met.get_nameMeet());
        holder.tvTimeMeeting.setText(met.get_timeStart());
        holder.tvDateMeeting.setText(met.get_dayStart());
        holder.btnListMeeting.setOnClickListener(v->{
            eventUser.ListMeetting(met);});
        holder.btnEditMeetting.setOnClickListener(v->{
            eventUser.EditMeetting(met);
            });
        holder.btnDeleteMeeting.setOnClickListener(v->{
            eventUser.DeleteMeetting(met);
            });
    }

    @Override
    public int getItemCount() {
        if(lMeetting!=null)
            return lMeetting.size();
        return 0;
    }

    public class ListMeetingAdaperViewHondler extends RecyclerView.ViewHolder {
        TextView tvNameMeeting, tvTimeMeeting, tvDateMeeting;
        Button btnListMeeting, btnDeleteMeeting, btnEditMeetting;
        public ListMeetingAdaperViewHondler(@NonNull View itemView) {
            super(itemView);
            tvNameMeeting = itemView.findViewById(R.id.tv_NameMeetting);
            tvTimeMeeting = itemView.findViewById(R.id.tv_TimeMeetting);
            tvDateMeeting = itemView.findViewById(R.id.tv_DateMeetting);
            btnListMeeting = itemView.findViewById(R.id.btn_ListMeetting);
            btnDeleteMeeting =itemView.findViewById(R.id.btn_DeleteMeetting);
            btnEditMeetting = itemView.findViewById(R.id.btn_EditMeetting);
        }
    }
}
