package com.example.moslah_hamza.stam;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
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

public class LoginFragment extends Fragment {
    EditText pwd, email;
    Button log;
    DataBaseHandler db;
    UserLocalStore userLocalStore;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.login_fragment, container, false);
        db = new DataBaseHandler(getActivity());
        //shared preferences class'
        userLocalStore = new UserLocalStore(getActivity());
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        log = (Button) view.findViewById(R.id.bLogin);
        pwd = (EditText) view.findViewById(R.id.etPassword);
        email = (EditText) view.findViewById(R.id.email);
        Button register = (Button) view.findViewById(R.id.tvRegisterLink);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.frame, new SignUPFragment(), "NewFragmentTag");
                ft.commit();
            }
        });

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pwdHash = pwd.getText().toString();
                if (pwd.getText().toString().length() < 4 || email.getText().toString().length() < 4
                        || !email.getText().toString().contains("@")) {
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
                        User user = new User();
                        user = db.getUser(email.getText().toString());
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
            }
        });
    }
}
