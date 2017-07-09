package com.example.red.photometa;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.red.photometa.APIModule.Constants;
import com.example.red.photometa.APIModule.PhotoMetaAPI;
import com.example.red.photometa.Common.Application;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by RED on 9/27/2016.
 */

public class LoginActivity extends Activity implements View.OnClickListener,PhotoMetaAPI.ApiSyncHandler {

    Button login_btn;
    TextView newmember_txt;
    EditText email_et,password_et;
    ProgressDialog progress;
    Application instance;
    public static int OVERLAY_PERMISSION_REQ_CODE = 1234;
    public static int REQUEST_CALL_PHONE_CODE = 1235;

    //android OS 6.0 permerssion(SYSTEMALERTWINDOW permission)
    public void someMethod() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initView();
        login_btn.setOnClickListener(this);
        newmember_txt.setOnClickListener(this);

        //Call permission automatically
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if (ContextCompat.checkSelfPermission(LoginActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            {
                if (ActivityCompat.shouldShowRequestPermissionRationale(LoginActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE))
                {

                }else
                {
                    ActivityCompat.requestPermissions(LoginActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_CALL_PHONE_CODE);
                }
            }
        }
    }

    private void initView()
    {
        login_btn = (Button) findViewById(R.id.login_btn);
        newmember_txt = (TextView) findViewById(R.id.newmember_txt);
        email_et = (EditText) findViewById(R.id.email_et);
        password_et = (EditText) findViewById(R.id.password_et);
        instance = Application.getSharedInstance();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.login_btn:

                if(email_et.getText().length() <= 0)
                    showFailedAlert("Please enter valid email");
                else if(password_et.getText().length() <= 0)
                    showFailedAlert("Please enter Password");
                else
                {
                    progress = ProgressDialog.show(LoginActivity.this, "Loading", "Please wait",true);
                    String url = Constants.HOST_URL + Constants.LOGIN_URL;
                    RequestParams params = new RequestParams();
                    params.put("email", email_et.getText().toString());
                    params.put("password", password_et.getText().toString());
                    PhotoMetaAPI api = new PhotoMetaAPI(url, params, LoginActivity.this);
                    api.syncObject();
                }
                break;
            case R.id.newmember_txt:
                Intent intent1 = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(intent1);
                break;
        }
    }
    private void showSuccessAlert(String message)  {
        AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
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
        AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
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
                instance.username = email_et.getText().toString();
                Log.d("Username",instance.username);
                Intent intent = new Intent(LoginActivity.this,UploadActivity.class);
                startActivity(intent);
            }
            else
            {
                progress.dismiss();
                showFailedAlert("Incorrect email and password");
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
    //Disable back button
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }
}
