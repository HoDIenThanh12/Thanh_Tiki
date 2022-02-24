package com.example.tiki.app_canhbao.backend;

import static androidx.core.app.ActivityCompat.finishAffinity;

import static com.example.tiki.app_canhbao.constants.CONSTANTS.PATH_DTAREATIME_USER;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tiki.app_canhbao.entity.User;
import com.example.tiki.firebase.AuthEmails;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AuthAccount {
    public static AuthAccount _AuthEmails = null;
    public User userAccount =null;
    private  FirebaseAuth auth;
    private  FirebaseUser user;
    private  boolean isCreate;

    private ProgressDialog dialog;

    public static AuthAccount getInstant(){
        if(_AuthEmails==null){
            _AuthEmails =new AuthAccount();
            _AuthEmails.userAccount=new User();
        }
        return _AuthEmails;
    }
    public void Navigations(Context mF, Class<?> clas ){
        Intent intent =new Intent(mF,clas);
        mF.startActivity(intent);
        //finishAffinity((Activity) mF);
    }
    public void Log(Context mContext,String e, String p, Class<?> clas ){
        FirebaseAuth auth =FirebaseAuth.getInstance();
        dialog=new ProgressDialog(mContext);
        dialog.show();
        auth.signInWithEmailAndPassword(e, p)
                .addOnCompleteListener((Activity) mContext, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("---> ", "signInWithEmail: success");
                                FirebaseUser user = auth.getCurrentUser();
                                _AuthEmails.userAccount.set_id(user.getUid());
                            _AuthEmails.GetAccount(user.getUid()+"");
                            Log.d("---> ", "idusser: "+user.getUid());
//                                updateUI(user);
                            dialog.dismiss();
                            Navigations(mContext,clas);
                            dialog=null;
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG---->  ", "signInWithEmail: fail");
                            dialog.dismiss();
                                Toast.makeText(mContext, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
//                                updateUI(null);
                        }
                    }
                });
    }
    public void Register(Context mFirst, User u, Class<?> mLast ){
        auth=FirebaseAuth.getInstance();
        dialog=new ProgressDialog(mFirst);
        dialog.show();
        auth.createUserWithEmailAndPassword(u.get_Email(), u.get_Pass())
                .addOnCompleteListener((Activity) mFirst, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("cr---> ", "createUserWithEmail:success");
                            FirebaseUser user = auth.getCurrentUser();
                            PushAboutAccount(user,u);
                            Toast.makeText(mFirst, "Authentication success.",
                                    Toast.LENGTH_SHORT).show();
                            setCreate(true);
                            Navigations(mFirst,mLast);
                            dialog.dismiss();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("cr---> ", "createUserWithEmail:failure", task.getException());
                            dialog.dismiss();
                            Toast.makeText(mFirst, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            setCreate(false);
                            //isCreate=false;
                            //updateUI(null);
                        }
                    }
                });
    }
    public void PushAboutAccount(FirebaseUser u,User us){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(PATH_DTAREATIME_USER);
        //String userId = myRef.push().getKey();
        Log.w("cr---> ", "id đăng ký "+u.getUid());
        User user=new User(u.getUid(), us.get_Name(), "","",
                "","",u.getEmail(),us.get_Pass(),us.get_category());
        //_AuthEmails.userAccount=us;
        myRef.child(u.getUid()).setValue(user);
    //cách khác
//        myRef.setValue(u, new DatabaseReference.CompletionListener() {
//            @Override
//            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//
//            }
//        });
    }
    public void GetAccount(String id){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference =database.getReference(PATH_DTAREATIME_USER);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    User utemp =dataSnapshot.getValue(User.class);
                    if(utemp.get_id().equals(_AuthEmails.userAccount.get_id())){
                        _AuthEmails.userAccount=utemp;
                        //_AuthEmails.userAccount.set_Name(utemp.get_Name());
                        Log.e("user--> "," IDobj: "+id);
                        Log.e("user--> "," obj: "+_AuthEmails.userAccount);
                        Log.e("user--> "," id: "+utemp.get_id()+
                                " id: "+utemp.get_Name()+
                                " id: "+utemp.get_MSSV());
                    }
                }

//               if(utemp.get_id().equals(id)){
//                   _AuthEmails.userAccount=utemp;
//               }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void updateAccount(User u){
        FirebaseDatabase database =FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(PATH_DTAREATIME_USER).child(u.get_id());
        //cách 1 setvalues đối tượng ==> update cả đối tượng
//        reference.setValue(u, new DatabaseReference.CompletionListener() {
//            @Override
//            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//
//            }
//        });
        //cách 2
//        reference= database.getReference(PATH_DTAREATIME_USER+"/"+"thuộc tính");
//        reference.setValue("giá trị", new DatabaseReference.CompletionListener() {
//            @Override
//            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//
//            }
//        });
        //có thể dùng reference.child("đường dẫn thay đổi thuộc tính");
        //cách 3 update những giá trị cần update
        Map<String, Object> map=new HashMap<>();
        map.put("_Name",u.get_Name());
        map.put("_Class",u.get_Class());
        reference.updateChildren(map, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Log.e("update---> ","success");
            }
        });
    }
    public void Singouts(String e, String p){

    }
    public void PushDataRealtime(){

    }
    public boolean getisCreate() {
        return isCreate;
    }
    public ArrayList<String> getSpinnerLession(){
        ArrayList<String> list =new ArrayList<>();
        for (int i=2;i<=8;i++){
            list.add(i==8 ?"Chủ nhật": "Thứ "+i);
        }
        return list;
    }
    public ArrayList<String> getStartLearning(){
        ArrayList<String> list =new ArrayList<String>();
        for (int i=1;i<=14;i++){
            list.add("Tiết " +i);
        }
        return list;
    }
    public void setCreate(boolean create) {
        isCreate = create;
    }
}

