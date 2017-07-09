package com.example.red.photometa;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.red.photometa.APIModule.Constants;
import com.example.red.photometa.APIModule.PhotoMetaAPI;
import com.example.red.photometa.Common.Application;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by RED on 9/27/2016.
 */

public class RatingtwoActivity extends Activity implements View.OnClickListener,PhotoMetaAPI.ApiSyncHandler {

    ImageView previous_ratingtwo,lefttwo_img,midtwo_img,righttwo_img,left1_img,left2_img,left3_img,left4_img,mid1_img,mid2_img,mid3_img,mid4_img;
    RelativeLayout lefttwo_rel,midtwo_rel,righttwo_rel,left1_rel,left2_rel,left3_rel,left4_rel,mid1_rel,mid2_rel,mid3_rel,mid4_rel;
    Button submit_btn;
    RelativeLayout alert_reltwo;
    TextView alertOKTwo_btn,alertCancelTwo_btn;
    int flag = 0;
    Application instance;
    ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ratingtwo);
        initView();
    }

    private void initView()
    {
        previous_ratingtwo = (ImageView)findViewById(R.id.previous_ratingtwo);
        lefttwo_img = (ImageView)findViewById(R.id.lefttwo_img);
        midtwo_img = (ImageView)findViewById(R.id.midtwo_img);
        righttwo_img = (ImageView)findViewById(R.id.righttwo_img);
        lefttwo_rel = (RelativeLayout)findViewById(R.id.lefttwo_rel);
        midtwo_rel = (RelativeLayout)findViewById(R.id.midtwo_rel);
        righttwo_rel = (RelativeLayout)findViewById(R.id.righttwo_rel);
        left1_rel = (RelativeLayout) findViewById(R.id.left1_rel);
        left2_rel = (RelativeLayout) findViewById(R.id.left2_rel);
        left3_rel = (RelativeLayout) findViewById(R.id.left3_rel);
        left4_rel = (RelativeLayout) findViewById(R.id.left4_rel);
        mid1_rel = (RelativeLayout) findViewById(R.id.mid1_rel);
        mid2_rel = (RelativeLayout) findViewById(R.id.mid2_rel);
        mid3_rel = (RelativeLayout) findViewById(R.id.mid3_rel);
        mid4_rel = (RelativeLayout) findViewById(R.id.mid4_rel);
        left1_img = (ImageView)findViewById(R.id.left1_img);
        left2_img = (ImageView)findViewById(R.id.left2_img);
        left3_img = (ImageView)findViewById(R.id.left3_img);
        left4_img = (ImageView)findViewById(R.id.left4_img);
        mid1_img = (ImageView) findViewById(R.id.mid1_img);
        mid2_img = (ImageView) findViewById(R.id.mid2_img);
        mid3_img = (ImageView) findViewById(R.id.mid3_img);
        mid4_img = (ImageView) findViewById(R.id.mid4_img);
        submit_btn = (Button)findViewById(R.id.submit_btn);
        alert_reltwo = (RelativeLayout) findViewById(R.id.alert_ratingtwo);
        alertOKTwo_btn = (TextView)findViewById(R.id.verifyOK_btn_two);
        alertCancelTwo_btn = (TextView)findViewById(R.id.verifyCan_btn_two);

        midtwo_img.setVisibility(View.GONE);
        righttwo_img.setVisibility(View.GONE);
        submit_btn.setVisibility(View.GONE);
        alert_reltwo.setVisibility(View.GONE);
        left1_img.setVisibility(View.GONE);
        left2_img.setVisibility(View.GONE);
        left3_img.setVisibility(View.GONE);
        left4_img.setVisibility(View.GONE);
        mid1_img.setVisibility(View.GONE);
        mid2_img.setVisibility(View.GONE);
        mid3_img.setVisibility(View.GONE);
        mid4_img.setVisibility(View.GONE);
        instance = Application.getSharedInstance();

        previous_ratingtwo.setOnClickListener(this);
        lefttwo_rel.setOnClickListener(this);
        midtwo_rel.setOnClickListener(this);
        righttwo_rel.setOnClickListener(this);
        left1_rel.setOnClickListener(this);
        left2_rel.setOnClickListener(this);
        left3_rel.setOnClickListener(this);
        left4_rel.setOnClickListener(this);
        mid1_rel.setOnClickListener(this);
        mid2_rel.setOnClickListener(this);
        mid3_rel.setOnClickListener(this);
        mid4_rel.setOnClickListener(this);
        submit_btn.setOnClickListener(this);
        alertOKTwo_btn.setOnClickListener(this);
        alertCancelTwo_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.previous_ratingtwo:
                onBackPressed();
                break;
            case R.id.lefttwo_rel:
                lefttwo_img.setVisibility(View.VISIBLE);
                midtwo_img.setVisibility(View.GONE);
                righttwo_img.setVisibility(View.GONE);
                left1_img.setVisibility(View.GONE);
                left2_img.setVisibility(View.GONE);
                left3_img.setVisibility(View.GONE);
                left4_img.setVisibility(View.GONE);
                mid1_img.setVisibility(View.GONE);
                mid2_img.setVisibility(View.GONE);
                mid3_img.setVisibility(View.GONE);
                mid4_img.setVisibility(View.GONE);
                submit_btn.setVisibility(View.VISIBLE);
                flag = 1;
                break;
            case R.id.midtwo_rel:
                lefttwo_img.setVisibility(View.GONE);
                midtwo_img.setVisibility(View.VISIBLE);
                righttwo_img.setVisibility(View.GONE);
                left1_img.setVisibility(View.GONE);
                left2_img.setVisibility(View.GONE);
                left3_img.setVisibility(View.GONE);
                left4_img.setVisibility(View.GONE);
                mid1_img.setVisibility(View.GONE);
                mid2_img.setVisibility(View.GONE);
                mid3_img.setVisibility(View.GONE);
                mid4_img.setVisibility(View.GONE);
                submit_btn.setVisibility(View.VISIBLE);
                flag = 2;
                break;
            case R.id.righttwo_rel:
                lefttwo_img.setVisibility(View.GONE);
                midtwo_img.setVisibility(View.GONE);
                righttwo_img.setVisibility(View.VISIBLE);
                left1_img.setVisibility(View.GONE);
                left2_img.setVisibility(View.GONE);
                left3_img.setVisibility(View.GONE);
                left4_img.setVisibility(View.GONE);
                mid1_img.setVisibility(View.GONE);
                mid2_img.setVisibility(View.GONE);
                mid3_img.setVisibility(View.GONE);
                mid4_img.setVisibility(View.GONE);
                submit_btn.setVisibility(View.VISIBLE);
                flag = 3;
                break;
            case R.id.left1_rel:
                lefttwo_img.setVisibility(View.GONE);
                midtwo_img.setVisibility(View.GONE);
                righttwo_img.setVisibility(View.GONE);
                left1_img.setVisibility(View.VISIBLE);
                left2_img.setVisibility(View.GONE);
                left3_img.setVisibility(View.GONE);
                left4_img.setVisibility(View.GONE);
                mid1_img.setVisibility(View.GONE);
                mid2_img.setVisibility(View.GONE);
                mid3_img.setVisibility(View.GONE);
                mid4_img.setVisibility(View.GONE);
                submit_btn.setVisibility(View.GONE);
                break;
            case R.id.left2_rel:
                lefttwo_img.setVisibility(View.GONE);
                midtwo_img.setVisibility(View.GONE);
                righttwo_img.setVisibility(View.GONE);
                left1_img.setVisibility(View.GONE);
                left2_img.setVisibility(View.VISIBLE);
                left3_img.setVisibility(View.GONE);
                left4_img.setVisibility(View.GONE);
                mid1_img.setVisibility(View.GONE);
                mid2_img.setVisibility(View.GONE);
                mid3_img.setVisibility(View.GONE);
                mid4_img.setVisibility(View.GONE);
                submit_btn.setVisibility(View.GONE);
                break;
            case R.id.left3_rel:
                lefttwo_img.setVisibility(View.GONE);
                midtwo_img.setVisibility(View.GONE);
                righttwo_img.setVisibility(View.GONE);
                left1_img.setVisibility(View.GONE);
                left2_img.setVisibility(View.GONE);
                left3_img.setVisibility(View.VISIBLE);
                left4_img.setVisibility(View.GONE);
                mid1_img.setVisibility(View.GONE);
                mid2_img.setVisibility(View.GONE);
                mid3_img.setVisibility(View.GONE);
                mid4_img.setVisibility(View.GONE);
                submit_btn.setVisibility(View.GONE);
                break;
            case R.id.left4_rel:
                lefttwo_img.setVisibility(View.GONE);
                midtwo_img.setVisibility(View.GONE);
                righttwo_img.setVisibility(View.GONE);
                left1_img.setVisibility(View.GONE);
                left2_img.setVisibility(View.GONE);
                left3_img.setVisibility(View.GONE);
                left4_img.setVisibility(View.VISIBLE);
                mid1_img.setVisibility(View.GONE);
                mid2_img.setVisibility(View.GONE);
                mid3_img.setVisibility(View.GONE);
                mid4_img.setVisibility(View.GONE);
                submit_btn.setVisibility(View.GONE);
                break;
            case R.id.mid1_rel:
                lefttwo_img.setVisibility(View.GONE);
                midtwo_img.setVisibility(View.GONE);
                righttwo_img.setVisibility(View.GONE);
                left1_img.setVisibility(View.GONE);
                left2_img.setVisibility(View.GONE);
                left3_img.setVisibility(View.GONE);
                left4_img.setVisibility(View.GONE);
                mid1_img.setVisibility(View.VISIBLE);
                mid2_img.setVisibility(View.GONE);
                mid3_img.setVisibility(View.GONE);
                mid4_img.setVisibility(View.GONE);
                submit_btn.setVisibility(View.GONE);
                break;
            case R.id.mid2_rel:
                lefttwo_img.setVisibility(View.GONE);
                midtwo_img.setVisibility(View.GONE);
                righttwo_img.setVisibility(View.GONE);
                left1_img.setVisibility(View.GONE);
                left2_img.setVisibility(View.GONE);
                left3_img.setVisibility(View.GONE);
                left4_img.setVisibility(View.GONE);
                mid1_img.setVisibility(View.GONE);
                mid2_img.setVisibility(View.VISIBLE);
                mid3_img.setVisibility(View.GONE);
                mid4_img.setVisibility(View.GONE);
                submit_btn.setVisibility(View.GONE);
                break;
            case R.id.mid3_rel:
                lefttwo_img.setVisibility(View.GONE);
                midtwo_img.setVisibility(View.GONE);
                righttwo_img.setVisibility(View.GONE);
                left1_img.setVisibility(View.GONE);
                left2_img.setVisibility(View.GONE);
                left3_img.setVisibility(View.GONE);
                left4_img.setVisibility(View.GONE);
                mid1_img.setVisibility(View.GONE);
                mid2_img.setVisibility(View.GONE);
                mid3_img.setVisibility(View.VISIBLE);
                mid4_img.setVisibility(View.GONE);
                submit_btn.setVisibility(View.GONE);
                break;
            case R.id.mid4_rel:
                lefttwo_img.setVisibility(View.GONE);
                midtwo_img.setVisibility(View.GONE);
                righttwo_img.setVisibility(View.GONE);
                left1_img.setVisibility(View.GONE);
                left2_img.setVisibility(View.GONE);
                left3_img.setVisibility(View.GONE);
                left4_img.setVisibility(View.GONE);
                mid1_img.setVisibility(View.GONE);
                mid2_img.setVisibility(View.GONE);
                mid3_img.setVisibility(View.GONE);
                mid4_img.setVisibility(View.VISIBLE);
                submit_btn.setVisibility(View.GONE);
                break;
            case R.id.submit_btn:
                if(flag == 1)
                {
                    instance.rating2 = "LESS";
                    Log.d("Rating2",instance.rating2);
                }else
                if(flag == 2)
                {
                    instance.rating2 = "SAME";
                    Log.d("Rating2",instance.rating2);
                }else
                if(flag == 3)
                {
                    instance.rating2 = "MORE";
                    Log.d("Rating2",instance.rating2);
                }
                alert_reltwo.setVisibility(View.VISIBLE);
                break;
            case R.id.verifyOK_btn_two:
                progress = ProgressDialog.show(RatingtwoActivity.this, "uploading", "Please wait",true);
                String url = Constants.HOST_URL + Constants.UPLOAD_URL;
                RequestParams params = new RequestParams();
                params.put("username", instance.username);
                try {
                    params.put("userfile", new File(instance.imagePath));
                } catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                }
                params.put("title", instance.title);
                params.put("rating1", instance.rating1);
                params.put("rating2", instance.rating2);
                PhotoMetaAPI api = new PhotoMetaAPI(url, params, RatingtwoActivity.this);
                api.syncObject();
                break;
            case R.id.verifyCan_btn_two:
                alert_reltwo.setVisibility(View.GONE);
                break;
        }
    }
    private void showSuccessAlert(String message)  {
        AlertDialog alertDialog = new AlertDialog.Builder(RatingtwoActivity.this).create();
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
        AlertDialog alertDialog = new AlertDialog.Builder(RatingtwoActivity.this).create();
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
    public void success(String response) {
        Log.d("Server Response", response);
        progress.dismiss();
        Toast.makeText(this, "Submit successfully",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(RatingtwoActivity.this,UploadActivity.class);
        startActivity(intent);
    }

    @Override
    public void failed(String response, Throwable throwable) {
        showFailedAlert("Please check network state.");
        progress.dismiss();
    }
}
