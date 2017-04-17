package com.example.moslah_hamza.stam;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Moslah_Hamza on 17/04/2017.
 */

public class SignUPFragment extends Fragment {
    EditText pwd, pwdConf, name, email;
    Button register;
    DataBaseHandler db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.signup_fragment, container, false);
        db = new DataBaseHandler(getActivity());
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
                User user = new User(name.getText().toString(), email.getText().toString(), pwd.getText().toString());
                db.addUser(user);
                Log.d("name : ",db.getAllUsers().get(0).get_name());
            }
        });
    }
}