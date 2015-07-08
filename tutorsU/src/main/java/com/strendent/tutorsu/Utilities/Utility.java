package com.strendent.tutorsu.Utilities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.joshdholtz.sentry.Sentry;
import com.strendent.tutorsu.R;

import java.io.ByteArrayOutputStream;
import java.net.InetAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Irfan Ali on 6/17/2015.
 */
public class Utility {

    /**
     * Converting date from a given format to a specific format
     * @return String formatted date
     */
    public static HashMap<String,String> sentryMap=new HashMap<>();
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
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni != null) {
            if(isInternetWorking()==true){
                return true;

            }else
            {
                return false;
            }
        } else
            return false;
    }
    public static boolean isInternetWorking() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");

            if (ipAddr.equals("")) {
                return false;
            } else {
                return true;
            }

        } catch (Exception e) {
            return false;
        }

    }
    public static void noInternetDialog(Context context){
        final Dialog dialog=new Dialog(context,android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_demo);

        // set the custom dialog components - text, image and button
        // if button is clicked, close the custom dialog
      /*  dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
*/
        dialog.show();
    }
    public static void sendSentryLog(Exception e,String expcetionMessage, String customMessage,HashMap sentryMap){
        Sentry.captureEvent(new Sentry.SentryEventBuilder().setMessage(expcetionMessage).
                setCulprit(customMessage).setTimestamp(System.currentTimeMillis())
                .setException(e).setExtra(sentryMap));
    }


}
