package com.example.tiki.ggmap.roomdatabase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ggMap")
public class ggMapEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private double _longitude;

    private double _latitude;
    //@ColumnInfo(name = "country")
    private String _country;

    //@ColumnInfo(name = "adress")
    private String _address;

    private String _time;

    public ggMapEntity(double _longitude, double _latitude, String _country, String _address) {
        this._longitude = _longitude;
        this._latitude = _latitude;
        this._country = _country;
        this._address = _address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double get_longitude() {
        return _longitude;
    }

    public void set_longitude(double _longitude) {
        this._longitude = _longitude;
    }

    public double get_latitude() {
        return _latitude;
    }

    public void set_latitude(double _latitude) {
        this._latitude = _latitude;
    }

    public String get_country() {
        return _country;
    }

    public void set_country(String _country) {
        this._country = _country;
    }

    public String get_address() {
        return _address;
    }

    public void set_address(String _address) {
        this._address = _address;
    }

    public String get_time() {
        return _time;
    }

    public void set_time(String _time) {
        this._time = _time;
    }
}
