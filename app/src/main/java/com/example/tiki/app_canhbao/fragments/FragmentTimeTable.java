package com.example.tiki.app_canhbao.fragments;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiki.R;
import com.example.tiki.app_canhbao.MainActivityHomes;
import com.example.tiki.app_canhbao.adappers.TimeTableAdapper;
import com.example.tiki.app_canhbao.backend.AuthAccount;
import com.example.tiki.app_canhbao.entity.TimeTable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class FragmentTimeTable extends Fragment {
    private View fView;
    private ImageButton imgBtnNew1,imgBtnNew2;
    private Button btnSaveTimetable, btnCloseTimetable;
    private EditText edtNameSubject, edtNote, edtNameClass_GV;
    private MainActivityHomes mMainActivityHomes;
    private RecyclerView rcvListTimeTable;
    Spinner spnDayLearning, spnStartLearning;
    private TextView tvDayStart, tvDayEnd, tvNameClass_GV;


    private List<TimeTable> mListTimeTableList;
    private TimeTableAdapper mTimeTableAdapper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fView= inflater.inflate(R.layout.fragment_time_table, container, false);
        mMainActivityHomes= (MainActivityHomes) getActivity();
        inits();

        return fView;
    }

    private void inits() {
        imgBtnNew1=fView.findViewById(R.id.imgbtn_NewTimetable);
        mTimeTableAdapper=new TimeTableAdapper(mMainActivityHomes);
        mListTimeTableList=new ArrayList<>();
        rcvListTimeTable=fView.findViewById(R.id.rcv_list_TimeTable);
        LinearLayoutManager manager =new LinearLayoutManager(mMainActivityHomes);
        rcvListTimeTable.setLayoutManager(manager);
        RecyclerView.ItemDecoration item= new DividerItemDecoration(mMainActivityHomes, DividerItemDecoration.VERTICAL);
        rcvListTimeTable.addItemDecoration(item);
//        imgBtnNew2=fView.findViewById(R.id.imgbtn_NewTimetable2);
//        txt =fView.findViewById(R.id.edt_Freeback);

        imgBtnNew1.setOnClickListener(v->{
            openFeebackDialog(Gravity.CENTER);
        });
//        imgBtnNew2.setOnClickListener(v->{
//            openFeebackDialog(Gravity.BOTTOM);
//        });

        getAllTimeTable(mListTimeTableList);
    }

    private void getAllTimeTable(List<TimeTable > l) {
        for (TimeTable t: l){
            Log.e("đối tượng-----> "," "+t.toString());
        }
        mTimeTableAdapper.setmListTimeTable(l);
        rcvListTimeTable.setAdapter(mTimeTableAdapper);
    }

    private void openFeebackDialog(int g){
        final Dialog dialog=new Dialog(mMainActivityHomes);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_timetable);

        Window window= dialog.getWindow();
        if(window==null)
            return;
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity =g;
        window.setAttributes(layoutParams);
        //click ra ngoài tắt dialog
        if(Gravity.BOTTOM == g){
            //dialog.setCancelable(true);
            //chonNgay(1,fView);
        }
        else {
            //dialog.setCancelable(false);
            //chonNgay(1,fView);
        }
        intiDialog(dialog);
        dialog.show();
    }

    private void intiDialog(Dialog dialog) {
        btnSaveTimetable=dialog.findViewById(R.id.btn_SaveTimeTable);
        btnCloseTimetable=dialog.findViewById(R.id.btn_CloseTimeTable);
        tvDayStart=dialog.findViewById(R.id.tv_DayStart);
        tvDayEnd=dialog.findViewById(R.id.tv_DayEnd);
        edtNameSubject=dialog.findViewById(R.id.edt_NameSubjectTimeTable);
        tvNameClass_GV=dialog.findViewById(R.id.tv_nameClass_GV);
        edtNameClass_GV=dialog.findViewById(R.id.edt_nameClass_GV);
        edtNote=dialog.findViewById(R.id.edt_Note);
        spnDayLearning=dialog.findViewById(R.id.spner_BuoiHocTimeTable);
        spnStartLearning=dialog.findViewById(R.id.spner_StartTimeTable);
        if(AuthAccount.getInstant().userAccount.get_category()==1){
          tvNameClass_GV.setText("Tên GV: ");
          edtNameClass_GV.setHint("Nhập tên GV...");
        }
        else {
            tvNameClass_GV.setText("Tên lớp: ");
            edtNameClass_GV.setHint("Nhập tên lóp...");
        }
        getSpinnerDay();
        getStartLearning();
        btnSaveTimetable.setOnClickListener(v->{
            addTimeTablse();
        });
        btnCloseTimetable.setOnClickListener(v->{dialog.dismiss();});
        tvDayStart.setOnClickListener(v->{chonNgay(1, fView);});
        tvDayEnd.setOnClickListener(v->{chonNgay(2,fView);});
    }

    private void addTimeTablse() {
        String _nameSubject = edtNameSubject.getText().toString().trim();
        String _dayLearning=spnDayLearning.getSelectedItem().toString().trim();
        String _startTimeLearning= spnStartLearning.getSelectedItem().toString();
        String _dayStart=tvDayStart.getText().toString();
        String _dayEnd= tvDayEnd.getText().toString().trim();
        String _nameClass_GV=edtNameClass_GV.getText().toString().trim();
        String _note=edtNote.getText().toString().trim();
        if(_nameSubject.equals("") || _nameClass_GV.equals("") || _dayStart.equals("") || _dayEnd.equals("") ){
            Toast.makeText(mMainActivityHomes, "Chưa nhập xong dữ liệu!", Toast.LENGTH_SHORT).show();
        }
        else {
            ///nếu user là sv
            String n1="", n2="";
            if(AuthAccount.getInstant().userAccount.get_category()==1){
                n1=_nameClass_GV;
            }
            else
                n2=_nameClass_GV;
            mListTimeTableList.add(new TimeTable(_nameSubject, _startTimeLearning,
                    _dayLearning,_dayStart, _dayEnd, _note,n1, n2));
            getAllTimeTable(mListTimeTableList);
            Toast.makeText(mMainActivityHomes,
                    "Thêm dữ liệu thành công",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void getSpinnerDay(){
        ArrayList<String> lSpinner =AuthAccount.getInstant().getSpinnerLession();
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, lSpinner);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnDayLearning.setAdapter(arrayAdapter);
    }
    private void getStartLearning(){
        ArrayList<String> mLisst=AuthAccount.getInstant().getStartLearning();
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, mLisst);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnStartLearning.setAdapter(arrayAdapter);
    }
    public void chonNgay(final int loai, View v) {
        //khai báo ngày tùy biến theo thời gian thực
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        String s = "";
        //xây dựng DatePicker
        DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //gán thời gian người dùng lựa chọn
                calendar.set(year, month, dayOfMonth);
                //định dạng ngày theo kiểu ngày / tháng / năm
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                //đưa dữ liệu vào edittext
                if (loai == 1) {
                    tvDayStart.setText(simpleDateFormat.format(calendar.getTime()));
                    Log.e("ngày chọn: ---->> ",""+simpleDateFormat.format(calendar.getTime()));
                    //s="" +year+"/"+month+"/"+dayOfMonth;
                } else {
                    if(tvDayStart.getText().toString().isEmpty()){
                        Toast.makeText(mMainActivityHomes, "Chưa chọn ngày bắt đầu?", Toast.LENGTH_SHORT).show();
                    }else {
                        try {
                            if(checkDayEnd(tvDayStart.getText().toString(), simpleDateFormat.format(calendar.getTime()))){
                                Log.e("ngày chọn: ---->> đạt chuẩn ", "" + simpleDateFormat.format(calendar.getTime()));
                                tvDayEnd.setText(simpleDateFormat.format(calendar.getTime()));
                            }
                            else {
                                Toast.makeText(mMainActivityHomes, "Chọn ngày chưa hợp lệ!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }
//                Log.e("ngày chọn: finsh ---->> ",""+year+"/"+month+"/"+dayOfMonth);
            }
        }, nam, thang, ngay);
        //hiển thị DatePicker
        datePickerDialog.show();
    }
    private boolean checkDayEnd(String d1, String d2) throws ParseException {
        final Calendar calendar = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dStart= simpleDateFormat.parse(d1);
        Date dEnd= simpleDateFormat.parse(d2);
        assert dEnd != null;
        return dEnd.compareTo(dStart) > 0;
    }
}