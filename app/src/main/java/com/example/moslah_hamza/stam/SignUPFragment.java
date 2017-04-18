package com.example.moslah_hamza.stam;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Moslah_Hamza on 17/04/2017.
 */

public class SignUPFragment extends Fragment {
    EditText pwd, pwdConf, name, email;
    Button register;
    DataBaseHandler db;
    UserLocalStore userLocalStore;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.signup_fragment, container, false);
        db = new DataBaseHandler(getActivity());
        //shared preferences class'
        userLocalStore = new UserLocalStore(getActivity());
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pwd = (EditText) view.findViewById(R.id.etPassword);
        pwdConf = (EditText) view.findViewById(R.id.etPassword1);
        name = (EditText) view.findViewById(R.id.name);
        email = (EditText) view.findViewById(R.id.email);
        register = (Button) view.findViewById(R.id.tvRegisterLink);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pwdHash = pwd.getText().toString();
                if (pwdConf.getText().toString().length() < 4 || pwd.getText().toString().length() < 4 ||
                        name.getText().toString().length() < 4 || email.getText().toString().length() < 4 ||
                        !email.getText().toString().contains("@") || pwd.getText().toString() != pwdConf.getText().toString()) {
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Erreur")
                            .setMessage("Veuillez vérifier les champs saisis")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // do nothing
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                } else {
                    try {
                        pwdHash = SimpleSHA1.SHA1(pwdHash);
                        User user = new User(name.getText().toString(), email.getText().toString(), pwdHash);
                        db.addUser(user);
                        userLocalStore.storeUserDate(user);
                        userLocalStore.setUserLoggedIn(true);
                        final FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.frame, new SuppliersFragment(), "NewFragmentTag");
                        ft.commit();
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                Log.d("name : ", db.getAllUsers().get(0).get_name());
            }
        });
    }
}