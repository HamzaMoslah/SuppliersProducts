package com.example.moslah_hamza.stam;

/**
 * Created by Moslah_Hamza on 20/04/2017.
 */

public class Product {
    //private variables
    int _id;
    String _name;
    double _PU;
    int qte;

    public Product() {
    }

    public Product(String _name, double _PU) {
        this._name = _name;
        this._PU = _PU;
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

    public double get_PU() {
        return _PU;
    }

    public void set_PU(double _PU) {
        this._PU = _PU;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }
}
