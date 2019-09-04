package com.ideatech.org.online_noticeboard;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    EditText mytext;

    @Bind(R.id.input_email)
    EditText _emailText;
    @Bind(R.id.input_password)
    EditText _passwordText;
    @Bind(R.id.btn_login)
    Button _loginButton;
    @Bind(R.id.link_signup)
    TextView _signupLink;
    // SessionManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // **************Do it code here*****************
       /* if(manager.getISLOGIN()){
            // startActivity(new Intent(LoginActivity.this,AdminPortal.class));
        }*/
        ButterKnife.bind(this);
        ButterKnife.bind(this);
        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }
    public void login() {
        Log.d(TAG, "Login");
        if (!validate()) {
            onLoginFailed();
            return;
        }
        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        // TODO: Implement your own authentication logic here.
        // isAdminLogin(email, password
        // Do it code here
       /*if (isUserLogin(email, password)) {
            Toast.makeText(this, "User Login", Toast.LENGTH_SHORT).show();
            manager.setLogin(true);
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            //return;
            }
            */

        // ********** TODO code here**********
        /* if(isAdminLogin(email, password)){
            Toast.makeText(this, "Admin Login", Toast.LENGTH_SHORT).show();
            // startActivity(new Intent(LoginActivity.this, AdminPortal.class));
            manager.setLogin(true);
        }
        else
            Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            */
        //saveUser();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {

                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                          progressDialog.dismiss();
                    }
                }, 3000);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }
    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);

        // finish();
    }
    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }
    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }
        return valid;
    }
    //***********Do it code here***************
  /*  public boolean isAdminLogin(String username, String password) {
        boolean value = false;

        }
        return value;
    }*/

   /* public boolean isUserLogin(String username, String password) {
        DbHelper helper = new DbHelper(this);
        boolean value = false;
        String inputvalues="-"+username+"-"+password;
        Cursor cur = helper.selectEmployeeUserName();
        Log.w("@@@@@@LInput-Values", inputvalues);
        if (cur.getCount() != 0) {
            cur.moveToFirst();
            do {
                String row_values = "";
                for (int i = 0; i < cur.getColumnCount(); i++) {
                    row_values = row_values+"-"+cur.getString(i);
                }
                if (row_values.equals(inputvalues)) {
                    value = true;
                }
                Log.w("@@@@@@LOG_TAG_HERE", row_values);
                // employeelist.add(row_values);
            } while (cur.moveToNext());
        } else {
            Log.w("######LOG_TAG_HERE", "No values");
        }
        return value;
    }*/

    //************** Do it code here*************************
    public void saveUser(){
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        //***************    Do it  code here****************
        /*
        DbHelper helper=new DbHelper(this);
        helper.inserRecord(email,password);
        */
    }








//
//    @Override
//    public void onClick(View v) {
//        Intent intent=new Intent(this,SignUp.class);
//        startActivity(intent);
//
//        }
}
