package com.example.moslah_hamza.stam;

/**
 * Created by Moslah_Hamza on 20/04/2017.
 */

public class Supplier {
    //private variables
    int _id;
    String _name;

    public Supplier() {
    }

    public Supplier(String _name) {
        this._name = _name;
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
