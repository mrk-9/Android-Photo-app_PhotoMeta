package com.example.red.photometa;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.red.photometa.Common.Application;
import com.loopj.android.http.RequestParams;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static android.R.attr.start;
import static android.R.attr.width;
import static com.example.red.photometa.R.attr.height;

/**
 * Created by RED on 9/27/2016.
 */

public class UploadActivity extends Activity implements View.OnClickListener {

    private static final int CAMERA_REQUEST = 1888;
    Button gallery_btn,takePhoto_btn,remove_btn;
    TextView logout_txt;
    ImageView next_img,photo_img;
    private static final int SELECT_PICTURE = 1;
    private String selectedImagePath;
    Application instance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload);
        initView();
    }

    private void initView()
    {
        gallery_btn = (Button)findViewById(R.id.gallery_btn);
        takePhoto_btn = (Button)findViewById(R.id.takePhoto_btn);
        remove_btn = (Button)findViewById(R.id.remove_btn);
        next_img = (ImageView)findViewById(R.id.next_upload);
        logout_txt = (TextView)findViewById(R.id.logout_upload);
        photo_img = (ImageView)findViewById(R.id.img_photo);

        remove_btn.setVisibility(View.GONE);
        next_img.setVisibility(View.GONE);
        instance = Application.getSharedInstance();

        gallery_btn.setOnClickListener(this);
        takePhoto_btn.setOnClickListener(this);
        remove_btn.setOnClickListener(this);
        next_img.setOnClickListener(this);
        logout_txt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.gallery_btn:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
                break;

            case R.id.takePhoto_btn:
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
                break;

            case R.id.remove_btn:
                photo_img.setImageBitmap(null);
                gallery_btn.setVisibility(View.VISIBLE);
                takePhoto_btn.setVisibility(View.VISIBLE);
                remove_btn.setVisibility(View.GONE);
                next_img.setVisibility(View.GONE);
                break;

            case R.id.next_upload:
                Intent intent1 = new Intent(UploadActivity.this, TitleActivity.class);
                startActivity(intent1);
                break;

            case R.id.logout_upload:
                Intent intent2 = new Intent(UploadActivity.this,LoginActivity.class);
                startActivity(intent2);
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();
            selectedImagePath = getPath(selectedImageUri);

            if (requestCode == SELECT_PICTURE && data != null && data.getData() != null)
            {
                instance.imagePath = selectedImagePath;
                Log.d("ImagePath", instance.imagePath);
                System.out.println("Image Path : " + selectedImagePath);
                photo_img.setImageBitmap(decodeSampledBitmapFromResource(getResources(), selectedImagePath, 100, 100));

                gallery_btn.setVisibility(View.GONE);
                takePhoto_btn.setVisibility(View.GONE);
                remove_btn.setVisibility(View.VISIBLE);
                next_img.setVisibility(View.VISIBLE);
            }
            if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK)
            {
                instance.imagePath = selectedImagePath;
                Log.d("ImagePath from camera", instance.imagePath);
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                photo_img.setImageBitmap(photo);

                gallery_btn.setVisibility(View.GONE);
                takePhoto_btn.setVisibility(View.GONE);
                remove_btn.setVisibility(View.VISIBLE);
                next_img.setVisibility(View.VISIBLE);
            }
        }
    }

    //Image compression
    public static Bitmap decodeSampledBitmapFromResource(Resources res,String filePath,int reqWidth,int reqHeight)
    {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }
    //Image compression
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight)
    {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth)
        {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }
    //get ImagePath
    public String getPath(Uri uri)
    {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }
    //Disable back button
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }
}
