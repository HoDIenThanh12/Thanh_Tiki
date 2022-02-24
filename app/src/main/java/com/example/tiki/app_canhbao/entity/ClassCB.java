package com.example.tiki.app_canhbao.entity;

public class ClassCB {
    private int _id;
    private String _nameClassCB;
    private String _noteClassCB;

    public ClassCB(String _nameClassCB, String _noteClassCB) {
        this._nameClassCB = _nameClassCB;
        this._noteClassCB = _noteClassCB;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_nameClassCB() {
        return _nameClassCB;
    }

    public void set_nameClassCB(String _nameClassCB) {
        this._nameClassCB = _nameClassCB;
    }

    public String get_noteClassCB() {
        return _noteClassCB;
    }

    public void set_noteClassCB(String _noteClassCB) {
        this._noteClassCB = _noteClassCB;
    }
}
