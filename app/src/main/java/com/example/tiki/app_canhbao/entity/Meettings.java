package com.example.tiki.app_canhbao.entity;

import java.io.Serializable;

public class Meettings implements Serializable {
    private String _id;
    private String _nameMeet;
    private String _timeStart;
    private String _dayStart;

    public Meettings() {
    }

    public Meettings(String _nameMeet, String _timeStart, String _dayStart) {
        this._nameMeet = _nameMeet;
        this._timeStart = _timeStart;
        this._dayStart = _dayStart;
    }

    public Meettings(String _id, String _nameMeet, String _timeStart, String _dayStart) {
        this._id = _id;
        this._nameMeet = _nameMeet;
        this._timeStart = _timeStart;
        this._dayStart = _dayStart;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_nameMeet() {
        return _nameMeet;
    }

    public void set_nameMeet(String _nameMeet) {
        this._nameMeet = _nameMeet;
    }

    public String get_timeStart() {
        return _timeStart;
    }

    public void set_timeStart(String _timeStart) {
        this._timeStart = _timeStart;
    }

    public String get_dayStart() {
        return _dayStart;
    }

    public void set_dayStart(String _dayStart) {
        this._dayStart = _dayStart;
    }
}
