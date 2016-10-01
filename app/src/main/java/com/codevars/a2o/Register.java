package com.codevars.a2o;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.codevars.a2o.Server.RegisterUserClass;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.prefs.PreferenceChangeEvent;

public class Register extends Fragment implements View.OnClickListener {


    private static final String REGISTER_URL = "http://a2o.esy.es/register.php";

    private EditText fullname;

    private EditText email;

    private EditText password;

    private Spinner bloodgroup;

    private Spinner convention;

    private Button registerbutton;


    public Register() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Declaring Fonts

        Typeface one = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Lato-Light.ttf");

        Typeface two = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Lato-Regular.ttf");


        // Inflating View

        View view = inflater.inflate(R.layout.fragment_register, container, false);


        // Declaring Variables

        fullname = (EditText) view.findViewById(R.id.fullname);

        email = (EditText) view.findViewById(R.id.email);

        password = (EditText) view.findViewById(R.id.password);

        bloodgroup = (Spinner) view.findViewById(R.id.bloodgroup);

        convention = (Spinner) view.findViewById(R.id.convention);

        registerbutton = (Button) view.findViewById(R.id.register);


        // Setting Fonts

        fullname.setTypeface(one);

        email.setTypeface(one);

        password.setTypeface(one);

        registerbutton.setTypeface(two);


        // Setting OnClickListeners

        registerbutton.setOnClickListener(this);


        return view;

    }



    public boolean online() {

        final ConnectivityManager internet = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        return internet.getActiveNetworkInfo() != null && internet.getActiveNetworkInfo().isConnected();

    }



    public void check() {

        if (online()) {

            emptycheck();

        }

        else {

            Toast.makeText(getContext(), "You Are Not Connected To The Internet!", Toast.LENGTH_SHORT).show();

        }

    }



    public void emptycheck() {

        if (fullname.getText().toString().trim().matches("")) {

            Toast.makeText(getContext(), "Please Enter Your Full Name!", Toast.LENGTH_SHORT).show();

            return;

        }

        if (email.getText().toString().trim().matches("")) {

            Toast.makeText(getContext(), "Please Enter Your Email!", Toast.LENGTH_SHORT).show();

            return;

        }

        if (password.getText().toString().trim().matches("")) {

            Toast.makeText(getContext(), "Please Enter Your Password!", Toast.LENGTH_SHORT).show();

            return;

        }

        if (bloodgroup.getSelectedItem().toString().matches("Select")) {

            Toast.makeText(getContext(), "Please Select A Blood Group!", Toast.LENGTH_SHORT).show();

            return;

        }

        if (convention.getSelectedItem().toString().matches("Select")) {

            Toast.makeText(getContext(), "Please Select A Convention!", Toast.LENGTH_SHORT).show();

            return;

        }

        else {

            initiate();

        }

    }



    public void initiate() {

        String fn = fullname.getText().toString().trim();

        String em = email.getText().toString().trim();

        String pass = password.getText().toString().trim();

        String bg = bloodgroup.getSelectedItem().toString().trim();

        String cv = convention.getSelectedItem().toString().trim();

        String grp = bg + cv;

        register(fn, em, pass, grp);

    }



    public void register(final String fn, final String em, final  String pass, final  String grp) {

        class RegisterClass extends AsyncTask<String, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                loading = new ProgressDialog(getContext(), R.style.LoaderTheme);
                loading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
                loading.setCancelable(false);
                loading.show();

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();

                if (s.equalsIgnoreCase("Successfully Registered!")) {

                    return;

                }

                if (s.equalsIgnoreCase("")) {

                    Toast.makeText(getContext(), "Try Again!", Toast.LENGTH_SHORT).show();

                }

                else {

                    Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            protected String doInBackground(String... params) {

                HashMap<String, String> credentials = new HashMap<>();

                credentials.put("fullname", params[0]);

                credentials.put("email", params[1]);

                credentials.put("password", params[2]);

                credentials.put("group", params[3]);

                RegisterUserClass ruc = new RegisterUserClass();

                String result = ruc.sendPostRequest(REGISTER_URL, credentials);

                return result;

            }

        }

        RegisterClass attempt = new RegisterClass();

        attempt.execute(fn, em, pass, grp);

    }



    @Override
    public void onClick(View view) {

        if (view == registerbutton)  {

            check();

        }

    }



}
