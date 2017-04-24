package com.example.moslah_hamza.stam;

/**
 * Created by Moslah_Hamza on 20/04/2017.
 */

public class Product {
    //private variables
    private int _id;
    private String _name;
    private double _PU;
    private int qte;
    private int user, sup;

    public Product() {
    }

    public Product(String _name, double _PU) {
        this._name = _name;
        this._PU = _PU;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getSup() {
        return sup;
    }

    public void setSup(int sup) {
        this.sup = sup;
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
