package com.example.red.photometa;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.red.photometa.Common.Application;

/**
 * Created by RED on 9/27/2016.
 */

public class TitleActivity extends Activity implements View.OnClickListener {

    ImageView next_img,previous_img;
    EditText title_edittxt;
    Application instance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title);
        initView();
        editTextState();
    }
    private void initView()
    {
        next_img = (ImageView)findViewById(R.id.next_title);
        previous_img = (ImageView)findViewById(R.id.previous_title);
        title_edittxt = (EditText) findViewById(R.id.title_title);

        next_img.setVisibility(View.GONE);
        instance = Application.getSharedInstance();

        next_img.setOnClickListener(this);
        previous_img.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.next_title:
                if(title_edittxt.getText().length() <= 0)
                    showFailedAlert("Please enter this photo a title");
                else
                {
                    Log.d("Title",title_edittxt.getText().toString());
                    instance.title = title_edittxt.getText().toString();
                    Intent intent = new Intent(TitleActivity.this, Ratingone.class);
                    startActivity(intent);
                }
                break;
            case R.id.previous_title:
                onBackPressed();
                break;
        }
    }
    private void showFailedAlert(String message)  {
        AlertDialog alertDialog = new AlertDialog.Builder(TitleActivity.this).create();
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
    private void editTextState()
    {
        title_edittxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                next_img.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    //Disable back button
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }
}
