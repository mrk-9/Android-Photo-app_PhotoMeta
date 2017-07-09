package com.example.red.photometa.Common;

import android.widget.ImageView;

/**
 * Created by RED on 9/29/2016.
 */

public class Application {

    public static Application instance = null;
    public static String username;
    public static String imagePath;
    public static String title;
    public static String rating1;
    public static String rating2;

    public static Application getSharedInstance()
    {
        if(instance == null)
        {
            instance = new Application();
        }
        return instance;
    }
    public Application()
    {

    }
}
