package com.example.red.photometa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.red.photometa.Common.Application;

/**
 * Created by RED on 9/27/2016.
 */

public class Ratingone extends Activity implements View.OnClickListener {

    ImageView next_ratingone,previous_ratingone,left_img,mid_img,right_img,left1_img,left2_img,left3_img,left4_img,mid1_img,mid2_img,mid3_img,mid4_img;
    RelativeLayout left_rel,mid_rel,right_rel,left1_rel,left2_rel,left3_rel,left4_rel,mid1_rel,mid2_rel,mid3_rel,mid4_rel;
    Application instance;
    int flag = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ratingone);
        initView();
    }

    private void initView()
    {
        next_ratingone = (ImageView)findViewById(R.id.next_ratingone);
        previous_ratingone = (ImageView)findViewById(R.id.previous_ratingone);
        left_rel = (RelativeLayout) findViewById(R.id.left_rel);
        mid_rel = (RelativeLayout) findViewById(R.id.mid_rel);
        right_rel = (RelativeLayout) findViewById(R.id.right_rel);
        left1_rel = (RelativeLayout) findViewById(R.id.left1_rel);
        left2_rel = (RelativeLayout) findViewById(R.id.left2_rel);
        left3_rel = (RelativeLayout) findViewById(R.id.left3_rel);
        left4_rel = (RelativeLayout) findViewById(R.id.left4_rel);
        mid1_rel = (RelativeLayout) findViewById(R.id.mid1_rel);
        mid2_rel = (RelativeLayout) findViewById(R.id.mid2_rel);
        mid3_rel = (RelativeLayout) findViewById(R.id.mid3_rel);
        mid4_rel = (RelativeLayout) findViewById(R.id.mid4_rel);
        left_img = (ImageView) findViewById(R.id.left_img);
        mid_img = (ImageView) findViewById(R.id.mid_img);
        right_img = (ImageView) findViewById(R.id.right_img);
        left1_img = (ImageView)findViewById(R.id.left1_img);
        left2_img = (ImageView)findViewById(R.id.left2_img);
        left3_img = (ImageView)findViewById(R.id.left3_img);
        left4_img = (ImageView)findViewById(R.id.left4_img);
        mid1_img = (ImageView) findViewById(R.id.mid1_img);
        mid2_img = (ImageView) findViewById(R.id.mid2_img);
        mid3_img = (ImageView) findViewById(R.id.mid3_img);
        mid4_img = (ImageView) findViewById(R.id.mid4_img);

        mid_img.setVisibility(View.GONE);
        right_img.setVisibility(View.GONE);
        left1_img.setVisibility(View.GONE);
        left2_img.setVisibility(View.GONE);
        left3_img.setVisibility(View.GONE);
        left4_img.setVisibility(View.GONE);
        mid1_img.setVisibility(View.GONE);
        mid2_img.setVisibility(View.GONE);
        mid3_img.setVisibility(View.GONE);
        mid4_img.setVisibility(View.GONE);
        instance = Application.getSharedInstance();
        next_ratingone.setVisibility(View.GONE);

        next_ratingone.setOnClickListener(this);
        previous_ratingone.setOnClickListener(this);
        left_rel.setOnClickListener(this);
        mid_rel.setOnClickListener(this);
        right_rel.setOnClickListener(this);
        left1_rel.setOnClickListener(this);
        left2_rel.setOnClickListener(this);
        left3_rel.setOnClickListener(this);
        left4_rel.setOnClickListener(this);
        mid1_rel.setOnClickListener(this);
        mid2_rel.setOnClickListener(this);
        mid3_rel.setOnClickListener(this);
        mid4_rel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.next_ratingone:
                if(flag == 1)
                {
                    instance.rating1 = "It makes me sad";
                    Log.d("Rating1",instance.rating1);
                }else
                if(flag == 2)
                {
                    instance.rating1 = "I am ok with this";
                    Log.d("Rating1",instance.rating1);
                }else
                if(flag == 3)
                {
                    instance.rating1 = "It makes me happy";
                    Log.d("Rating1",instance.rating1);
                }
                Intent intent = new Intent(Ratingone.this, RatingtwoActivity.class);
                startActivity(intent);
                break;
            case R.id.previous_ratingone:
                onBackPressed();
                break;
            case R.id.left_rel:
                left_img.setVisibility(View.VISIBLE);
                mid_img.setVisibility(View.GONE);
                right_img.setVisibility(View.GONE);
                left1_img.setVisibility(View.GONE);
                left2_img.setVisibility(View.GONE);
                left3_img.setVisibility(View.GONE);
                left4_img.setVisibility(View.GONE);
                mid1_img.setVisibility(View.GONE);
                mid2_img.setVisibility(View.GONE);
                mid3_img.setVisibility(View.GONE);
                mid4_img.setVisibility(View.GONE);
                next_ratingone.setVisibility(View.VISIBLE);
                flag = 1;
                break;
            case R.id.mid_rel:
                left_img.setVisibility(View.GONE);
                mid_img.setVisibility(View.VISIBLE);
                right_img.setVisibility(View.GONE);
                left1_img.setVisibility(View.GONE);
                left2_img.setVisibility(View.GONE);
                left3_img.setVisibility(View.GONE);
                left4_img.setVisibility(View.GONE);
                mid1_img.setVisibility(View.GONE);
                mid2_img.setVisibility(View.GONE);
                mid3_img.setVisibility(View.GONE);
                mid4_img.setVisibility(View.GONE);
                next_ratingone.setVisibility(View.VISIBLE);
                flag = 2;
                break;
            case R.id.right_rel:
                left_img.setVisibility(View.GONE);
                mid_img.setVisibility(View.GONE);
                right_img.setVisibility(View.VISIBLE);
                left1_img.setVisibility(View.GONE);
                left2_img.setVisibility(View.GONE);
                left3_img.setVisibility(View.GONE);
                left4_img.setVisibility(View.GONE);
                mid1_img.setVisibility(View.GONE);
                mid2_img.setVisibility(View.GONE);
                mid3_img.setVisibility(View.GONE);
                mid4_img.setVisibility(View.GONE);
                next_ratingone.setVisibility(View.VISIBLE);
                flag = 3;
                break;
            case R.id.left1_rel:
                left_img.setVisibility(View.GONE);
                mid_img.setVisibility(View.GONE);
                right_img.setVisibility(View.GONE);
                left1_img.setVisibility(View.VISIBLE);
                left2_img.setVisibility(View.GONE);
                left3_img.setVisibility(View.GONE);
                left4_img.setVisibility(View.GONE);
                mid1_img.setVisibility(View.GONE);
                mid2_img.setVisibility(View.GONE);
                mid3_img.setVisibility(View.GONE);
                mid4_img.setVisibility(View.GONE);
                next_ratingone.setVisibility(View.GONE);
                break;
            case R.id.left2_rel:
                left_img.setVisibility(View.GONE);
                mid_img.setVisibility(View.GONE);
                right_img.setVisibility(View.GONE);
                left1_img.setVisibility(View.GONE);
                left2_img.setVisibility(View.VISIBLE);
                left3_img.setVisibility(View.GONE);
                left4_img.setVisibility(View.GONE);
                mid1_img.setVisibility(View.GONE);
                mid2_img.setVisibility(View.GONE);
                mid3_img.setVisibility(View.GONE);
                mid4_img.setVisibility(View.GONE);
                next_ratingone.setVisibility(View.GONE);
                break;
            case R.id.left3_rel:
                left_img.setVisibility(View.GONE);
                mid_img.setVisibility(View.GONE);
                right_img.setVisibility(View.GONE);
                left1_img.setVisibility(View.GONE);
                left2_img.setVisibility(View.GONE);
                left3_img.setVisibility(View.VISIBLE);
                left4_img.setVisibility(View.GONE);
                mid1_img.setVisibility(View.GONE);
                mid2_img.setVisibility(View.GONE);
                mid3_img.setVisibility(View.GONE);
                mid4_img.setVisibility(View.GONE);
                next_ratingone.setVisibility(View.GONE);
                break;
            case R.id.left4_rel:
                left_img.setVisibility(View.GONE);
                mid_img.setVisibility(View.GONE);
                right_img.setVisibility(View.GONE);
                left1_img.setVisibility(View.GONE);
                left2_img.setVisibility(View.GONE);
                left3_img.setVisibility(View.GONE);
                left4_img.setVisibility(View.VISIBLE);
                mid1_img.setVisibility(View.GONE);
                mid2_img.setVisibility(View.GONE);
                mid3_img.setVisibility(View.GONE);
                mid4_img.setVisibility(View.GONE);
                next_ratingone.setVisibility(View.GONE);
                break;
            case R.id.mid1_rel:
                left_img.setVisibility(View.GONE);
                mid_img.setVisibility(View.GONE);
                right_img.setVisibility(View.GONE);
                left1_img.setVisibility(View.GONE);
                left2_img.setVisibility(View.GONE);
                left3_img.setVisibility(View.GONE);
                left4_img.setVisibility(View.GONE);
                mid1_img.setVisibility(View.VISIBLE);
                mid2_img.setVisibility(View.GONE);
                mid3_img.setVisibility(View.GONE);
                mid4_img.setVisibility(View.GONE);
                next_ratingone.setVisibility(View.GONE);
                break;
            case R.id.mid2_rel:
                left_img.setVisibility(View.GONE);
                mid_img.setVisibility(View.GONE);
                right_img.setVisibility(View.GONE);
                left1_img.setVisibility(View.GONE);
                left2_img.setVisibility(View.GONE);
                left3_img.setVisibility(View.GONE);
                left4_img.setVisibility(View.GONE);
                mid1_img.setVisibility(View.GONE);
                mid2_img.setVisibility(View.VISIBLE);
                mid3_img.setVisibility(View.GONE);
                mid4_img.setVisibility(View.GONE);
                next_ratingone.setVisibility(View.GONE);
                break;
            case R.id.mid3_rel:
                left_img.setVisibility(View.GONE);
                mid_img.setVisibility(View.GONE);
                right_img.setVisibility(View.GONE);
                left1_img.setVisibility(View.GONE);
                left2_img.setVisibility(View.GONE);
                left3_img.setVisibility(View.GONE);
                left4_img.setVisibility(View.GONE);
                mid1_img.setVisibility(View.GONE);
                mid2_img.setVisibility(View.GONE);
                mid3_img.setVisibility(View.VISIBLE);
                mid4_img.setVisibility(View.GONE);
                next_ratingone.setVisibility(View.GONE);
                break;
            case R.id.mid4_rel:
                left_img.setVisibility(View.GONE);
                mid_img.setVisibility(View.GONE);
                right_img.setVisibility(View.GONE);
                left1_img.setVisibility(View.GONE);
                left2_img.setVisibility(View.GONE);
                left3_img.setVisibility(View.GONE);
                left4_img.setVisibility(View.GONE);
                mid1_img.setVisibility(View.GONE);
                mid2_img.setVisibility(View.GONE);
                mid3_img.setVisibility(View.GONE);
                mid4_img.setVisibility(View.VISIBLE);
                next_ratingone.setVisibility(View.GONE);
                break;
        }
    }
}
