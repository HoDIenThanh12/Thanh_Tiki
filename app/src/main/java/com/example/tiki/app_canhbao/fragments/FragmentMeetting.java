package com.example.tiki.app_canhbao.fragments;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.tiki.R;
import com.example.tiki.app_canhbao.MainActivityHomes;
import com.example.tiki.app_canhbao.adappers.ListMeetingAdaper;
import com.example.tiki.app_canhbao.entity.Meettings;
import com.example.tiki.app_canhbao.interfaces.EventUser;
import com.example.tiki.app_canhbao.views.MainActivityUserListMeetting;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class FragmentMeetting extends Fragment {
    private View fView;
    private ImageButton fimgbtnNewMetting;
    private RecyclerView rcvListMeetting;
    private MainActivityHomes mMainActivityHomes;
    Button btnSave, btnClose;
    TextView tvNameMeetting, tvDatetimeMeeting, tvTimeMeeting;
    //import recyclerview
    ListMeetingAdaper mListMeeettingAdaper;
    List<Meettings> mListMeetting;
    Boolean isTime=false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fView= inflater.inflate(R.layout.fragment_meetting, container, false);
        mMainActivityHomes= (MainActivityHomes) getActivity();
        inits();
        return fView;
    }
    private void inits(){
        fimgbtnNewMetting=fView.findViewById(R.id.bnt_NewMeettingF);
        fimgbtnNewMetting.setOnClickListener(v->{
            newMetting();
        });
        mListMeeettingAdaper=new ListMeetingAdaper(new EventUser() {
            @Override
            public void ListMeetting(Meettings m) {
                ListUser(m);
            }

            @Override
            public void EditMeetting(Meettings m) {
                EditMeettingF(m);
            }

            @Override
            public void DeleteMeetting(Meettings m) {
                DeleteMeettingF(m);
            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(mMainActivityHomes);
        rcvListMeetting = fView.findViewById(R.id.rcv_ListMeeting);
        rcvListMeetting.setLayoutManager(manager);
        RecyclerView.ItemDecoration item= new DividerItemDecoration(mMainActivityHomes, DividerItemDecoration.VERTICAL);
        rcvListMeetting.addItemDecoration(item);
        mListMeetting =new ArrayList<>();
        getAllListMeetting(mListMeetting);
    }

    private void getAllListMeetting(List<Meettings> mListMeetting) {
        for(Meettings m: mListMeetting){
            Log.e("đối tượng-----> "," "+m.toString());
        }
        mListMeeettingAdaper.setlMeetting(mListMeetting);
        rcvListMeetting.setAdapter(mListMeeettingAdaper);
    }

    private void newMetting() {
        openDialog(0, null);
        //Toast.makeText(mMainActivityHomes, "Thêm cuộc họp", Toast.LENGTH_SHORT).show();
    }
    private void openDialog(int temp, Meettings m){
        final Dialog dialog=new Dialog(mMainActivityHomes);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_meeting);
        Window window=dialog.getWindow();
        if(window==null)
            return;
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams params =window.getAttributes();
        window.setAttributes(params);
        initDialog(dialog, temp, m);
        dialog.show();
    }
    private void initDialog(Dialog d, int isEdit, Meettings m){
        tvNameMeetting= d.findViewById(R.id.txt_NameMeettingD);
        tvTimeMeeting=d.findViewById(R.id.tv_TimeMeettingD);
        tvDatetimeMeeting=d.findViewById(R.id.tv_DatetimeMeettingD);
        btnSave=d.findViewById(R.id.btn_SaveMeeting);
        btnClose=d.findViewById(R.id.btn_CloasMeeting);
        if(isEdit==0){
            btnSave.setText("Lưu");
        }
        else {
            btnSave.setText("Lưu thay đổi");
            tvNameMeetting.setText(m.get_nameMeet().trim());
            tvTimeMeeting.setText(m.get_timeStart().trim());
            tvDatetimeMeeting.setText(m.get_dayStart());
        }
        tvTimeMeeting.setOnClickListener(v->{
            showTimepicker();
        });
        tvDatetimeMeeting.setOnClickListener(v->{
            if(isTime){
                showDatetimepicker(fView);
            }
            else {
                Toast.makeText(mMainActivityHomes, "Chưa chọn thời gian?", Toast.LENGTH_SHORT).show();
            }
        });
        btnSave.setOnClickListener(v->{saveData(d);});
        btnClose.setOnClickListener(v->{
            //timeMeeting.setVisibility(View.VISIBLE);
            //showTimepicker();
            d.dismiss();
        });
    }
    private void saveData(Dialog d){
        Toast.makeText(mMainActivityHomes, "Thêm thành công", Toast.LENGTH_SHORT).show();
        String nameMeetting = tvNameMeetting.getText().toString().trim();
        String timemeetting= tvTimeMeeting.getText().toString().trim();
        String dateMeetting= tvDatetimeMeeting.getText().toString().trim();;
        mListMeetting.add(new Meettings(nameMeetting,timemeetting,dateMeetting));
        getAllListMeetting(mListMeetting);
        //d.dismiss();
    }
    private void showTimepicker(){
        isTime=true;
        Calendar calendar=Calendar.getInstance();
        int hour=calendar.get(Calendar.HOUR);
        int mint=calendar.get(Calendar.MINUTE);
        TimePickerDialog dialog = new TimePickerDialog(mMainActivityHomes, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Toast.makeText(mMainActivityHomes, "--> "+hourOfDay+"| "+minute, Toast.LENGTH_SHORT).show();
                SimpleDateFormat timNow= new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
                Date day=Calendar.getInstance().getTime();

                tvTimeMeeting.setText(""+hourOfDay+":"+minute);
            }
        }, hour,mint, false);
        dialog.show();
    }
    private void showDatetimepicker(View v){
        //khai báo ngày tùy biến theo thời gian thực
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        Date dayTime=Calendar.getInstance().getTime();
        SimpleDateFormat formats=new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());

        String s = "";
        //xây dựng DatePicker
        DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //gán thời gian người dùng lựa chọn
                calendar.set(year, month, dayOfMonth);
                //đưa dữ liệu vào textview
                try {
                    Date itemDate = formats.parse(formats.format(calendar.getTime()));
                    if(dayTime.compareTo(itemDate)<1){
                        assert itemDate != null;
                        tvDatetimeMeeting.setText(""+formats.format(itemDate) +"---| "+dayTime.compareTo(itemDate)
                                + "|| "+formats.format(itemDate) +" |--| "+formats.format(dayTime));
                        int t= itemDate.getHours();
                    }
                    else {
                        tvDatetimeMeeting.setText("sai ngày chọn"+"---| "+dayTime.compareTo(itemDate)
                                + "|| "+formats.format(itemDate) +" |--| "+formats.format(dayTime));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        }, nam, thang, ngay);
        //hiển thị DatePicker
        datePickerDialog.show();
    }
    private void ListUser(Meettings m){
        Intent i = new Intent(mMainActivityHomes, MainActivityUserListMeetting.class);
        Bundle bundle =new Bundle();
        bundle.putSerializable("Meetting",m );
        startActivity(i);
        Log.e("","Danh sách");
    }
    private void EditMeettingF(Meettings m){
        openDialog(1, m);
        Log.e("","edit Danh sách");
    }
    private void DeleteMeettingF(Meettings m){
        getAllListMeetting(mListMeetting);
        Log.e("","dele Danh sách");
    }
}