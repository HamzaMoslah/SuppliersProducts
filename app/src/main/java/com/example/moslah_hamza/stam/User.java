package com.example.moslah_hamza.stam;

/**
 * Created by Moslah_Hamza on 17/04/2017.
 */

public class User {
    //private variables
    int _id;
    String _name;
    String _email;
    String _pwd;

    public User() {
    }

    public User(String _name, String _email, String _pwd) {
        this._name = _name;
        this._email = _email;
        this._pwd = _pwd;
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

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_pwd() {
        return _pwd;
    }

    public void set_pwd(String _pwd) {
        this._pwd = _pwd;
    }
}
