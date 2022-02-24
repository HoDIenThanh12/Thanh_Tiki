package com.example.tiki.app_canhbao.entity;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {
    private String _id;
    private String _Name;
    private String _MSSV;
    private String _Khoa;
    private String _Class;
    private String _Image;
    private String _Email;
    private String _Pass;
    private int _category;

    public User() {
    }

    public User(String _Name, String _Email, String _Pass, int _category) {
        this._Name = _Name;
        this._Email = _Email;
        this._Pass = _Pass;
        this._category = _category;
    }

    public User(String _id, String _Name, String _MSSV, String _Khoa, String _Class, String _Image, String _Email, String _Pass, int _category) {
        this._id = _id;
        this._Name = _Name;
        this._MSSV = _MSSV;
        this._Khoa = _Khoa;
        this._Class = _Class;
        this._Image = _Image;
        this._Email = _Email;
        this._Pass = _Pass;
        this._category=_category;
    }

    @Override
    public String toString() {
        return "User{" +
                "_id='" + _id + '\'' +
                ", _Name='" + _Name + '\'' +
                ", _MSSV='" + _MSSV + '\'' +
                ", _Khoa='" + _Khoa + '\'' +
                ", _Class='" + _Class + '\'' +
                ", _Image='" + _Image + '\'' +
                ", _Email='" + _Email + '\'' +
                ", _Pass='" + _Pass + '\'' +
                ", _category='" + _category + '\'' +
                '}';
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_Name() {
        return _Name;
    }

    public void set_Name(String _Name) {
        this._Name = _Name;
    }

    public String get_MSSV() {
        return _MSSV;
    }

    public void set_MSSV(String _MSSV) {
        this._MSSV = _MSSV;
    }

    public String get_Khoa() {
        return _Khoa;
    }

    public void set_Khoa(String _Khoa) {
        this._Khoa = _Khoa;
    }

    public String get_Class() {
        return _Class;
    }

    public void set_Class(String _Class) {
        this._Class = _Class;
    }

    public String get_Image() {
        return _Image;
    }

    public void set_Image(String _Image) {
        this._Image = _Image;
    }

    public String get_Email() {
        return _Email;
    }

    public void set_Email(String _Email) {
        this._Email = _Email;
    }

    public String get_Pass() {
        return _Pass;
    }

    public void set_Pass(String _Pass) {
        this._Pass = _Pass;
    }

    public int get_category() {
        return _category;
    }

    public void set_category(int _category) {
        this._category = _category;
    }
}
