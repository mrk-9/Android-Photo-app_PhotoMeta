package com.example.red.photometa;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.red.photometa.APIModule.Constants;
import com.example.red.photometa.APIModule.PhotoMetaAPI;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;


public class SignupActivity extends Activity implements View.OnClickListener,PhotoMetaAPI.ApiSyncHandler {

    Button signup_btn;
    RelativeLayout back_rel;
    EditText username_et_sign,email_et_sign,age_et_sign,gender_et_sign,pass_et_sign,confirm_et_pass,postcode_et_sign;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        initView();
    }
    private void initView()
    {
        back_rel = (RelativeLayout)findViewById(R.id.back_rel);
        signup_btn = (Button)findViewById(R.id.signup_btn);
        username_et_sign = (EditText)findViewById(R.id.username_et_signup);
        email_et_sign = (EditText)findViewById(R.id.email_et_signup);
        pass_et_sign = (EditText)findViewById(R.id.password_et_signup);
        confirm_et_pass = (EditText) findViewById(R.id. confirm_et_signup);
        age_et_sign = (EditText) findViewById(R.id. age_et_signup);
        gender_et_sign = (EditText) findViewById(R.id. gender_et_signup);
        postcode_et_sign = (EditText) findViewById(R.id. postcode_et_signup);
        back_rel.setOnClickListener(this);
        signup_btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.back_rel:
                onBackPressed();
                break;
            case R.id.signup_btn:
                if(username_et_sign.getText().length() <= 0)
                   showFailedAlert("Please enter valid username");
                else if(email_et_sign.getText().length() <= 0)
                    showFailedAlert("Please enter valid email");
                else if(age_et_sign.getText().length() <= 0)
                    showFailedAlert("Please enter valid age");
                else if(gender_et_sign.getText().length() <= 0)
                    showFailedAlert("Please enter valid gender");
                else if(pass_et_sign.getText().length() <=0)
                    showFailedAlert("Please enter Password");
                else if(confirm_et_pass.getText().length() <= 0)
                    showFailedAlert("Please enter Confirm Password");
                else if(!pass_et_sign.getText().toString().equals(confirm_et_pass.getText().toString()))
                    showFailedAlert("Reenter Confirm Password");
                else if(postcode_et_sign.getText().length() <= 0)
                    showFailedAlert("Please enter valid postcode");
                else
                {
                    progress = ProgressDialog.show(SignupActivity.this, "Loading", "Please wait",true);
                    String url = Constants.HOST_URL + Constants.SIGNUP_URL;
                    RequestParams params = new RequestParams();
                    params.put("username", username_et_sign.getText().toString());
                    params.put("email", email_et_sign.getText().toString());
                    params.put("password",pass_et_sign.getText().toString());
                    params.put("age",age_et_sign.getText().toString());
                    params.put("gender",gender_et_sign.getText().toString());
                    params.put("postcode",postcode_et_sign.getText().toString());
                    PhotoMetaAPI api = new PhotoMetaAPI(url, params, SignupActivity.this);
                    api.syncObject();
                }
                break;
        }
    }
    private void showSuccessAlert(String message)  {
        AlertDialog alertDialog = new AlertDialog.Builder(SignupActivity.this).create();
        alertDialog.setTitle("Help");
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
    private void showFailedAlert(String message)  {
        AlertDialog alertDialog = new AlertDialog.Builder(SignupActivity.this).create();
        alertDialog.setTitle("Error");
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
    //Disable back button
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }
    @Override
    public void success(String response)
    {
        try
        {
            Log.d("Server Response",response);
            JSONObject jsonObject = new JSONObject(response);
            if(jsonObject.getString("status").equals("true"))
            {
                    progress.dismiss();
                    showSuccessAlert("Signup successfully");
                    Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
                    startActivity(intent);
            }else
            {
                    progress.dismiss();
                    if (jsonObject.getString("error").equals("existing user name"))
                        showFailedAlert("Username already exist");
                    else if (jsonObject.getString("error").equals("existing email address"))
                        showFailedAlert("Email already exist");
                    else if(jsonObject.getString("error").equals("existing account"))
                        showFailedAlert("User already exist");
                    else if (jsonObject.getString("error").equals("failed insert user info"))
                        showFailedAlert("Invalid user information");
            }
        }catch (JSONException e)
        {
            progress.dismiss();
            e.printStackTrace();
        }
    }
    @Override
    public void failed(String response, Throwable throwable)
    {
        showFailedAlert("Please check network state.");
        progress.dismiss();
    }
}
