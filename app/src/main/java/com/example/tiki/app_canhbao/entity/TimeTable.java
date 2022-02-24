package com.example.tiki.app_canhbao.entity;

public class TimeTable {
    private int _id;
    private String _nameSubject;
    private String _startTimeLearning;
    private String _dayLearning;
    private String _dayStart;
    private String _dayEnd;
    private String _note;
    private String _nameClass;
    private String _nameGV;

    public TimeTable() {
    }

    public TimeTable(String _nameSubject, String _startTimeLearning, String _dayLearning, String _dayStart, String _dayEnd, String _note, String _nameClass, String _nameGV) {
        this._nameSubject = _nameSubject;
        this._startTimeLearning = _startTimeLearning;
        this._dayLearning = _dayLearning;
        this._dayStart = _dayStart;
        this._dayEnd = _dayEnd;
        this._note = _note;
        this._nameClass = _nameClass;
        this._nameGV = _nameGV;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_nameSubject() {
        return _nameSubject;
    }

    public void set_nameSubject(String _nameSubject) {
        this._nameSubject = _nameSubject;
    }

    public String get_startTimeLearning() {
        return _startTimeLearning;
    }

    public void set_startTimeLearning(String _startTimeLearning) {
        this._startTimeLearning = _startTimeLearning;
    }

    public String get_dayLearning() {
        return _dayLearning;
    }

    public void set_dayLearning(String _dayLearning) {
        this._dayLearning = _dayLearning;
    }

    public String get_dayStart() {
        return _dayStart;
    }

    public void set_dayStart(String _dayStart) {
        this._dayStart = _dayStart;
    }

    public String get_dayEnd() {
        return _dayEnd;
    }

    public void set_dayEnd(String _dayEnd) {
        this._dayEnd = _dayEnd;
    }

    public String get_note() {
        return _note;
    }

    public void set_note(String _note) {
        this._note = _note;
    }

    public String get_nameClass() {
        return _nameClass;
    }

    public void set_nameClass(String _nameClass) {
        this._nameClass = _nameClass;
    }

    public String get_nameGV() {
        return _nameGV;
    }

    public void set_nameGV(String _nameGV) {
        this._nameGV = _nameGV;
    }
}
