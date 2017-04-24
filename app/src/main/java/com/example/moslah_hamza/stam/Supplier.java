package com.example.moslah_hamza.stam;

/**
 * Created by Moslah_Hamza on 20/04/2017.
 */

public class Supplier {
    //private variables
    private int _id;
    private int usr_id;
    private String _name;

    public Supplier() {
    }

    public Supplier(String _name, int usr_id) {
        this._name = _name;
        this.usr_id = usr_id;
    }

    public int getUsr_id() {
        return usr_id;
    }

    public void setUsr_id(int usr_id) {
        this.usr_id = usr_id;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }
}
