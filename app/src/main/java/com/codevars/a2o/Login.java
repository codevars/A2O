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
import android.widget.Toast;
import com.codevars.a2o.Server.RegisterUserClass;
import java.util.HashMap;

public class Login extends Fragment implements View.OnClickListener {


    private static final String LOGIN_URL = "http://a2o.esy.es/login.php";

    private EditText mobile;

    private EditText password;

    private Button loginbutton;


    public Login() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        // Declaring Fonts

        Typeface one = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Lato-Regular.ttf");

        Typeface two = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Lato-Light.ttf");


        // Inflating View

        View view =  inflater.inflate(R.layout.fragment_login, container, false);


        // Declaring Datatypes And Variables

        mobile = (EditText) view.findViewById(R.id.mobile);

        password = (EditText) view.findViewById(R.id.password);

        loginbutton = (Button) view.findViewById(R.id.loginbutton);


        // Setting Fonts

        mobile.setTypeface(two);

        password.setTypeface(two);

        loginbutton.setTypeface(one);


        // Setting OnClickListeners

        loginbutton.setOnClickListener(this);


        return view;


    }


    private boolean online () {

        final ConnectivityManager internet = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        return internet.getActiveNetworkInfo() != null && internet.getActiveNetworkInfo().isConnected();

    }



    private void check() {

        if (online()) {

            emptycheck();

        }

        else {

            Toast.makeText(getContext(), "You Are Not Connected To Internet!", Toast.LENGTH_SHORT).show();

        }

    }



    private void emptycheck() {

        if (mobile.getText().toString().trim().matches("")) {

            Toast.makeText(getContext(), "Please Enter Your Mobile Number!", Toast.LENGTH_SHORT).show();

            return;

        }

        if (password.getText().toString().trim().matches("")) {

            Toast.makeText(getContext(), "Please Enter Your Password!", Toast.LENGTH_SHORT).show();

            return;

        }

        else {

            initiate();

        }

    }



    private void initiate() {

        String mob = mobile.getText().toString().trim();

        String pass = password.getText().toString().trim();

        login(mob, pass);

    }



    private void login(final String mob, final String pass){

        class UserLoginClass extends AsyncTask<String, Void, String> {

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

                if (s.equalsIgnoreCase("Successfully Logged In!")) {

                    Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();

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

                credentials.put("mobile", params[0]);

                credentials.put("password", params[1]);

                RegisterUserClass ruc = new RegisterUserClass();

                String result = ruc.sendPostRequest(LOGIN_URL, credentials);

                return result;

            }

        }

        UserLoginClass attempt = new UserLoginClass();

        attempt.execute(mob, pass);

    }



    @Override
    public void onClick(View view) {

        if (view == loginbutton) {

            check();

        }

    }




}
