package com.example.tiki.app_canhbao.adappers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tiki.R;
import com.example.tiki.app_canhbao.entity.TimeTable;

import java.util.List;

public class TimeTableAdapper extends RecyclerView.Adapter<TimeTableAdapper.TimeTableAdapperViewHolder>{

    private Context context;
    private List<TimeTable> mList;

    public TimeTableAdapper(Context context) {
        this.context = context;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setmListTimeTable(List<TimeTable> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TimeTableAdapperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_timetable,parent,false);
        return new TimeTableAdapperViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeTableAdapperViewHolder holder, int position) {
        TimeTable timeTable=mList.get(position);
        if(timeTable==null){
            return;
        }
        holder.tvNameSubjec.setText(timeTable.get_nameSubject() );
        holder.tvDayLearning.setText(timeTable.get_dayLearning() );
        holder.tvStartTimeLearning.setText(timeTable.get_startTimeLearning());
        holder.tvdayStart.setText("Thời gian từ: "+timeTable.get_dayStart());
        holder.tvdayEnd.setText("Đến: "+timeTable.get_dayEnd());
        if(timeTable.get_nameGV().isEmpty()){
            holder.tvNameClass_GV.setText("Tên GV: "+timeTable.get_nameClass());
        }
        else {
            holder.tvNameClass_GV.setText("Tên lớp: "+timeTable.get_nameGV());
        }
        Log.e("timetable: --->> "," "+timeTable.get_nameGV()+"    |  "+timeTable.get_nameClass());
    }

    @Override
    public int getItemCount() {
        if(mList!=null)
            return mList.size();
        return 0;
    }

    public class TimeTableAdapperViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNameClass_GV, tvNameSubjec, tvStartTimeLearning, tvDayLearning, tvdayStart, tvdayEnd, tvNote;
        public TimeTableAdapperViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameSubjec=itemView.findViewById(R.id.tv_item_NameSubject);
            tvStartTimeLearning=itemView.findViewById(R.id.tv_item_StartTimelearning);
            tvDayLearning=itemView.findViewById(R.id.tv_item_Dayleraning);
            tvdayStart=itemView.findViewById(R.id.tv_item_dayStart);
            tvdayEnd=itemView.findViewById(R.id.tv_item_dayEnd);
            tvNameClass_GV=itemView.findViewById(R.id.tv_item_nameClass_GV);
        }
    }
}
