package com.example.tiki.firebase;

import static androidx.core.app.ActivityCompat.finishAffinity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

public class AuthEmails {
    private AuthEmails _AuthEmails = null;
    private  FirebaseAuth auth;
    private FirebaseUser user;
    private  boolean isCreate;
    private ProgressDialog dialog;
    public AuthEmails getInstant(){
        if(_AuthEmails==null){
            _AuthEmails =new AuthEmails();
        }
        return _AuthEmails;
    }
    public void Navigations(Context mF, Class<?> clas ){
        Intent intent =new Intent(mF,clas);
        mF.startActivity(intent);
        finishAffinity((Activity) mF);
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
                                Log.d("---> ", "signInWithEmail:success");
//                                FirebaseUser user = mAuth.getCurrentUser();
//                                updateUI(user);
                            dialog.dismiss();
                            Navigations(mContext,clas);
                            dialog=null;
                        } else {
                            // If sign in fails, display a message to the user.
                                Log.w("TAG---->  ", "signInWithEmail:failure", task.getException());
//                                Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
//                                        Toast.LENGTH_SHORT).show();
//                                updateUI(null);
                        }
                    }
                });
    }
    public void Singins(Context mFirst, String e, String p, Class<?> mLast ){
        auth=FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(e, p)
                .addOnCompleteListener((Activity) mFirst, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("cr---> ", "createUserWithEmail:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            updateUI(user);
                            Toast.makeText(mFirst, "Authentication success.",
                                    Toast.LENGTH_SHORT).show();
                            setCreate(true);
                            Navigations(mFirst,mLast);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("cr---> ", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(mFirst, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            setCreate(false);
                            //isCreate=false;
                            //updateUI(null);
                        }
                    }
                });
        //auth=FirebaseAuth.getInstance();
//        Intent intent =new Intent(mContext, clas);
//        mContext.startActivity(intent);
//        finishAffinity((Activity) mContext.getApplicationContext());
    }
    public void Singouts(String e, String p){

    }
    public void PushDataRealtime(){

    }
    public boolean getisCreate() {
        return isCreate;
    }

    public void setCreate(boolean create) {
        isCreate = create;
    }
}
