package com.strendent.tutorsu.Utilities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Irfan Ali on 6/17/2015.
 */
public class Utility {

    /**
     * Converting date from a given format to a specific format
     * @return String formatted date
     */

    public static String dateFormatter(String toFomat,String dateInString, String fromFormat){

        SimpleDateFormat formatter = new SimpleDateFormat(toFomat);
        try {
            Date date = formatter.parse(fromFormat);
            return formatter.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static byte[] convertBitmapToByteArray(Bitmap bmp){

        if(bmp!=null) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            return byteArray;
        }
        return null;
    }

    public static Bitmap convertByteArrayToBitmap(byte[] byteArray){

        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        return bmp;

    }

}
