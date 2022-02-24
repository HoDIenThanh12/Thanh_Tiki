package com.example.tiki.app_canhbao.entity;

public class Schedule {
    private String _id;
    private String _subJect;
    private String _lesson;
    private String _timeStart;
    private String _timeEnd;
    private String _dayLearn;

    public Schedule() {
    }

    public Schedule(String _subJect, String _lesson, String _timeStart, String _timeEnd, String _dayLearn) {
        this._subJect = _subJect;
        this._lesson = _lesson;
        this._timeStart = _timeStart;
        this._timeEnd = _timeEnd;
        this._dayLearn = _dayLearn;
    }

    public Schedule(String _id, String _subJect, String _lesson, String _timeStart, String _timeEnd, String _dayLearn) {
        this._id = _id;
        this._subJect = _subJect;
        this._lesson = _lesson;
        this._timeStart = _timeStart;
        this._timeEnd = _timeEnd;
        this._dayLearn = _dayLearn;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_subJect() {
        return _subJect;
    }

    public void set_subJect(String _subJect) {
        this._subJect = _subJect;
    }

    public String get_lesson() {
        return _lesson;
    }

    public void set_lesson(String _lesson) {
        this._lesson = _lesson;
    }

    public String get_timeStart() {
        return _timeStart;
    }

    public void set_timeStart(String _timeStart) {
        this._timeStart = _timeStart;
    }

    public String get_timeEnd() {
        return _timeEnd;
    }

    public void set_timeEnd(String _timeEnd) {
        this._timeEnd = _timeEnd;
    }

    public String get_dayLearn() {
        return _dayLearn;
    }

    public void set_dayLearn(String _dayLearn) {
        this._dayLearn = _dayLearn;
    }
}
