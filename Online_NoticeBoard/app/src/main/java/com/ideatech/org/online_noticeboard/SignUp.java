package com.ideatech.org.online_noticeboard;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SignUp extends AppCompatActivity {

    private static final String TAG = "SignUp";
    private static final int REQUEST_SIGNUP = 0;
    EditText mytexr;
    @Bind(R.id.input_email2)
    EditText _emailText2;
    @Bind(R.id.input_password2)
    EditText _passwordText2;
    @Bind(R.id.btn_signup)
    Button _signupButton;
    @Bind(R.id.re_input_password)
    EditText _reinputPasswordText;
    @Bind(R.id.text_view2)
    TextView _textview;
    private ProgressDialog pDialog;
    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        ButterKnife.bind(this);
        session=new SessionManager(this);
        pDialog=new ProgressDialog(this);
        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //createAccount();
             //   String name = inputFullName.getText().toString().trim();
                if (Utils.isConnected(SignUp.this)) {
                String email = _emailText2.getText().toString().trim();
                String password = _passwordText2.getText().toString().trim();
                if (validate()) {
                    registerUser("onbUser", email, password);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please enter your details!", Toast.LENGTH_LONG)
                            .show();
                }
                } else {
                    Utils.showSettingsAlert("DATA", SignUp.this);

                }
            }
        });



        _textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      //  super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK){
                this.finish();
            }
        }
    }

    //******************Create Account sucessful method******************
    public void createAccount()
    {
        Log.d(TAG, "Created");
        if(!validate())
        {
            onCreateAccountFailed();
            return;
        }
        _signupButton.setEnabled(false);
//        final ProgressDialog progressDialog=new ProgressDialog(SignUp.this);
//        progressDialog.setMessage("Account going to create");
//        progressDialog.show();
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onCreateAccountSucessful();
                // progressDialog.dismiss();
            }
        }, 3000);


    }

    //**********Create failed****************
    public void onCreateAccountFailed()
    {
        Toast.makeText(getApplicationContext(), "Created account failed", Toast.LENGTH_SHORT).show();
        _signupButton.setEnabled(true);
    }
    public void onCreateAccountSucessful()
    {
//registerUser();
        _signupButton.setEnabled(true);
    }
    //********************Back pressed method*************
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
    }

        //****************** Validate Code8***************
    public boolean validate()
    {
        //**********TODO code here for save account data.******************

        boolean valid=true;
        String email=_emailText2.getText().toString();
        String password=_passwordText2.getText().toString();
        String repassword=_reinputPasswordText.getText().toString();
        if(email.isEmpty()||!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText2.setError("Enter a valid email address");
            valid=false;
        }   else{
            _emailText2.setError(null);
        }
        if(password.isEmpty()||password.length()<4||password.length()>10)
        {
            _passwordText2.setError("Password character between 4 and 10");
            valid=false;
        }   else{
            _passwordText2.setError(null);
        }
        if(repassword.isEmpty()||(!(repassword.equals(password)))){
            _reinputPasswordText.setError("Please! again enter passwor!");
            _passwordText2.setText("");
            _reinputPasswordText.setText("");
            valid=false;
        } else{
            _reinputPasswordText.setError(null);
        }
        return valid;

    }
    private void registerUser(final String name, final String email,
                              final String password) {
        // Tag used to cancel the request
        String tag_string_req = "req_register";

        pDialog.setMessage("Registering ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.getUrlSignUp(email,password), new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {
                    if(response.equals("True")) {
//

                        Toast.makeText(getApplicationContext(), "User successfully registered. Try login now!", Toast.LENGTH_LONG).show();

                        // Launch login activity
                        Intent intent = new Intent(
                                SignUp.this,
                                LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }else if(response.equals("false")){
                        Toast.makeText(getApplicationContext(), "User Already Registered!", Toast.LENGTH_LONG).show();
                    }else
                        Toast.makeText(getApplicationContext(), "Problem in Singning Up!", Toast.LENGTH_LONG).show();
//                    } else {
//
//                        // Error occurred in registration. Get the error
//                        // message
//                        String errorMsg = jObj.getString("error_msg");
//                        Toast.makeText(getApplicationContext(),
//                                errorMsg, Toast.LENGTH_LONG).show();
//                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Registration Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String,String>();
                params.put("name", name);
                params.put("email", email);
                params.put("password", password);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addTORequestQueue(strReq, tag_string_req);
    }
    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
